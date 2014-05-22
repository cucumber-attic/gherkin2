require 'spec_helper'
require 'gherkin/formatter/ansi_escapes'

module Gherkin
  module Formatter
    describe AnsiEscapes do
      describe "instance methods" do
        include AnsiEscapes

        it "failed is red" do
          expect(failed).to eq("\e[31m")
        end

        it "failed_arg is red bold" do
          expect(failed_arg).to eq("\e[31m\e[1m")
        end
      end

      describe "class methods" do
        subject { AnsiEscapes }

        it "failed is red" do
          expect(subject.failed).to eq("\e[31m")
        end

        it "failed_arg is red bold" do
          expect(subject.failed_arg).to eq("\e[31m\e[1m")
        end
      end
    end
  end
end
