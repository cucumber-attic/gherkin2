// This is a straight port of json_formatter.rb
var JSONFormatter = function(io) {
    this.io = io;
    this.feature_hashes = []

	this.uri = function(uri) {
	    this.uri = uri;
	};
	
	this.feature = function(feature) {
	    this.feature_hash = feature.to_hash;
	    this.feature_hash['uri'] = this.uri;
	    this.feature_hashes.push(this.feature_hash);
	};

	this.background = function(background) {
	    feature_elements().push(background.to_hash);
	};
	
	this.scenario = function(scenario) {
	    feature_elements().push(scenario.to_hash);
	};

	this.scenario_outline = function(scenario_outline) {
	    feature_elements().push(scenario_outline.to_hash);
	};

	this.examples = function(examples) {
	    all_examples().push(examples.to_hash);
	};

    this.step = function(step) {
      	this.current_step_or_hook = step.to_hash;
      	steps().push(this.current_step_or_hook);
    }
	
    this.match = function(match) {
      	this.current_step_or_hook['match'] = match.to_hash;
    }

    this.result = function(result) {
      	this.current_step_or_hook['result'] = result.to_hash;
    }

    this.before = function(match, result) {
        add_hook(match, result, "before");
    }

    this.after = function(match, result) {
        add_hook(match, result, "after");
    }

    this.embedding = function(mime_type, data) {
      embeddings().push({'mime_type': mime_type, 'data': encode64s(data)})
    }

	this.write = function(text) {
		output().push(text);
	};

	this.eof = function() {};
	
	this.done = function() {
	    this.io.write(JSON.stringify(this.feature_hashes));
	};

	// "private" methods
	var self = this;

    function add_hook(match, result, hook) {
		if(!feature_element()[hook]) {
			feature_element()[hook] = [];
		}
        var hooks = feature_element()[hook];
        hooks.push({'match': match.to_hash, 'result': result.to_hash});
    }

	function feature_elements() {
		if(!self.feature_hash['elements']) {
			self.feature_hash['elements'] = [];
		}
		return self.feature_hash['elements'];
	}

	function feature_element() {
		return feature_elements()[feature_elements().length - 1];
	}

	function all_examples() {
		if(!feature_element()['examples']) {
			feature_element()['examples'] = [];
		}
		return feature_element()['examples'];
	}

	function steps() {
		if(!feature_element()['steps']) {
			feature_element()['steps'] = [];
		}
		return feature_element()['steps'];
	}

	function embeddings() {
		if(!self.current_step_or_hook['embeddings']) {
			self.current_step_or_hook['embeddings'] = [];
		}
		return self.current_step_or_hook['embeddings'];
	}

	function output() {
		if(!self.current_step_or_hook['output']) {
			self.current_step_or_hook['output'] = [];
		}
		return self.current_step_or_hook['output'];
	}
	
	// http://gitorious.org/javascript-base64/javascript-base64/blobs/master/base64.js
	function encode64s(input) {
		var swaps = ["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","0","1","2","3","4","5","6","7","8","9","+","/"];
		var input_binary = "";
		var output = "";
		var temp_binary;
		var index;
		for (index=0; index < input.length; index++) {
			temp_binary = input.charCodeAt(index).toString(2);
			while (temp_binary.length < 8)
			{
				temp_binary = "0"+temp_binary;
			}
			input_binary = input_binary + temp_binary;
			while (input_binary.length >= 6)
			{
				output = output + swaps[parseInt(input_binary.substring(0,6),2)];
				input_binary = input_binary.substring(6);
			}
		}
		if (input_binary.length == 4)
		{
			temp_binary = input_binary + "00";
			output = output + swaps[parseInt(temp_binary,2)];
			output = output + "=";
		}
		if (input_binary.length == 2)
		{
			temp_binary = input_binary + "0000";
			output = output + swaps[parseInt(temp_binary,2)];
			output = output + "==";
		}
		return output;
	}
}

exports = JSONFormatter;
