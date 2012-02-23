#JBWAPI

###Introduction

I decided to fork the [JNIBWAPI](http://code.google.com/p/jnibwapi/) after working with it during a graduate school course. I found myself
both exposing new methods from the C++ BWAPI and also adding and improving the existing code base. The API works, but
there are a few reasons I forked it instead of asking for commit access to the original project.

* The original code did not use a build system and the first thing I changed was I setup a Maven project. I didn't want
to force this change on the original authors.
* I prefer github's style of pull requests for sharing changes to a project.
* I would much rather use git instead of svn.


###Usage
1. You need Starcraft BroodWar installed and patched to the lastest version 1.16.1 either download the patch or connect to battle.net
2. Download the current version of BWAPI, 3.7.2 at the time of writing, from [here](http://code.google.com/p/bwapi/).
3. Read the instructions in the BWAPI README.
4. By now if you followed step 3 you should have installed chaoslauncher and copied files from the BWAPI folder to
various places on your computer. You also should have edited your bwapi.ini file.
5. If you want to run the ExampleAIClient:
	1. Run mvn package
	2. From the project folder run java -Djava.library.path=. -cp eisbot-0.2.1.jar eisbot.proxy.ExampleAIClient
	3. Now use chaoslauncher to launch StarCraft. 
