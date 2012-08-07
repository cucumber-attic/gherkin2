// This is a straight port of json_formatter.rb
var JSONFormatter = function(io) {
    var feature_hashes = [];
	var uri, feature_hash, current_step_or_hook;

    this.done = function() {
        io.write(JSON.stringify(feature_hashes));
    };

    this.uri = function(_uri) {
        uri = _uri;
    };
    
    this.feature = function(feature) {
        feature_hash = feature;
        feature_hash['uri'] = uri;
        feature_hashes.push(feature_hash);
    };

    this.background = function(background) {
        feature_elements().push(background);
    };
    
    this.scenario = function(scenario) {
        feature_elements().push(scenario);
    };

    this.scenario_outline = function(scenario_outline) {
        feature_elements().push(scenario_outline);
    };

    this.examples = function(examples) {
        all_examples().push(examples);
    };

    this.step = function(step) {
	    current_step_or_hook = step;
	    steps().push(current_step_or_hook);
    }
    
    this.match = function(match) {
	    current_step_or_hook['match'] = match;
    }

    this.result = function(result) {
		current_step_or_hook['result'] = result;
    }

    this.hook = function(type, match, result) {
      if(!feature_element()['hooks']) {
          feature_element()['hooks'] = [];
      }
      var hooks = feature_element()['hooks'];
      hooks.push({'type': type, 'match': match, 'result': result});
    }

    this.embedding = function(mime_type, data) {
	    embeddings().push({'mime_type': mime_type, 'data': encode64s(data)})
    }

    this.write = function(text) {
        output().push(text);
    };

    this.eof = function() {};
    
    // "private" methods

    function feature_elements() {
        if(!feature_hash['elements']) {
            feature_hash['elements'] = [];
        }
        return feature_hash['elements'];
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
        if(!current_step_or_hook['embeddings']) {
            current_step_or_hook['embeddings'] = [];
        }
        return current_step_or_hook['embeddings'];
    }

    function output() {
        if(!current_step_or_hook['output']) {
            current_step_or_hook['output'] = [];
        }
        return current_step_or_hook['output'];
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
            while (temp_binary.length < 8) {
                temp_binary = "0"+temp_binary;
            }
            input_binary = input_binary + temp_binary;
            while (input_binary.length >= 6) {
                output = output + swaps[parseInt(input_binary.substring(0,6),2)];
                input_binary = input_binary.substring(6);
            }
        }
        if (input_binary.length == 4) {
            temp_binary = input_binary + "00";
            output = output + swaps[parseInt(temp_binary,2)];
            output = output + "=";
        }
        if (input_binary.length == 2) {
            temp_binary = input_binary + "0000";
            output = output + swaps[parseInt(temp_binary,2)];
            output = output + "==";
        }
        return output;
    }
}

module.exports = JSONFormatter;
