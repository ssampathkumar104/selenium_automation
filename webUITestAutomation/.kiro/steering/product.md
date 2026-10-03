# Product Overview

This workspace contains the **Infor CQA Test Automation** framework — a reusable Java library (`inforTestAutomation`) and a companion sample project (`sampleTestProject`) that demonstrates its usage.

## Purpose

The library provides a shared test automation infrastructure for Infor CloudSuite applications. It handles:

- Browser lifecycle management (local and remote/Docker Selenium Grid)
- Page Object Model base classes with custom iframe handling
- Test data utilities (Excel, CSV, JSON, Properties files)
- Screenshot capture and PDF report generation
- Test result reporting via a REST API with S3 artifact storage
- Sikuli-based image recognition for desktop automation
- Custom annotations for retry logic, iframe navigation, and popup handling

## Consumers

Teams across Infor CQA use this library as a Maven dependency (`com.infor.testautomation:inforTestAutomation`) to build test suites for products like LN, WMS, and other CloudSuite applications.

## Repository Layout

- `inforTestAutomation` — the shared framework library (published as a JAR)
- `sampleTestProject` — reference implementation showing how to consume the library
