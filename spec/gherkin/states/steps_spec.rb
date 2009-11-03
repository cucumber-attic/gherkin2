shared_examples_for "a section containing steps" do
  it "should not allow a py_string unless preceded by a step" do
    @state.should_not allow(:py_string)
  end

  it "should not allow a table unless preceded by a step" do
    @state.should_not allow(:table)
  end

  describe "following a step" do
    before do
      @state.step
    end

    it "should not allow a py_string to follow a table" do
      @state.table
      @state.should_not allow(:py_string)
    end

    it "should not allow a table to follow a py_string" do
      @state.py_string
      @state.should_not allow(:table)
    end

    it "should allow py_string to follow a step" do
      @state.should allow(:py_string)
    end

    it "should allow table to follow a step" do
      @state.should allow(:table)
    end
  end
end
