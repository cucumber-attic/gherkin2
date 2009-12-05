begin
  require 'trollop'
rescue LoadError
  require 'rubygems'
  require 'trollop'
end
require 'gherkin/tools'

module Gherkin
  module Cli
    class Main
      def self.run(args)
        Trollop::options(args) do
          banner "Super fast gherkin parser"
          stop_on Tools::SUB_COMMANDS
        end

        cmd_name = args.shift
        begin
          die("Missing command") if cmd_name.nil?
          Tools.const_get(cmd_name.capitalize.to_sym).new(args).run
        rescue NameError
          die("Unknown command #{cmd_name}")
        end
      end

      def self.die(msg)
        Trollop::die "#{msg}\nusage: gherkin COMMAND [ARGS]\n\nAvailable commands:\n#{Tools::SUB_COMMANDS.join("\n")}\n"
      end
    end
  end
end
