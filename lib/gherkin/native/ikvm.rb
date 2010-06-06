load_assembly('IKVM.OpenJDK.Core')

class Class

  def implements(java_class_name)
    m = java_class_name.split('.').inject(Object) do |mod, name|
      mod = mod.const_get(name)
    end
    include m
  end

  # Causes an IKVM-compiled Java class to be instantiated instead of the Ruby class when 
  # running on IronRuby.
  def native_impl(lib)
    begin
      load_assembly(lib)
    rescue LoadError => e
      # TODO - provide a different tip when running on .NET (Windows)
      e.message << "\nTry this: SET MONO_PATH=#{File.expand_path(File.dirname(__FILE__) + '/../..')} (or export MONO_PATH=...)"
      raise e
    end

    class << self
      def ikvmify(arg)
        case(arg)
        when Array
          array_list = Object.const_get('java').const_get('util').const_get('ArrayList').new
          arg.each do |o| 
            array_list.add(ikvmify(o))
          end
          array_list
        when String
          Object.const_get('java').const_get('lang').const_get('StringBuffer').new(arg).toString
        when Regexp
          Object.const_get('java').const_get('util').const_get('regex').const_get('Pattern').compile(arg.source)
        else
          arg
        end
      end

      def new(*args)
        begin
          ikvm_class.new(*ikvmify(args))
        rescue System::InvalidCastException
          ikvm_class.new(ikvmify(args).get(0))
        end
      end

      def ===(object)
        super || object.java_kind_of?(java_class)
      end

      def ikvm_class
        names = self.name.split('::')
        namespace = Object
        names[0..-2].each do |module_name|
          namespace = namespace.const_get(module_name.downcase)
        end

        namespace.const_get(names[-1])
      end
    end
  end
end