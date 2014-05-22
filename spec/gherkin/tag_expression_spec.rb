require 'spec_helper'
require 'gherkin/tag_expression'
require 'gherkin/formatter/model'

module Gherkin
  describe TagExpression do
    def tag(name)
      Formatter::Model::Tag.new(name, 0)
    end

    context "no tags" do
      before(:each) do
        @e = Gherkin::TagExpression.new([])
      end

      it 'is empty' do
        expect(@e).to be_empty
      end

      it "matchs @foo" do
        expect(@e.evaluate([tag('@foo')])).to be_true
      end

      it "matchs empty tags" do
        expect(@e.evaluate([])).to be_true
      end
    end

    context "@foo" do
      before(:each) do
        @e = Gherkin::TagExpression.new(['@foo'])
      end

      it "matchs @foo" do
        expect(@e.evaluate([tag('@foo')])).to be_true
      end

      it "does not match @bar" do
        expect(@e.evaluate([tag('@bar')])).to be_false
      end

      it "does not match no tags" do
        expect(@e.evaluate([])).to be_false
      end
    end

    context "!@foo" do
      before(:each) do
        @e = Gherkin::TagExpression.new(['~@foo'])
      end

      it "matchs @bar" do
        expect(@e.evaluate([tag('@bar')])).to be_true
      end

      it "does not match @foo" do
        expect(@e.evaluate([tag('@foo')])).to be_false
      end
    end

    context "@foo || @bar" do
      before(:each) do
        @e = Gherkin::TagExpression.new(['@foo,@bar'])
      end

      it "matchs @foo" do
        expect(@e.evaluate([tag('@foo')])).to be_true
      end

      it "matchs @bar" do
        expect(@e.evaluate([tag('@bar')])).to be_true
      end

      it "does not match @zap" do
        expect(@e.evaluate([tag('@zap')])).to be_false
      end
    end

    context "(@foo || @bar) && !@zap" do
      before(:each) do
        @e = Gherkin::TagExpression.new(['@foo,@bar', '~@zap'])
      end

      it "matchs @foo" do
        expect(@e.evaluate([tag('@foo')])).to be_true
      end

      it "does not match @foo @zap" do
        expect(@e.evaluate([tag('@foo'), tag('@zap')])).to be_false
      end
    end

    context "(@foo:3 || !@bar:4) && @zap:5" do
      before(:each) do
        @e = Gherkin::TagExpression.new(['@foo:3,~@bar','@zap:5'])
      end

      it "counts tags for positive tags" do
        expect(rubify_hash(@e.limits)).to eq({ '@foo' => 3, '@zap' => 5 })
      end

      it "matchs @foo @zap" do
        expect(@e.evaluate([tag('@foo'), tag('@zap')])).to be_true
      end
    end

    context "Parsing '@foo:3,~@bar', '@zap:5'" do
      before(:each) do
        @e = Gherkin::TagExpression.new([' @foo:3 , ~@bar ', ' @zap:5 '])
      end

      unless defined?(JRUBY_VERSION)
        it "splits and trim (ruby implementation detail)" do
          expect(@e.__send__(:ruby_expression)).to eq("(!vars['@bar']||vars['@foo'])&&(vars['@zap'])")
        end
      end

      it "has limits" do
        expect(rubify_hash(@e.limits)).to eq({ "@zap" => 5, "@foo" => 3 })
      end
    end

    context "Tag limits" do
      it "is counted for negative tags" do
        @e = Gherkin::TagExpression.new(['~@todo:3'])
        expect(rubify_hash(@e.limits)).to eq({ "@todo" => 3 })
      end

      it "is counted for positive tags" do
        @e = Gherkin::TagExpression.new(['@todo:3'])
        expect(rubify_hash(@e.limits)).to eq({ "@todo" => 3 })
      end

      it "raises an error for inconsistent limits" do
        expect do
          @e = Gherkin::TagExpression.new(['@todo:3', '~@todo:4'])
        end.to raise_error(/Inconsistent tag limits for @todo: 3 and 4/)
      end

      it "allows duplicate consistent limits" do
        @e = Gherkin::TagExpression.new(['@todo:3', '~@todo:3'])
        expect(rubify_hash(@e.limits)).to eq({ "@todo" => 3 })
      end
    end
  end
end
