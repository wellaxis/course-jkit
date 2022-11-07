#                        Utils Application

Goal of project
---------------

> It is necessary to develop the java utilities features
for other modules [Utils][link].
***

Structure
---------

- utils [root application directory]
    - bin [folder contains batch files]
    - log [folder contains module logs]
    - res [folder contains results of run]
    - src [folder contains source files]
    - configuration and documentation files
***

Installation
------------

It's required to set up application environment variable:
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

Application is currently consists of two subdirectories
    - `main` (source classes and resources)
    - `test` (test classes and resources)
Main directory contains all classes to process application functionality, test directory contains all classes to process all unit, functional and integration tests.

Todos
-----
* To develop back-end features
***

[link]: <https://en.wikipedia.org/wiki/utils>
