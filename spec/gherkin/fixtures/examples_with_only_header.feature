Feature: An example with only a header
  See [this thread](http://groups.google.com/group/cukes/browse_thread/thread/3e55777ee29c445c)

  Scenario Outline: 
    When I do <foo>
    Then something

    Examples: A
      | page |
      | Golf | 

    @failing
    Examples: B
      | Scottish Football |
