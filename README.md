## **REST API JAVA autotests**

Main task:

* Pick 2 APIs from the list in https://www.thecocktaildb.com/api.php

* Write 2-3 tests for each API in framework of your choosing

##### **HOW TO RUN**

```sh
$ mvn clean test -Dparallel=classes -DthreadCount=10 -DbaseURI=https://www.thecocktaildb.com/ -Dsuite=regression

```
- -Dparallel=classes - parallel tests by java classes
- -DthreadCount=10 - count of threads
- -DbaseURI=https://www.thecocktaildb.com/ - endpoint base url (proxy endpoint)
- -Dsuite=regression - test suite

##### **HOW TO GET REPORT**

```sh
$ mvn allure:serve

```  



