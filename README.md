# Demo project for REST API Tests

## Prerequisites:

* Java 11 or newer
* Maven
* Allure commandline (installation instructions can be found here: https://docs.qameta.io/allure/#_installing_a_commandline)

## Launch all tests

To execute the entire test suite run the following command:
`mvn clean test`

If you want to run only the regression tests execute:
`mvn clean test -DtestGroup=regression`

If you want to run only the smoke tests execute:
`mvn clean test -DtestGroup=smoke`

## Setting parameters dynamically

The tests show two approaches offered by TestNG when it comes to parametrized tests. One is DataProvider which uses an `xml` file to store the test's parameters. The other is using `@Parameters` annotation.

Test named `shouldReturnBeersAboveGivenAbv` can be launched with dynamically set _Abv_ parameter. This can be done by running the following command (i.e.: Abv = 7):
`mvn clean test -Dabv="7" -Dtest=BeersTest`

## Parallel execution

By default tests are running in parallel. This can be changed by passing `-Dthreads=n` (where n is desired number of threads) when launching the tests.
Also, parallel stratedy can be changed by passing `-DparallelMethod=tests` - available options: tests|classes|instances|methods(default). 

## Generating test report

To generate a test report after the tests are finished navigate to `target` directory and there run:
`allure serve`

This will generate a test report and automatically open it in your default browser.

NOTE: Allure commandline must be installed to generate a report, see Prerequisites for details.
