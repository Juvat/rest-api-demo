# Demo project for REST API Tests

## Prerequsities:

* Java 11 or newer
* Maven
* Allure commandline (installation instrictions can be found here: https://docs.qameta.io/allure/#_installing_a_commandline)

## Launch all tests

To execute the entire test suite run the following command:
`mvn clean test`

If you want to run only the regression tests execute:
`mvn clean test -DtestGroup=regression`

If you want to run only the smoke tests execute:
`mvn clean test -DtestGroup=smoke`

## Generating test report

To generate a test report after the tests are finished navigate to `target` directory and there run:
`allure serve`

This will generate a test report and automatically open it in your default browser.
NOTE: Allure commandline must be installed to generate a report, see Prereqisities for details.
