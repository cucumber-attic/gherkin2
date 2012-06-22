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
	
	this.scenario = function(scenario) {
	    feature_elements().push(scenario.to_hash);
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

    this.after = function(match, result) {
        add_hook(match, result, "after");
    }

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

	function steps() {
		if(!feature_element()['steps']) {
			feature_element()['steps'] = [];
		}
		return feature_element()['steps'];
	}
}

exports = JSONFormatter;
