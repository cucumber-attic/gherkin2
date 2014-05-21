#encoding: utf-8
if defined?(JRUBY_VERSION)
  require 'spec_helper'
  include Gherkin::Rubify

  module Gherkin
    module Rubify
      describe "rubify" do
        before do
          @java_collection = [double("Java.java.util.ArrayList")]
          allow(@java_collection).to receive(:===).and_return(Java.java.util.Collection)
          allow(@java_collection).to receive(:line).and_return(15)
          @rubified_array = rubify(@java_collection)
        end

        it "keeps the line number attribute from Java object" do
          expect(@rubified_array).to respond_to(:line)
          expect(@rubified_array.line).to eq(15)
        end
      end
    end
  end
end
