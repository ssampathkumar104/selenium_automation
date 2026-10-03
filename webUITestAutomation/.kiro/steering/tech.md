# Tech Stack

## Language & Platform

- Java 17 (source and target)
- Maven build system

## Core Frameworks & Libraries

| Category | Library | Version |
|----------|---------|---------|
| Test runner | TestNG | 7.9.0 |
| Browser automation | Selenium WebDriver | 4.7.2 |
| Driver management | WebDriverManager | 5.7.0 |
| Assertions | AssertJ | 3.21.0 |
| API testing | REST Assured | 3.0.7 |
| Image-based automation | SikuliX | 2.0.4 |
| Reporting | ExtentReports | 5.0.9 |
| Data (Excel) | Apache POI | 5.2.2 |
| Data (PDF) | Apache PDFBox | 2.0.30 |
| HTTP client | OkHttp | 3.14.0 |
| REST client (JAX-RS) | Jersey | 3.1.3 |
| JSON | json-simple, Jackson, JsonPath | — |
| AWS | AWS SDK v2 (S3) | 2.20.162 |
| Logging | Log4j 2 | 2.24.1 |
| Boilerplate | Lombok | 1.18.22 |

## Build Commands

```bash
# Compile the library
mvn clean compile

# Package the library JAR
mvn clean package

# Package fat JAR with all dependencies (for distribution)
mvn clean package -PDistribute

# Deploy to internal Nexus repository
mvn clean deploy

# Run tests in the sample project (specify TEST_PLAN)
mvn clean test -DTEST_PLAN=SampleSuite.xml

# Package sample project as executable fat JAR
mvn clean package -PFatJar
```

## Key Maven Profiles

- `Distribute` (library) — shades all deps into a single JAR
- `FatJar` (sample project) — creates an executable JAR with `com.runners.testNGRunner` as main class
- `LightJar` (sample project) — shades only Infor dependencies

## Test Execution

Tests are driven by **TestNG XML suite files** located in `src/main/java/plan/`. The suite file is specified via the `TEST_PLAN` Maven property:

```bash
mvn test -DTEST_PLAN=<filename>.xml
```

Parameters like `BASE_URL`, `USER_NAME`, `PASSWORD`, `browserName`, timeouts, etc. are defined in the suite XML.

## Docker / Selenium Grid

The project supports remote execution on Docker-based Selenium grids. Docker compose files (`docker-zalenium-compose.yml`, `dockerCompose2_new.yml`) and helper batch scripts are provided in the sample project.
