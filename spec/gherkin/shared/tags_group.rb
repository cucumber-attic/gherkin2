# encoding: utf-8
module Gherkin
  module Lexer
    shared_examples_for "a Gherkin lexer lexing tags" do
      def scan(gherkin)
        @lexer.scan(gherkin)
      end

      it "lexs a single tag" do
        expect(@listener).to receive(:tag).with("@dog", 1)
        scan("@dog\n")
      end

      it "lexs multiple tags" do
        expect(@listener).to receive(:tag).twice
        scan("@dog @cat\n")
      end

      it "lexs UTF-8 tags" do
        expect(@listener).to receive(:tag).with("@シナリオテンプレート", 1)
        scan("@シナリオテンプレート\n")
      end

      it "lexs mixed tags" do
        expect(@listener).to receive(:tag).with("@wip", 1).ordered
        expect(@listener).to receive(:tag).with("@Значения", 1).ordered
        scan("@wip @Значения\n")
      end

      it "lexs wacky identifiers" do
        expect(@listener).to receive(:tag).exactly(4).times
        scan("@BJ-x98.77 @BJ-z12.33 @O_o" "@#not_a_comment\n")
      end

      # TODO: Ask on ML for opinions about this one
      it "lexs tags without spaces between them?" do
        expect(@listener).to receive(:tag).twice
        scan("@one@two\n")
      end

      it "does not lex tags beginning with two @@ signs" do
        expect(@listener).not_to receive(:tag)
        expect { scan("@@test\n") }.to raise_error(/Lexing error on line 1/)
      end

      it "does not lex a lone @ sign" do
        expect(@listener).not_to receive(:tag)
        expect { scan("@\n") }.to raise_error(/Lexing error on line 1/)
      end
    end
  end
end
