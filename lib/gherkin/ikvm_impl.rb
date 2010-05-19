# export MONO_PATH=/usr/local/ikvm/bin:lib

class Class
  # Causes a .NET class to be instantiated instead of the Ruby class when 
  # running on IronRuby. This is used to test both pure .NET and pure Ruby classes 
  # from the same Ruby based test suite. The .NET Class must have a package name
  # that corresponds with the Ruby class.
  def ikvm_impl(dll)
    if defined?(RUBY_ENGINE) && RUBY_ENGINE == "ironruby"
      load_assembly(dll)
      class << self
        def ikvmify(arg)
          if Array === arg
            arg.map{|a| ikvmify(a)}
          else
            case(arg)
            when Regexp
              Object.const_get('java').const_get('util').const_get('regex').const_get('Pattern').compile(arg.source)
            else
              arg
            end
          end
        end

        def new(*args)
          ikvm_class.new(*ikvmify(args))
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
end