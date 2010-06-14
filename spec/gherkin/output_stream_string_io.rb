if defined?(JRUBY_VERSION)
  class OutputStreamStringIO < Java.java.io.ByteArrayOutputStream
    def write(what)
      if String === what
        super(Java.java.lang.String.new(what).getBytes("UTF-8"))
      else
        super(what)
      end
    end

    def string
      toString("UTF-8")
    end
  end

  require 'stringio'
  class StringIO
    class << self
      def new
        OutputStreamStringIO.new
      end
    end
  end
end
