var JSONFormatter = function(io) {
    this.io = io;
    this.feature_hashes = []
}

JSONFormatter.prototype.uri = function(uri) {
    this.uri = uri;
};
JSONFormatter.prototype.feature = function(feature) {
    console.log(feature);
    this.feature_hash = feature.to_hash(); // ??? Property 'to_hash' of object #<rb::Feature> is not a function
    this.feature_hash['uri'] = this.uri;
    this.feature_hashes.push(this.feature_hash);

};
JSONFormatter.prototype.eof = function() {};
JSONFormatter.prototype.done = function() {
    this.io.write(JSON.stringify(this.feature_hashes));
};

exports = JSONFormatter;
