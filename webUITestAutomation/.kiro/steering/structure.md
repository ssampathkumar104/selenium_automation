# Project Structure

## Workspace Layout

```
infor-cqa-libraries/
├── inforTestAutomation/     # Shared test automation library
│   ├── pom.xml
│   ├── src/main/java/
│   │   ├── annotations/     # Custom annotations (@IFrame, @Hidden, @PopUp, @RetryAnalyzer)
│   │   ├── dataUtils/       # Data providers (Excel, CSV, JSON, Properties, runtime data)
│   │   ├── pageFactory/     # Custom PageFactory with iframe-aware element location
│   │   ├── testBase/        # Core framework classes (BaseClass, BrowserFactory, Driver, etc.)
│   │   ├── testReportingAPI/ # REST API client for test result reporting + S3 uploads
│   │   └── MailCheck.java   # Email verification utility
│   ├── doc/                 # Generated Javadoc
│   └── target/              # Build output
│
└── sampleTestProject/       # Reference/consumer test project
    ├── pom.xml
    ├── src/main/java/
    │   ├── commons/         # Shared utility code for tests
    │   ├── config/          # config.properties, logos, credentials
    │   ├── constants/       # Constant values
    │   ├── contexts/        # Test context/state passing between tests
    │   ├── data/            # Test data files (Excel, CSV, JSON)
    │   ├── dataMapping/     # Data mapping utilities
    │   ├── desktop/         # Desktop automation (Sikuli) page objects
    │   ├── functions/       # Reusable workflow functions
    │   ├── pages/           # Page Object classes
    │   ├── plan/            # TestNG XML suite files
    │   ├── scripts/         # Test script classes
    │   └── workflow/        # End-to-end workflow orchestration
    ├── src/test/java/
    │   └── scripts/         # Additional test classes (e.g. retry logic)
    └── target/              # Build output
```

## Key Conventions

### Page Objects
- Extend `BasePageObject<T>` (self-referencing generic)
- Use `@FindBy` annotations for element locators
- Use `@IFrames` / `@IFrame` annotations when elements are inside iframes
- Dynamic locators use `%s` placeholder in `@FindBy` and resolve via `getDynamicElement()`

### Test Scripts
- Extend `BaseClass` (provides driver, screenshots, logging, utilities)
- Use `@Test`, `@BeforeMethod`, `@AfterMethod` from TestNG
- Naming convention: `TC<Product>_<Description>.java` (e.g. `TCLN_LNCreateApprovePurchaseOrder`)

### Test Plans (TestNG XML)
- Located in `src/main/java/plan/`
- Define parameters: `BASE_URL`, `USER_NAME`, `PASSWORD`, `browserName`, timeouts
- Support parallel execution via `parallel="tests"` and `thread-count`

### Configuration
- `config/config.properties` — runtime settings
- TestNG XML parameters — environment-specific values
- `.env` file — sensitive/local overrides (gitignored)

### Data Files
- Stored under `src/main/java/data/`
- Accessed via `TestData.setDataFolder()` and data utility classes

### Framework Entry Points
- `BaseClass.getDriver()` — access the current WebDriver
- `BaseClass.initElements(Class)` — instantiate a page object
- `BaseClass.screenshot(description)` — capture a screenshot
- `BaseClass.getParameter(key)` — read parameters from TestNG XML or config
- `BrowserFactory.initBrowser(browserName)` — create a browser session
