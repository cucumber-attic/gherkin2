module Gherkin
  module Tools
    SUB_COMMANDS = %w(stats reformat)
    SUB_COMMANDS.each do |cmd|
      autoload cmd.capitalize.to_sym, "gherkin/tools/#{cmd}"
    end
  end
end
