class StepsBuilder
  def initialize
    @steps = []
  end

  def step(content)
    @steps << "Given #{content}"
  end

  def to_s
    @steps.join("\n")
  end
end

class FeatureBuilder
  def initialize(name, &block)
    @name = name
    @scenarios = {}
    block.call(self)
  end

  def scenario(name, &block)
    @scenarios[name] = StepsBuilder.new
    block.call(@scenarios[name])
  end

  def to_s
    str = "Feature: #{@name}\n"
    @scenarios.each do |scenario, steps|
      str += "\n"
      str += "Scenario: #{scenario}\n"
      str += steps.to_s
      str += "\n"
    end
    str
  end
end

