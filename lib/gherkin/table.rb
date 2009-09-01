
# line 1 "lib/gherkin/table.rl"
module Gherkin
  class Table
    
# line 14 "lib/gherkin/table.rl"


    def initialize
      
# line 12 "lib/gherkin/table.rb"
class << self
	attr_accessor :_table_key_offsets
	private :_table_key_offsets, :_table_key_offsets=
end
self._table_key_offsets = [
	0, 0, 4, 8, 9, 17, 21, 26
]

class << self
	attr_accessor :_table_trans_keys
	private :_table_trans_keys, :_table_trans_keys=
end
self._table_trans_keys = [
	32, 124, 9, 13, 65, 90, 97, 122, 
	124, 10, 32, 9, 13, 65, 90, 97, 
	122, 10, 32, 9, 13, 10, 32, 124, 
	9, 13, 10, 32, 124, 9, 13, 0
]

class << self
	attr_accessor :_table_single_lengths
	private :_table_single_lengths, :_table_single_lengths=
end
self._table_single_lengths = [
	0, 2, 0, 1, 2, 2, 3, 3
]

class << self
	attr_accessor :_table_range_lengths
	private :_table_range_lengths, :_table_range_lengths=
end
self._table_range_lengths = [
	0, 1, 2, 0, 3, 1, 1, 1
]

class << self
	attr_accessor :_table_index_offsets
	private :_table_index_offsets, :_table_index_offsets=
end
self._table_index_offsets = [
	0, 0, 4, 7, 9, 15, 19, 24
]

class << self
	attr_accessor :_table_trans_targs
	private :_table_trans_targs, :_table_trans_targs=
end
self._table_trans_targs = [
	1, 2, 1, 0, 3, 3, 0, 4, 
	0, 7, 5, 5, 3, 3, 0, 7, 
	5, 5, 0, 7, 6, 2, 6, 0, 
	7, 6, 2, 6, 0, 0
]

class << self
	attr_accessor :table_start
end
self.table_start = 1;
class << self
	attr_accessor :table_first_final
end
self.table_first_final = 7;
class << self
	attr_accessor :table_error
end
self.table_error = 0;

class << self
	attr_accessor :table_en_main
end
self.table_en_main = 1;


# line 18 "lib/gherkin/table.rl"
    end

    def scan(data, listener)
      data = data.unpack("c*") if data.is_a?(String)
      
# line 92 "lib/gherkin/table.rb"
begin
	p ||= 0
	pe ||= data.length
	cs = table_start
end

# line 23 "lib/gherkin/table.rl"
      
# line 101 "lib/gherkin/table.rb"
begin
	_klen, _trans, _keys = nil
	_goto_level = 0
	_resume = 10
	_eof_trans = 15
	_again = 20
	_test_eof = 30
	_out = 40
	while true
	_trigger_goto = false
	if _goto_level <= 0
	if p == pe
		_goto_level = _test_eof
		next
	end
	if cs == 0
		_goto_level = _out
		next
	end
	end
	if _goto_level <= _resume
	_keys = _table_key_offsets[cs]
	_trans = _table_index_offsets[cs]
	_klen = _table_single_lengths[cs]
	_break_match = false
	
	begin
	  if _klen > 0
	     _lower = _keys
	     _upper = _keys + _klen - 1

	     loop do
	        break if _upper < _lower
	        _mid = _lower + ( (_upper - _lower) >> 1 )

	        if data[p] < _table_trans_keys[_mid]
	           _upper = _mid - 1
	        elsif data[p] > _table_trans_keys[_mid]
	           _lower = _mid + 1
	        else
	           _trans += (_mid - _keys)
	           _break_match = true
	           break
	        end
	     end # loop
	     break if _break_match
	     _keys += _klen
	     _trans += _klen
	  end
	  _klen = _table_range_lengths[cs]
	  if _klen > 0
	     _lower = _keys
	     _upper = _keys + (_klen << 1) - 2
	     loop do
	        break if _upper < _lower
	        _mid = _lower + (((_upper-_lower) >> 1) & ~1)
	        if data[p] < _table_trans_keys[_mid]
	          _upper = _mid - 2
	        elsif data[p] > _table_trans_keys[_mid+1]
	          _lower = _mid + 2
	        else
	          _trans += ((_mid - _keys) >> 1)
	          _break_match = true
	          break
	        end
	     end # loop
	     break if _break_match
	     _trans += _klen
	  end
	end while false
	cs = _table_trans_targs[_trans]
	end
	if _goto_level <= _again
	if cs == 0
		_goto_level = _out
		next
	end
	p += 1
	if p != pe
		_goto_level = _resume
		next
	end
	end
	if _goto_level <= _test_eof
	end
	if _goto_level <= _out
		break
	end
	end
	end

# line 24 "lib/gherkin/table.rl"
    end
  end
end