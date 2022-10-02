#                        Shell Application

Goal of project
---------------

> It is necessary to develop the java CLI command features
 for other modules [Shell][link].
***

Structure
---------

- shell [root application directory]
    - bin [folder contains batch files]
    - log [folder contains module logs]
    - res [folder contains results of run]
    - src [folder contains source files]
    - configuration and documentation files
***

Installation
------------

It's required to setup application environment variable:
```sh
$ JAVA_HOME = [path]\java\jdk{version}
```
***

Framework
---------

Application uses a number of open source frameworks:
* [`slf4j`, `log4j`] - for application logging
* [`lombok`] - to generate class environment
* etc
***

Structure
---------

Application is currently consists of two sub-directories
    - `main` (source classes and resources)
    - `test` (test classes and resources)
Main directory contains all classes to process application functionality, test directory contains all classes to process all unit, functional and integration tests. 

[shell]: <https://en.wikipedia.org/wiki/shell>
