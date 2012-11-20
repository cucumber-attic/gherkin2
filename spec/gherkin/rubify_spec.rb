#encoding: utf-8
if defined?(JRUBY_VERSION)
  require 'spec_helper'
  include Gherkin::Rubify

  module Gherkin
    module Rubify
      describe "rubify" do
        before do
          @java_collection = [mock("Java.java.util.ArrayList")]
          @java_collection.stub(:===).and_return(Java.java.util.Collection)
          @java_collection.stub(:line).and_return(15)
          @rubified_array = rubify(@java_collection)
        end

        it "should keep the line number attribute from Java object" do
          @rubified_array.should respond_to(:line)
          @rubified_array.line.should == 15
        end
      end
    end
  end
end