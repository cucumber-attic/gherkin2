{ "name" : "Feature Text\nIn order to test multiline forms",
  "tags" : [
    "@tag1",
    "@tag2"
  ],
  "background" : {
    "name" : "", 
    "steps" : [
      { "name" : "this is a background step" },
      { "name" : "this is another one",
        "keyword" : "When ",
        "line" : 412 }
    ]
  },
  "elements" : [
    { "type" : "Scenario Outline",
      "name" : "An Scenario Outline",
      "tags" : [ "@foo" ],
      "steps" : [
        { "name" : "A step with a table",
          "table" : [
            {"cells":
              [ { "text":"a" },
                { "text":"row" },
                { "text":"for" },
                { "text":"a"   },
                { "text":"step"}
              ]
            }
          ]
        }
      ],
      "examples" : [
        { "name" : "Sweet Example", 
          "table" : [
            {"cells" :
              [ { "text":"Fill" },
                { "text":"In"   }]},
            {"cells" :
              [ { "text":"The" },
                { "text": "Blanks" }]}
          ],
          "tags" : [ "@exampletag" ]
        }
      ]
    },
    { "type" : "Scenario",
      "name" : "Reading a Scenario",
      "tags" : [
        "@tag3",
        "@tag4"
      ],
      "steps" : [
        { "name" : "there is a step" },
        { "name" : "not another step" }
      ]
    },
    { "type" : "Scenario",
      "name" : "Reading a second scenario\nWith two lines of text",
      "tags" : [ "@tag3" ],
      "steps" : [
        { "name" : "a third step with a table",
          "table" : [
            { "cells" :
              [ { "text":"a" },
                { "text":"b" }]},
            { "cells" :
              [ { "text":"c" },
                { "text":"d" }]},
            { "cells" :
              [ { "text":"e" },
                { "text":"f" }]}
          ]
        },
        { "name" : "I am still testing things",
          "table" : [
            { "cells" :
              [ { "text":"g" },
                { "text":"h" }]},
            { "cells" :
              [ { "text":"e" },
                { "text":"r" }]},
            { "cells" :
              [ { "text":"k" },
                { "text":"i" }]},
            { "cells" :
              [ { "text":"n" },
                { "text":"" }]}
          ]
        },
        { "name" : "I am done testing these tables" },
        { "name" : "I am happy" }
      ]
    },
    { "type" : "Scenario",
     "name" : "Hammerzeit", 
      "steps" : [
        { "name" : "All work and no play", 
          "py_string" : "Makes Homer something something\nAnd something else" },
        { "name" : "crazy" }
      ]
    }
  ]
}

