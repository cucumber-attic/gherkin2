if defined?(JRUBY_VERSION)
  class OutputStreamStringIO < Java.java.io.ByteArrayOutputStream
    def rewind
    end

    def read
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
