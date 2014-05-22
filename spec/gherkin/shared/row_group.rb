# encoding: utf-8
module Gherkin
  module Lexer
    shared_examples_for "a Gherkin lexer lexing rows" do
      def scan(gherkin)
        @lexer.scan(gherkin)
      end

      rows = {
        "|a|b|\n"        => %w{a b},
        "|a|b|c|\n"      => %w{a b c},
      }

      rows.each do |text, expected|
        it "is parse #{text}" do
          expect(@listener).to receive(:row).with(r(expected), 1)
          scan(text.dup)
        end
      end

      it "parses a row with many cells" do
        expect(@listener).to receive(:row).with(r(%w{a b c d e f g h i j k l m n o p}), 1)
        scan("|a|b|c|d|e|f|g|h|i|j|k|l|m|n|o|p|\n")
      end

      it "parses multicharacter cell content" do
        expect(@listener).to receive(:row).with(r(%w{foo bar}), 1)
        scan("| foo | bar |\n")
      end

      it "escapes backslashed pipes" do
        expect(@listener).to receive(:row).with(r(['|', 'the', '\a', '\\', '|\\|']), 1)
        scan('| \| | the | \a | \\ |   \|\\\|    |' + "\n")
      end

      it "parses cells with newlines" do
        expect(@listener).to receive(:row).with(r(["\n"]), 1)
        scan("|\\n|" + "\n")
      end

      it "parses cells with spaces within the content" do
        expect(@listener).to receive(:row).with(r(["Dill pickle", "Valencia orange"]), 1)
        scan("| Dill pickle | Valencia orange |\n")
      end

      it "allows utf-8" do
        scan(" | ůﻚ | 2 | \n")
        expect(@listener.to_sexp).to eq([
          [:row, ["ůﻚ", "2"], 1],
          [:eof]
        ])
      end

      it "allows utf-8 using should_receive" do
        expect(@listener).to receive(:row).with(r(['繁體中文  而且','並且','繁體中文  而且','並且']), 1)
        scan("| 繁體中文  而且|並且| 繁體中文  而且|並且|\n")
      end

      it "parses a 2x2 table" do
        expect(@listener).to receive(:row).with(r(%w{1 2}), 1)
        expect(@listener).to receive(:row).with(r(%w{3 4}), 2)
        scan("| 1 | 2 |\n| 3 | 4 |\n")
      end

      it "parses a 2x2 table with empty cells" do
        expect(@listener).to receive(:row).with(r(['1', '']), 1)
        expect(@listener).to receive(:row).with(r(['', '4']), 2)
        scan("| 1 |  |\n|| 4 |\n")
      end

      it "parses a row with empty cells" do
        expect(@listener).to receive(:row).with(r(['1', '']), 1).twice
        scan("| 1 |  |\n")
        scan("|1||\n")
      end

      it "parses a 1x2 table that does not end in a newline" do
        expect(@listener).to receive(:row).with(r(%w{1 2}), 1)
        scan("| 1 | 2 |")
      end

      it "parses a row without spaces and with a newline" do
        expect(@listener).to receive(:row).with(r(%w{1 2}), 1)
        scan("|1|2|\n")
      end

      it "parses a row with whitespace after the rows" do
        expect(@listener).to receive(:row).with(r(%w{1 2}), 1)
        scan("| 1 | 2 | \n ")
      end

      it "parses a row with lots of whitespace" do
        expect(@listener).to receive(:row).with(r(["abc", "123"]), 1)
        scan("  \t| \t   abc\t| \t123\t \t\t| \t\t   \t \t\n  ")
      end

      it "parses a table with a commented-out row" do
        expect(@listener).to receive(:row).with(r(["abc"]), 1)
        expect(@listener).to receive(:comment).with("#|123|", 2)
        expect(@listener).to receive(:row).with(r(["def"]), 3)
        scan("|abc|\n#|123|\n|def|\n")
      end

      it "raises LexingError for rows that aren't closed" do
        expect do
          scan("|| oh hello \n")
        end.to raise_error(/Lexing error on line 1: '\|\| oh hello/)
      end

      it "raises LexingError for rows that are followed by a comment" do
        expect do
          scan("|hi| # oh hello \n")
        end.to raise_error(/Lexing error on line 1: '\|hi\| # oh hello/)
      end

      it "raises LexingError for rows that aren't closed" do
        expect do
          scan("|| oh hello \n  |Shoudn't Get|Here|")
        end.to raise_error(/Lexing error on line 1: '\|\| oh hello/)
      end
    end
  end
end
