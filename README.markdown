jitertools - java clone of pythons itertools package
====================================================

jitertools are a (sadly) not complete implementation of
[python's][python] [itertools][itertools] package.

using this implementation, the usage of lists and maps in java
can be a bit simplified. nevertheless, the [jitertools][ji] are
not the [holy grail][hgrail] so they can not solve all the
problems and quirks java collections/generics have. (e.g. zip don't
support distinct types :| )

to get an idea how to use [jitertools][ji], look at the unit tests
in the `src/test/java/de/downgra/jitertools` package. this should
give you a good overview and starting point.

to create a `.jar`, run

    > mvn package

on the command line and [maven 2][mvn] will then create an
`target/jitertools-<version>.jar` file, which you can use in your
project. or you install this package into your local maven repository
using

    > mvn install


functions
---------
at the moment, there are the following functions implemented:

* count
* cycle
* repeat
* filter
* filterfalse
* chain
* dropwhile
* map
* slice
* takewhile
* zip
* all
* any
* enumerate
* list
* ncycles
* no
* pad
* quantify
* range
* reduce
* tabulate
* consume
* nth


bug reporting
-------------

please report bugs [here](http://github.com/xfire/jitertools/issues)


license
-------
[GPLv2](http://www.gnu.org/licenses/gpl-2.0.html)



  [ji]:         http://github.com/xfire/jitertools/tree/master  "jitertools - github"
  [python]:     http://www.python.org/                          "python"
  [itertools]:  http://docs.python.org/library/itertools.html   "python itertools package"
  [hgrail]:     http://en.wikipedia.org/wiki/Monty_Python_and_the_Holy_Grail "_the_ holy grail"
  [mvn]:        http://maven.apache.org/                        "maven2"
