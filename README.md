JBWAPI
-----

Introduction
------------

I decided to fork the [JNIBWAPI](http://code.google.com/p/jnibwapi/) after working with it during a graduate school course. I found myself
both exposing new methods from the C++ BWAPI and also adding and improving the existing code base. The API works, but
there are a few reasons I forked it instead of asking for commit access to the original project.

* The original code did not use a build system and the first thing I changed was I setup a Maven project. I didn't want
to force this change on the original authors.
* I prefer github's style of pull requests for sharing changes to a project.
* I would much rather use git instead of svn.
