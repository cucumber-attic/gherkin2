class Class

  def implements(java_class_name)
    # no-op
  end

  # Causes a Java class to be instantiated instead of the Ruby class when 
  # running on JRuby.
  def native_impl(lib)
    require "#{lib}.jar"

    class << self
      def javaify(arg)
        case(arg)
        when Array
          arg.map{|a| javaify(a)}
        when Regexp
          java.util.regex.Pattern.compile(arg.source)
        else
          arg
        end
      end

      def new(*args)
        java_class.new(*javaify(args))
      end

      def ===(object)
        super || object.java_kind_of?(java_class)
      end

      def java_class
        names = self.name.split('::')
        package = Java
        names[0..-2].each do |module_name|
          package = package.__send__(module_name.downcase)
        end

        package.__send__(names[-1])
      end
    end
  end
end