module Gherkin
  module Parser
    class Misc
      %%{
        machine misc;
        
        action start_pystring {
          # capture indent as well?
          pystring_start = p + 4
        }

        action end_pystring {
          pystring_content = data[pystring_start...(p - 4)].pack("U*")
          @listener.pystring(pystring_content)
        }
        
        PyStringStart = '"""' space* '\n' ;
        PyStringEnd = '"""' ;
        PyString = PyStringStart any* PyStringEnd ;

        main := PyString >start_pystring %end_pystring ;
      }%%
      
      def initialize(listener)
        @listener = listener
        %% write data;
      end
      
      def scan(data)
        data = data.unpack("U*") if data.is_a?(String)
        eof = data.length
        
        %% write init;
        %% write exec;
      end
    end
  end
end
