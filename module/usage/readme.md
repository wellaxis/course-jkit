#                        Usage Application

Goal of project
---------------

> It is necessary to implement the Java language features and concepts
 for learning purposes & to solve various tasks into usage module [Usage][link].
***

Structure
---------

- usage [root application directory]
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
Main directory contains all classes to process application functionality.
Test directory contains all classes to process all unit, functional and integration tests. 

Todos
-----
* To reprocess all chapters into the book
* To implement the new java features
***

[link]: <https://en.wikipedia.org/wiki/usage>
