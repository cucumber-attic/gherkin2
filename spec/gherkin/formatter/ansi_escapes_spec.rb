require 'spec_helper'
require 'gherkin/formatter/ansi_escapes'

module Gherkin
  module Formatter
    describe AnsiEscapes do
      describe "instance methods" do
        include AnsiEscapes

        it "failed should be red" do
          failed.should == "\e[31m"
        end

        it "failed_arg should be red bold" do
          failed_arg.should == "\e[31m\e[1m"
        end
      end

      describe "class methods" do
        subject { AnsiEscapes }

        it "failed should be red" do
          subject.failed.should == "\e[31m"
        end

        it "failed_arg should be red bold" do
          subject.failed_arg.should == "\e[31m\e[1m"
        end
      end
    end
  end
end
