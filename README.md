# Selenium Automation

A Java-based Selenium test automation project focused on web UI testing. This repository contains a reusable automation framework, sample implementation projects, and supporting documentation for building and running browser-based tests efficiently.

## Project Overview

This project is designed for automated validation of web applications using Selenium WebDriver and Java. It includes examples of test automation structure, reusable utilities, and support for integration with common testing and reporting libraries.

The repository includes:
- a reusable Selenium test automation framework
- sample Maven-based test projects
- browser automation utilities
- reporting and data-handling capabilities
- documentation for framework usage and test execution

## Repository Structure

- `webUITestAutomation/` - core Java automation framework and supporting files
- `sampleTestProject/` - example test project using the framework
- `README.md` - project overview and instructions
- `LICENSE` - MIT license for the project

## Tech Stack

- Java 17
- Maven
- Selenium WebDriver
- TestNG
- JUnit
- WebDriver Manager
- ExtentReports
- Apache POI
- PDF utilities
- AWS SDK integrations

## Prerequisites

Before running the project, ensure the following are installed:

- Java 17 or later
- Maven 3.9+
- Git
- A supported browser such as Chrome or Firefox
- Optional: Docker for Selenium Grid-based execution

## Getting Started

### Clone the repository

```bash
git clone https://github.com/ssampathkumar104/selenium_automation.git
cd selenium_automation
```

### Run the core framework project

```bash
cd webUITestAutomation
mvn clean test
```

### Run the sample project

```bash
cd sampleTestProject
mvn clean test
```

## Documentation

The repository contains developer guides and testing references to help you understand and extend the framework:

- `webUITestAutomation/Selenium_Framework_Developer_Guide.md`
- `webUITestAutomation/Selenium_TestAutomation_Handbook.md`

## Notes

This project is intended for web UI automation, test execution, and learning. It can be extended with additional page objects, utility classes, test cases, and CI/CD integration depending on your automation needs.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.
