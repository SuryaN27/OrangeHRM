# Seleniumfraework

A Maven-based Selenium TestNG automation framework for testing OrangeHRM workflows using the Page Object Model pattern.

The framework currently covers login scenarios, dashboard/PIM navigation, employee creation, system user management, personal information updates, contact details, and emergency contact details. Test data and browser configuration are read from `src/main/resources/data.properties`.

## Technologies and Dependencies

- Java
- Maven
- Selenium Java `4.39.0`
- TestNG `7.11.0`
- Maven Surefire Plugin `3.0.0`
- Allure TestNG `2.31.0`
- Log4j `2.23.1`
- AssertJ `3.27.7`, declared in `pom.xml`
- Apache POI `5.2.3`, declared in `pom.xml`

The current test assertions use TestNG `Assert`. AssertJ and Apache POI are present as Maven dependencies, but there is no current project code using them.

## Framework Architecture

This project follows a layered Selenium automation structure:

```text
testng.xml
  -> TestNG test classes
      -> Base test setup and teardown
          -> DriverManager
              -> Browser driver initialization
      -> Page classes
          -> Selenium locators and page actions
              -> Utilities
                  -> PropertyReader
                  -> waithelpers
      -> Listeners
          -> Retry handling
          -> Failure screenshots
          -> Allure screenshot attachments
```

## Project Structure

```text
Seleniumfraework
|-- pom.xml
|-- testng.xml
|-- README.md
|-- src
    |-- main
    |   |-- java
    |   |   |-- org
    |   |       |-- example
    |   |           |-- base
    |   |           |   |-- CommonToAll.java
    |   |           |-- driver
    |   |           |   |-- DriverManager.java
    |   |           |-- pages
    |   |           |   |-- Myinfo.java
    |   |           |   |-- Orangedashboard.java
    |   |           |   |-- Orangelogin.java
    |   |           |   |-- Usermanagement.java
    |   |           |-- utils
    |   |               |-- PropertyReader.java
    |   |               |-- waithelpers.java
    |   |-- resources
    |       |-- data.properties
    |       |-- log4j2.xml
    |-- test
        |-- java
            |-- org
                |-- example
                    |-- basetest
                    |   |-- CommonToAllTest.java
                    |-- listeners
                    |   |-- RetryAnalyzer.java
                    |   |-- RetryListener.java
                    |   |-- ScreenshotListener.java
                    |-- tests
                        |-- Adduser.java
                        |-- Login.java
                        |-- My_info.java
```

## Important Files

### `pom.xml`

The Maven project file defines dependencies for Selenium, TestNG, Allure TestNG, AssertJ, Log4j, and Apache POI. The project code currently uses Selenium, TestNG, Allure attachment APIs, and logging APIs.

The Maven Surefire Plugin is configured to run tests from:

```xml
<suiteXmlFile>testng.xml</suiteXmlFile>
```

### `testng.xml`

The TestNG suite is named `All Test Suite` and runs the following test classes:

- `org.example.tests.Login`
- `org.example.tests.Adduser`
- `org.example.tests.My_info`

The suite also registers these listeners:

- `org.example.listeners.ScreenshotListener`
- `org.example.listeners.RetryListener`

### `data.properties`

This file stores test configuration and test data, including:

- Application URLs
- Browser name
- OrangeHRM credentials
- Expected messages
- Employee data
- User management data
- Personal details
- Contact details
- Emergency contact details

The current browser value is:

```properties
browser=chrome
```

### `log4j2.xml`

Log4j is configured with:

- Console logging
- File logging to `logs/test.log`
- Root log level `info`

## Page Object Model

The framework uses page classes under `src/main/java/org/example/pages`.

Each page class stores Selenium locators as `By` variables and exposes methods that perform actions on that page.

### `Orangelogin.java`

Handles OrangeHRM login page actions:

- Valid login
- Invalid login
- Empty login validation
- Forgot password text validation

### `Orangedashboard.java`

Handles dashboard and PIM-related actions:

- Verify PIM page/dashboard text
- Add employee from the PIM section

### `Usermanagement.java`

Handles Admin user management workflows:

- Add user
- Delete user
- Edit user
- Search system users
- Reset system user search

### `Myinfo.java`

Handles My Info workflows:

- Add/update personal details
- Add/update contact details
- Add emergency contact details

## Driver Management

Driver handling is implemented in:

```text
src/main/java/org/example/driver/DriverManager.java
```

`DriverManager` uses `ThreadLocal<WebDriver>` to store the active driver instance. The browser is selected from `data.properties`.

Supported browser values in the current code are:

- `chrome`
- `edge`
- `firefox`

Driver lifecycle methods:

- `init()` creates the browser instance.
- `getDriver()` returns the current thread's driver.
- `setDriver()` stores the driver.
- `down()` quits the browser and unloads the driver.
- `unload()` removes the driver from `ThreadLocal`.

## Utilities

### `PropertyReader.java`

Reads key-value data from:

```text
src/main/resources/data.properties
```

Example usage:

```java
PropertyReader.readkey("orange_url")
```

### `waithelpers.java`

Provides wait helper methods for:

- JVM sleep
- Implicit wait
- Explicit visibility wait
- Text wait
- Fluent wait

## Test Classes

All test classes are under:

```text
src/test/java/org/example/tests
```

### `Login.java`

Contains tests for:

- Valid login
- Invalid login
- Empty submit validation
- Forgot password text validation

### `Adduser.java`

Contains tests for:

- Adding an employee
- Adding a system user
- Deleting a system user
- Editing a user, currently disabled
- Searching system users
- Resetting system user search

### `My_info.java`

Contains tests for:

- Adding/updating personal details
- Adding/updating contact details
- Adding emergency contact details

## Base Test Flow

Base setup and teardown are handled by:

```text
src/test/java/org/example/basetest/CommonToAllTest.java
```

Execution lifecycle:

1. Before each test method, `@BeforeMethod` calls `DriverManager.init()`.
2. The test creates page objects using `DriverManager.getDriver()`.
3. Page methods interact with the browser.
4. Assertions are handled in the test class.
5. After each test method, `@AfterMethod` calls `DriverManager.down()`.

## Execution Flow

When tests are executed with Maven:

1. Maven Surefire reads `testng.xml`.
2. TestNG loads the configured listeners.
3. TestNG runs the classes listed in `testng.xml`.
4. `CommonToAllTest` starts a browser before each test method.
5. Test methods instantiate page classes.
6. Page classes use Selenium WebDriver to interact with OrangeHRM.
7. Test data is read from `data.properties`.
8. Waits are handled through `waithelpers`.
9. TestNG assertions validate expected results.
10. On failure, `ScreenshotListener` captures a screenshot.
11. After each method, the browser is closed through `DriverManager.down()`.

## Setup and Prerequisites

Install the following before running the project:

- Java JDK
- Maven
- Chrome, Edge, or Firefox browser
- Git, if cloning or version-controlling the project

No browser driver executable paths are configured in the project files. `DriverManager` directly creates `ChromeDriver`, `EdgeDriver`, or `FirefoxDriver` based on the `browser` value in `data.properties`.

Verify Java and Maven:

```bash
java -version
mvn -version
```

## Configuration

Update test data and browser settings in:

```text
src/main/resources/data.properties
```

Example:

```properties
browser=chrome
orange_url=https://opensource-demo.orangehrmlive.com/web/index.php/auth/login
ohr_username=admin
ohr_password=admin123
```

## How to Run Tests

Run all tests configured in `testng.xml`:

```bash
mvn test
```

Run TestNG suite directly from an IDE by selecting:

```text
testng.xml
```

The Maven Surefire Plugin is already configured to use `testng.xml`, so no extra Maven profile is required in the current project.

## Reporting

The project includes Allure TestNG as a dependency:

```text
io.qameta.allure:allure-testng:2.31.0
```

`ScreenshotListener` attaches failure screenshots to Allure using `Allure.addAttachment()`.

TestNG default reports are generated by Maven Surefire after test execution.

The current `pom.xml` includes the Allure TestNG dependency, but it does not configure an Allure Maven plugin.

## Screenshots

Failure screenshots are handled by:

```text
src/test/java/org/example/listeners/ScreenshotListener.java
```

When a test fails:

- A screenshot is captured from the active WebDriver.
- The image is saved under `failure_screenshots/`.
- A screenshot link is added to the TestNG report.
- The screenshot is attached to Allure.

Screenshot file names include the failed test method name and timestamp.

## Logging

Logging is configured in:

```text
src/main/resources/log4j2.xml
```

Logs are written to:

```text
logs/test.log
```

Logs are also printed to the console.

## Retry Mechanism

Retry support is implemented with:

- `RetryAnalyzer.java`
- `RetryListener.java`

`RetryAnalyzer` retries failed tests up to `3` times. `RetryListener` applies the retry analyzer through TestNG annotation transformation.

## Git Usage

Common Git commands for this project:

```bash
git status
git add README.md
git commit -m "Add project README"
git log --oneline
```

To inspect local changes before committing:

```bash
git diff
```

To push after committing:

```bash
git push
```

## Notes

- The suite execution order is controlled by `testng.xml`.
- Test method ordering is controlled with TestNG `priority` values.
- Browser selection comes from `data.properties`.
- Page classes directly use locators, waits, and property values.
- The framework currently stores test data in a properties file.
- `CommonToAll.java` exists under `src/main/java/org/example/base`, while the active test setup/teardown class is `CommonToAllTest.java` under `src/test/java/org/example/basetest`.
