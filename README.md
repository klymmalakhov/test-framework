## **REST API JAVA automated tests**

Main task:

- Pick 2 APIs from the list in https://www.thecocktaildb.com/api.php
- Write 2-3 tests for each API in framework of your choosing

##### Test scenarios

Test cases located at the test_scenarios folder

##### **HOW TO RUN**

```sh
$ mvn clean test -Dparallel=classes -DthreadCount=3 -DbaseURI=https://www.thecocktaildb.com/ -Dsuite=regression

```
- -Dparallel=classes - parallel tests by java classes
- -DthreadCount=3 - count of threads
- -DbaseURI=https://www.thecocktaildb.com/ - endpoint base url (proxy endpoint)
- -Dsuite=regression - test suite. There're possibility to run [regression, positive, negative] suits.

##### **HOW TO GET REPORT**

```sh
$ mvn allure:serve

```  



