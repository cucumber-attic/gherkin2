# encoding: utf-8
require 'spec_helper'
require 'gherkin/formatter/step_printer'
require 'gherkin/formatter/argument'
require 'stringio'

module Gherkin
  module Formatter
    class ParenthesisFormat
      def text(text)
        "(#{text})"
      end
    end

    class BracketFormat
      def text(text)
        "[#{text}]"
      end
    end

    describe StepPrinter do
      before do
        @io = StringIO.new
        @p = StepPrinter.new
        @pf = ParenthesisFormat.new
        @bf = BracketFormat.new
      end

      it "replaces 0 args" do
        @p.write_step(@io, @pf, @bf, "I have 10 cukes", [])
        expect(@io.string).to eq("(I have 10 cukes)")
      end

      it "replaces 1 arg" do
        @p.write_step(@io, @pf, @bf, "I have 10 cukes", [Argument.new(7, '10')])
        expect(@io.string).to eq("(I have )[10]( cukes)")
      end

      it "replaces 1 unicode arg" do
        @p.write_step(@io, @pf, @bf, "I hæve øæ cåkes", [Argument.new(7, 'øæ')])
        expect(@io.string).to eq("(I hæve )[øæ]( cåkes)")
      end

      it "replaces 2 args" do
        @p.write_step(@io, @pf, @bf, "I have 10 yellow cukes in my belly", [Argument.new(7, '10'), Argument.new(17, 'cukes')])
        expect(@io.string).to eq("(I have )[10]( yellow )[cukes]( in my belly)")
      end

      it "replaces 2 unicode args" do
        @p.write_step(@io, @pf, @bf, "Æslåk likes æøå", [Argument.new(0, 'Æslåk'), Argument.new(12, 'æøå')])
        expect(@io.string).to eq("[Æslåk]( likes )[æøå]")
      end
    end
  end
end
