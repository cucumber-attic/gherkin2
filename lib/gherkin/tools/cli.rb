begin
  require 'trollop'
rescue LoadError
  require 'rubygems'
  require 'trollop'
end

module Gherkin
  module Tools
    sub_commands = %w(stats reformat)

    autoload :Stats, 'gherkin/tools/stats'
    autoload :Reformat, 'gherkin/tools/reformat'
    
    global_opts = Trollop::options do
      banner "Super fast gherkin parser"
      stop_on sub_commands
    end

    cmd = ARGV.shift
    cmd_opts = case cmd
    when 'stats'
      Stats.new(ARGV).run
    when 'reformat'
      Reformat.new(ARGV).run
    else
      Trollop::die "Missing command\nusage: gherkin COMMAND [ARGS]\n\nAvailable commands:\n#{sub_commands.join("\n")}\n"
    end
  end
end
