module Gherkin
  module Rubify
    if defined?(JRUBY_VERSION)
      # Translate Java objects to Ruby.
      # This is especially important to convert java.util.List coming
      # from Java and back to a Ruby Array.
      def rubify(o)
        if Java.java.util.Collection === o || Array === o
          o.map{|e| rubify(e)}
        else
          o
        end
      end
    else
      def rubify(o)
        o
      end
    end
  end
end