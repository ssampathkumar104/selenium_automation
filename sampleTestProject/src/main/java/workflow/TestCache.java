package workflow;

import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
//import com.gtnexus.testautomation.runtime.bo.Credential;
//import com.gtnexus.testautomation.runtime.bo.FileAttachment;
//import com.gtnexus.testautomation.runtime.bo.Organization;
//import com.gtnexus.testautomation.runtime.bo.PageTrace;
//import com.gtnexus.testautomation.runtime.core.api.BrowserProcess;
//import com.gtnexus.testautomation.runtime.core.api.desktop.DesktopScreen;
//import com.gtnexus.testautomation.runtime.core.persistence.Procedure;
//import com.gtnexus.testautomation.runtime.core.util.Log;
//import com.gtnexus.testautomation.runtime.core.util.PlatformLogFactory;
//import com.gtnexus.testautomation.runtime.drivers.DriverProvider;
//import com.gtnexus.testautomation.runtime.drivers.WindowsAppiumDriver;
//import com.gtnexus.testautomation.runtime.mobile.core.MobileScriptContext;
//import com.gtnexus.testautomation.runtime.mobile.driver.MobileDriverProvider;
//import com.gtnexus.testautomation.runtime.mobile.enums.ApplicationType;
//import com.gtnexus.testautomation.runtime.mobile.enums.DevicePlatform;
//import io.appium.java_client.MobileDriver;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.io.FileUtils;
//import org.apache.commons.lang.StringUtils;
//import org.apache.http.client.CookieStore;
//import org.apache.logging.log4j.core.Logger;
//import org.assertj.core.api.SoftAssertions;
//import org.grep4j.core.model.Profile;
//import org.grep4j.core.model.ServerDetails;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;

public class TestCache {
	
	  public static boolean isSummerizedLog = false;
	  
//	  private static final Log log = PlatformLogFactory.getLogger(TestCache.class);
	  
	  public static final String UUID = "__test.exec.uuid";
	  
	  public static final String NAME = "__test.exec.name";
	  
	  public static final String TYPE = "__test.exec.type";
	  
	  public static final String DRYRUN = "__test.exec.dry.run.mode";
	  
	  public static final String SCREENSHOTS_ENABLED = "__test.exec.screenshots.enabled";
	  
	  public static final String SCRIPT_NAME = "__test.exec.script.name";
	  
	  public static final String SCRIPT_UUID = "__test.exec.script.uuid";
	  
	  public static final String TEST_EXECUTION_PLAN = "__test.exec.tep";
	  
	  public static final String TEST_EXECUTION_PLAN_FULL_NAME = "__test.exec.tep.full.name";
	  
	  public static final String TEST_EXECUTION_PLAN_UID = "__test.exec.tep.uuid";
	  
	  public static final String STATUS = "__test.exec.status";
	  
	  public static final String ENVIRONMENT = "__test.target.env";
	  
	  public static final String BASE_URL = "__test.base.url";
	  
	  public static final String LOGGER = "__test.logger";
	  
	  public static final String USER_LOGGER = "__test.user.logger";
	  
	  public static final String SELENIUM_HUB_URL = "__selenium.hub.url";
	  
	  public static final String ENHANCED_WEB_DRIVER = "__enhanced.web.driver";
	  
	  public static final String WEB_DRIVER_PROVIDER = "__web.driver.provider";
	  
	  public static final String WEB_DRIVER_ACTIVE = "__web.driver.active";
	  
	  public static final String DESIRED_CAPABILITIES = "__desired.capabilities";
	  
	  public static final String BENCHMARK_CAPABLE = "__benchmark.capable";
	  
	  public static final String PROFILE_FIXTURE = "__profile.fixture";
	  
	  public static final String STOPWATCH = "__stopwatch";
	  
	  public static final String BUILD_NO = "__build.no";
	  
	  public static final String BUILD_NO_RAW_RSS = "__build.no.raw.rss";
	  
	  public static final String ATTACHMENTS = "__test.attachments";
	  
	  public static final String TEST_CONTEXT = "__test.itestcontext";
	  
	  public static final String GTN_ORG = "__organization.gtn";
	  
	  public static final String PAGE_TRACES = "__test.page.trace.list";
	  
	  public static final String WINDOW_STACK = "__test.browser.window.stack";
	  
	  public static final String DATA_FOLDER = "__test.data.folder";
	  
	  public static final String FTE_ID = "__test.env.fte.id";
	  
	  public static final String ACTION_DELAY = "__test.action_delay";
	  
	  public static final String REASON = "__test.reason";
	  
	  public static final String REASON_URL = "__test.reason.url";
	  
	  public static final String REASON_LINE = "__test.reason.line";
	  
	  public static final String USER_CREDENTIALS = "__user.credentials";
	  
	  public static final String BINARY_FILE_PATH = "__binary.filePath";
	  
	  public static final String SCRIPT_CONTEXT = "__script.context";
	  
	  public static final String IMPLICITLY_WAIT_TIME = "__test.implicitlyWaitTime";
	  
	  public static final String SCRIPT_TIMEOUT = "__test.scriptTimeout";
	  
	  public static final String PAGELOAD_TIMEOUT = "__test.pageLoadTimeout";
	  
	  public static final String TEST_MANAGER_ID = "__test.manager.id";
	  
	  public static final String PARALLEL_EXECUTION_METADATA = "_agent.execution.parallel";
	  
	  public static final String TEMP_DIR = "__test.temp.dir";
	  
	  public static final String OVERRIDE_PRODUCT_LABEL = "__override.product.label";
	  
	  public static final String OVERRIDE_PRODUCT_TAG = "__override.product.tag";
	  
	  public static final String OVERRIDE_ALL_CONTEXT_VERSION = "__override.all.context.version";
	  
	  public static final String SCRIPT_EXECUTOR = "__script.executor";
	  
	  public static final String SCRIPT_EXECUTOR_PRETTY_NAME = "__script.executor.pretty.name";
	  
	  public static final String ORGANIZATION = "__organization";
	  
	  public static final String ORGANIZATION_ID = "__organization.id";
	  
	  public static final String SOFT_ASSERT = "__soft.assert";
	  
	  public static final String SDKAPI_AUTH_HEADERS = "__api.auth.headers";
	  
	  public static final String SDKAPI_BASE_URL = "__api.base.url";
	  
	  public static final String CACHE_BSA_CONTEXT = "__bsa.context";
	  
	  public static final String DESKTOP_APP_DRIVER_MAP = "__test.desktop.app.driver.map";
	  
	  public static final String ON_CLOSE_SUBSCRIBERS = "__test.on.close.subscribers";
	  
	  public static final String CURRENT_DESKTOP_APP = "__test.current.desktop.app.path";
	  
	  public static final String CURRENT_DESKTOP_WINDOW_HANDLE = "__test.current.desktop.window.handle";
	  
	  public static final String DESKTOP_APP_ELEMENT_MAP = "__test.desktop.app.element.map";
	  
	  public static final String TEST_CASE_ID = "_test.case.id";
	  
	  public static final String CURRENT_DESKTOP_TEST_PROVIDER = "__test.current.desktop.provider";
	  
	  public static final String APPIUM_HUB_URL = "__appium.hub.url";
	  
	  public static final String MOBILE_DRIVER_PROVIDER = "__mobile.driver.provider";
	  
	  public static final String MOBILE_DRIVER_ACTIVE = "__mobile.driver.active";
	  
	  public static final String MOBILE_DRIVER = "__mobile.driver";
	  
	  public static final String DESIRED_MOBILE_CAPABILITIES = "__mobile.desired.capabilities";
	  
	  public static final String MOBILE_SCRIPT_CONTEXT = "__mobile.script.context";
	  
	  public static final String MOBILE_SCREENSHOTS_ENABLED = "__mobile.screenshots.enabled";
	  
	  public static final String MOBILE_PLATFORM = "__mobile.platform";
	  
	  public static final String MOBILE_APPLICATION_TYPE = "__mobile.application.type";
	  
	  public static final String MOBILE_DEVICE_DETAILS = "__mobile.device.details";
	  
	  public static final String MOBILE_APPLICATION_KEY = "__mobile.application.key";
	  
	  public static final String MOBILE_DEVICE_POOL_KEY = "__mobile.device.pool.key";
	  
	  public static final String MOBILE_DEVICE_POOL_DIRTY = "__mobile.device.pool.dirty";
	  
	  public static final String MOBILE_APPLICATION_DIRTY = "__mobile.application.dirty";
	  
	  public static final String FRAME_TO_IGNORE = "__frame_to_ignore";
	  
	  public static final String CURRENT_DESKTOP_SCREEN_CLASS = "__test.current.desktop.screen";
	  
	  public static final String COOKIE_STORE = "_test.current.http.cookieStore";
	  
	  public static final String TEST_EXECUTION_START_TIME = "__test.execution.start.time";
	  
	  public static final String TEST_EXECUTED_AGENT_INSTANCE_ID = "__test.executed.agent.instance.id";
	  
	  protected static final ThreadLocal<Map<String, Object>> cache = new ThreadLocal<>();
	  
	  public static final Set<String> OPENEDAPPLICATIONS = new HashSet<>();
	  
	  
	  private static final Supplier<Map<String, WebElement>> NEW_ELEMENT_MAP_SUPPLIER = new Supplier<Map<String, WebElement>>() {
	      public Map<String, WebElement> get() {
	        return Maps.newHashMap();
	      }
	    };
	  
	  public static Map<String, WebElement> getDesktopElementMap() {
	    return getVal("__test.desktop.app.element.map", NEW_ELEMENT_MAP_SUPPLIER);
	  }
	  
//	  protected static List<Procedure> getOnCloseSubscribers() {
//	    return getVal("__test.on.close.subscribers", NEW_ARRAY_LIST_SUPPLIER);
//	  }
//	  
//	  public static void addOnCloseSubscriber(Procedure procedureToRunOnEndOfScriptExecution) {
//	    getOnCloseSubscribers().add(procedureToRunOnEndOfScriptExecution);
//	  }
//	  
	  public static boolean isDriverActive() {
	    return ((Boolean)((get("__web.driver.active") != null) ? get("__web.driver.active") : Boolean.valueOf(Boolean.FALSE.booleanValue()))).booleanValue();
	  }
	  
	  public static Long getImplicitlyWaitTime() {
	    return get("__test.implicitlyWaitTime");
	  }
	  
	  public static Long getScriptTimeout() {
	    return get("__test.scriptTimeout");
	  }
	  
	  public static Long getPageLoadTimeout() {
	    return get("__test.pageLoadTimeout");
	  }
	  
//	  public static Path getTestTempDir() {
//	    return getVal("__test.temp.dir", TEST_EXEC_TEMP_DIR_SUPPLIER);
//	  }
	  
//	  public static Logger getLogger() {
//	    return getVal("__test.logger", TEST_EXEC_LOGGER_SUPPLIER);
//	  }
//	  
//	  public static Logger getUserLogger() {
//	    return getVal("__test.user.logger", TEST_EXEC_USER_LOGGER_SUPPLIER);
//	  }
	  
	  private static void setDriverActive() {
	    set("__web.driver.active", Boolean.TRUE);
	  }
	  
	  public static Integer getDelay() {
	    return get("__test.action_delay");
	  }
	  
	  protected static void setDelay(Integer delay) {
	    set("__test.action_delay", delay);
	  }
	  
	  public static String getFteid() {
	    return get("__test.env.fte.id");
	  }
	  
	  protected static void setFteid(String fteid) {
	    set("__test.env.fte.id", fteid);
	  }
	  
	  public static Optional<String> getDataFolder() {
	    Optional<String> df = get("__test.data.folder");
	    if (df == null)
	      return Optional.absent(); 
	    return df;
	  }
	  
	  public static Optional<Path> getBinaryFilePath() {
	    Path bfp = get("__binary.filePath");
	    return Optional.fromNullable(bfp);
	  }
	  
//	  public static ScriptContext getScriptContext() {
//	    return get("__script.context");
//	  }
//	  
//	  public static CookieStore getCookieStore() {
//	    return get("_test.current.http.cookieStore");
//	  }
	  
//	  public static void setCookieStore(CookieStore cookieStore) {
//	    set("_test.current.http.cookieStore", cookieStore);
//	  }
	  
	  public static void setDataFolder(String folder) {
	    if (null != get("__test.data.folder") && getDataFolder().isPresent())
	      throw new IllegalStateException("You Cannot override Data Folder at Runtime"); 
	    set("__test.data.folder", Optional.fromNullable(folder));
	  }
	  
	  public static URL getSeleniumHubURL() {
	    String seleniumHubUrl = null;
	    try {
	      seleniumHubUrl = get("__selenium.hub.url");
	      return new URL(seleniumHubUrl);
	    } catch (MalformedURLException e) {
	      String logMessage = "[method = getSeleniumHubURL] [message= Selenium Hub Url is invalid. received= " + seleniumHubUrl + "]";
//	      log.warn(logMessage);
	      return null;
	    } 
	  }
	  
	  public static Set<String> windowStack() {
	    return get("__test.browser.window.stack");
	  }
	  
//	  public static void addTrace(PageTrace trace) {
//	    if (getPageTraces() != null) {
//	      List<PageTrace> pageTraces = getPageTraces();
//	      int size = pageTraces.size();
//	      if (size > 0) {
//	        PageTrace previous = pageTraces.get(size - 1);
//	        if (trace.getUri() != null && 
//	          !trace.getUri().equals(previous.getUri()))
//	          getPageTraces().add(trace); 
//	      } else {
//	        getPageTraces().add(trace);
//	      } 
//	    } 
//	  }
//	  
//	  public static List<PageTrace> getPageTraces() {
//	    return get("__test.page.trace.list");
//	  }
	  
	  public static boolean screenshotsEnabled() {
	    return ((Boolean)MoreObjects.firstNonNull(get("__test.exec.screenshots.enabled"), Boolean.valueOf(true))).booleanValue();
	  }
	  
	  public static void setScreenshotsEnabled(boolean enabled) {
	    Preconditions.checkState((get("__test.exec.screenshots.enabled") == null), "You Cannot override Screenshots Enabled/Disabled at Runtime");
	    set("__test.exec.screenshots.enabled", Boolean.valueOf(enabled));
	  }
	  
	  public static boolean isDryRunMode() {
	    return ((Boolean)MoreObjects.firstNonNull(get("__test.exec.dry.run.mode"), Boolean.valueOf(false))).booleanValue();
	  }
	  
	  public static void setDryRunMode(boolean dryRunMode) {
	    Preconditions.checkState((get("__test.exec.dry.run.mode") == null), "You Cannot override the Dry Run Mode at Runtime");
	    set("__test.exec.dry.run.mode", Boolean.valueOf(dryRunMode));
	  }
	  
//	  public static Organization getGTNOrg() {
//	    return get("__organization.gtn");
//	  }
//	  
//	  public static void setGTNOrg(Organization org) {
//	    Preconditions.checkState((getGTNOrg() == null), "You Cannot override the Host Org");
//	    set("__organization.gtn", org);
//	  }
//	  
//	  public static TestType getTestType() {
//	    return get("__test.exec.type");
//	  }
//	  
//	  public static void setTestType(TestType testType) {
//	    set("__test.exec.type", testType);
//	  }
	  
	  public static String getUuid() {
	    return get("__test.exec.uuid");
	  }
	  
	  public static String getTestExecutionPlanUuid() {
	    return get("__test.exec.tep.uuid");
	  }
	  
	  public static String getBaseUrl() {
	    return get("__test.base.url");
	  }
	  
//	  @Deprecated
//	  private static void setBaseUrl(String baseUrl) {
//	    getLogger().info("[DriverScript] Setting BaseURL " + baseUrl);
//	    set("__test.base.url", baseUrl);
//	  }
//	  
//	  public static WebDriver getDriver() {
//	    WebDriver driver = get("__enhanced.web.driver");
//	    if (null == driver) {
//	      setWebDriver((WebDriver)getWebDriverProvider().get());
//	      setDriverActive();
//	      return getDriver();
//	    } 
//	    return driver;
//	  }
	  
	  public static void setWebDriver(WebDriver webDriver) {
	    set("__enhanced.web.driver", webDriver);
	  }
	  
//	  private static DriverProvider getWebDriverProvider() {
//	    return get("__web.driver.provider");
//	  }
//	  
//	  protected static void setWebDriverProvider(DriverProvider driverSupplier) {
//	    set("__web.driver.provider", driverSupplier);
//	  }
	  
	  public static String getTestExecutionPlan() {
	    return get("__test.exec.tep");
	  }
	  
	  public static void setTestExecutionPlan(String tep) {
	    set("__test.exec.tep", tep);
	  }
	  
	  public static String getTestExecutionPlanFullName() {
	    return get("__test.exec.tep.full.name");
	  }
	  
	  public static void setTestExecutionPlanFullName(String tepFullName) {
	    set("__test.exec.tep.full.name", tepFullName);
	  }
	  
	  public static ITestContext getTestContext() {
	    return get("__test.itestcontext");
	  }
	  
	  public static void setTestContext(ITestContext testContext) {
	    set("__test.itestcontext", testContext);
	  }
	  
	  public static String getScriptName() {
	    return get("__test.exec.script.name");
	  }
	  
	  public static void setScriptName(String tsname) {
	    set("__test.exec.script.name", tsname);
	  }
	  
	  public static String getScriptUUID() {
	    return get("__test.exec.script.uuid");
	  }
	  
	  public static void setScriptUUID(String tsuuid) {
	    set("__test.exec.script.uuid", tsuuid);
	  }
	  
	  public static DesiredCapabilities getCapabilities() {
	    return get("__desired.capabilities");
	  }
	  
	  public static void setCapabilities(DesiredCapabilities capabilities) {
	    set("__desired.capabilities", capabilities);
	  }
	  
	  public static String getBuildNo() {
	    return get("__build.no");
	  }
	  
	  public static void setBuildNo(String buildNo) {
	    set("__build.no", buildNo);
	  }
	  
	  public static void setBuildInfo(String buildNoRawRss) {
	    set("__build.no.raw.rss", buildNoRawRss);
	  }
	  
	  public static String getBuildInfo() {
	    return get("__build.no.raw.rss");
	  }
	  
//	  public static Environment getEnv() {
//	    return get("__test.target.env");
//	  }
//	  
//	  public static void setEnv(Environment env) {
//	    Preconditions.checkNotNull(env, "Target Environment Cannot be Null");
//	    set("__test.target.env", env);
//	    setBaseUrl(env.getBaseUrl().toExternalForm());
//	  }
//	  
//	  public static TestStatus getTestStatus() {
//	    return get("__test.exec.status");
//	  }
//	  
//	  public static void setTestStatus(TestStatus status) {
//	    set("__test.exec.status", status);
//	  }
//	  
//	  public static void attach(FileAttachment screensnap) {
//	    getAttachments().add(screensnap);
//	  }
//	  
//	  public static List<FileAttachment> getAttachments() {
//	    return get("__test.attachments");
//	  }
	  
	  public static Stopwatch getStopwatch() {
	    return get("__stopwatch");
	  }
	  
	  public static void setStopwatch(Stopwatch stopwatch) {
	    set("__stopwatch", stopwatch);
	  }
	  
	  public static void setTestExecutionStartTime(LocalDateTime date) {
	    set("__test.execution.start.time", date);
	  }
	  
	  public static LocalDateTime getTestExecutionStartTime() {
	    return get("__test.execution.start.time");
	  }
	  
	  public static void setAgentInstanceId(String instanceId) {
	    set("__test.executed.agent.instance.id", instanceId);
	  }
	  
	  public static String getAgentInstanceId() {
	    return get("__test.executed.agent.instance.id");
	  }
	  
//	  public static SoftAssertions getSoftAssert() {
//	    return get("__soft.assert");
//	  }
//	  
//	  public static void setSoftAssert(SoftAssertions softassert) {
//	    set("__soft.assert", softassert);
//	  }
	  
	  public static final boolean isOpen() {
	    return (cache.get() != null);
	  }
	  
	  public static final void set(String key, Object val) {
	    Preconditions.checkNotNull(key);
	    Map<String, Object> map = cache.get();
	    if (map != null)
	      map.put(key, val); 
	  }
	  
	  public static final void remove(String key) {
	    Preconditions.checkNotNull(key);
	    Map<String, Object> map = cache.get();
	    if (map != null)
	      map.remove(key); 
	  }
	  
	  public static final <X> X get(String key) {
	    Map<String, Object> map = cache.get();
	    if (map != null)
	      return (X)map.get(key); 
	    return null;
	  }
	  
	  public static final <X> X getVal(String key, Supplier<X> supplier) {
	    if (isOpen()) {
	      if (null != get(key))
	        return get(key); 
	      X val = (X)supplier.get();
	      set(key, val);
	      return val;
	    } 
	    return (X)supplier.get();
	  }
	  
	  public static void setBenchmarkCapable() {
	    set("__benchmark.capable", Boolean.valueOf(true));
	  }
	  
	  public static boolean isBenchmarkCapable() {
	    return ((Boolean)MoreObjects.firstNonNull(get("__benchmark.capable"), Boolean.valueOf(false))).booleanValue();
	  }
	  
//	  public static ServerDetails getServerDetails() {
//	    return getProfileFixture().getServerDetails();
//	  }
//	  
//	  public static Profile getProfileFixture() {
//	    return get("__profile.fixture");
//	  }
//	  
//	  public static void setProfileFixture(Profile profileFixture) {
//	    set("__profile.fixture", profileFixture);
//	  }
	  
	  public static String getReason() {
	    return get("__test.reason");
	  }
	  
	  public static void setReason(String reason) {
	    set("__test.reason", reason);
	  }
	  
	  public static String getReasonUrl() {
	    return get("__test.reason.url");
	  }
	  
	  public static void setReasonUrl(String url) {
	    set("__test.reason.url", url);
	  }
	  
	  public static String getFailedLine() {
	    return get("__test.reason.line");
	  }
	  
	  public static String getCurrentWindowHandle() {
	    return get("__test.current.desktop.window.handle");
	  }
	  
	  public static void setFailedLine(String line) {
	    set("__test.reason.line", line);
	  }
	  
//	  public static Credential getUserCredentials() {
//	    return get("__user.credentials");
//	  }
//	  
//	  public static void setUserCredentials(Credential prodUser) {
//	    set("__user.credentials", prodUser);
//	  }
	  
	  public static Optional<String> getTestManagerId() {
	    String testManagerId = get("__test.manager.id");
	    return Optional.fromNullable(testManagerId);
	  }
	  
	  public static Optional<String> getParallelExecutionMetaData() {
	    String metaData = get("_agent.execution.parallel");
	    return Optional.fromNullable(metaData);
	  }
	  
	  public static Optional<String> getOverrideProductLabel() {
	    String productName = get("__override.product.label");
	    return Optional.fromNullable(productName);
	  }
	  
	  public static Optional<String> getOverrideProductTag() {
	    String productTag = get("__override.product.tag");
	    return Optional.fromNullable(productTag);
	  }
	  
	  public static Optional<String> getOverrideAllContextVersion() {
	    String overrideContextVersion = get("__override.all.context.version");
	    return Optional.fromNullable(overrideContextVersion);
	  }
	  
	  public static Optional<String> getScriptExecutor() {
	    String scriptExecutor = get("__script.executor");
	    return Optional.fromNullable(scriptExecutor);
	  }
	  
	  public static Optional<String> getScriptExecutorPrettyName() {
	    String scriptExecutorPrettyName = get("__script.executor.pretty.name");
	    return Optional.fromNullable(scriptExecutorPrettyName);
	  }
	  
	  public static Optional<String> getOrganization() {
	    String organization = get("__organization");
	    return Optional.fromNullable(organization);
	  }
	  
	  public static Optional<URL> getAppiumHubURL() {
	    URL appiumHubURL = get("__appium.hub.url");
	    return Optional.fromNullable(appiumHubURL);
	  }
	  
	  public static DesiredCapabilities getMobileCapabilities() {
	    return get("__mobile.desired.capabilities");
	  }
	  
	  public static String getCurrentTestCaseId() {
	    String testCaseId = get("_test.case.id");
	    if (Objects.nonNull(testCaseId))
	      return testCaseId.toString(); 
	    return testCaseId;
	  }
	  
	  public static void setMobileCapabilities(DesiredCapabilities mobileCapabilities) {
	    set("__mobile.desired.capabilities", mobileCapabilities);
	  }
	  
//	  public static MobileDriver getMobileDriver() {
//	    MobileDriver driver = get("__mobile.driver");
//	    if (null == driver) {
//	      setMobileDriver((MobileDriver)getMobileDriverProvider().get());
//	      setMobileDriverActive();
//	      return getMobileDriver();
//	    } 
//	    return driver;
//	  }
//	  
//	  public static void setMobileDriver(MobileDriver driver) {
//	    set("__mobile.driver", driver);
//	  }
//	  
//	  public static MobileScriptContext getMobileScriptContext() {
//	    return get("__mobile.script.context");
//	  }
	  
	  private static void setMobileDriverActive() {
	    set("__mobile.driver.active", Boolean.TRUE);
	  }
	  
	  public static boolean isMobileDriverActive() {
	    return ((Boolean)((get("__mobile.driver.active") != null) ? get("__mobile.driver.active") : Boolean.FALSE)).booleanValue();
	  }
	  
//	  public static void setMobileDriverProvider(MobileDriverProvider provider) {
//	    set("__mobile.driver.provider", provider);
//	  }
//	  
//	  private static MobileDriverProvider getMobileDriverProvider() {
//	    return get("__mobile.driver.provider");
//	  }
	  
	  public static void setMobileScreenshotsEnabled(boolean mobileScreenshotsEnabled) {
	    Preconditions.checkState((get("__mobile.screenshots.enabled") == null), "You Cannot override Screenshots Enabled/Disabled at Runtime");
	    set("__mobile.screenshots.enabled", Boolean.valueOf(mobileScreenshotsEnabled));
	  }
	  
	  public static boolean getMobileScreenshotsEnabled() {
	    return ((Boolean)MoreObjects.firstNonNull(get("__mobile.screenshots.enabled"), Boolean.valueOf(true))).booleanValue();
	  }
	  
//	  public static DevicePlatform getMobilePlatform() {
//	    return get("__mobile.platform");
//	  }
//	  
//	  public static void setMobilePlatform(DevicePlatform mobilePlatform) {
//	    set("__mobile.platform", mobilePlatform);
//	  }
//	  
//	  public static ApplicationType getMobileApplicationType() {
//	    return get("__mobile.application.type");
//	  }
//	  
//	  public static void setMobileApplicationType(ApplicationType applicationType) {
//	    set("__mobile.application.type", applicationType);
//	  }
	  
	  public static String getMobileApplicationKey() {
	    return get("__mobile.application.key");
	  }
	  
	  public static void setMobileApplicationKey(String mobileApplicationKey) {
	    set("__mobile.application.key", mobileApplicationKey);
	  }
	  
	  public static String getMobileDevicePoolKey() {
	    return get("__mobile.device.pool.key");
	  }
	  
	  public static void setMobileDevicePoolKey(String mobileDevicePoolKey) {
	    set("__mobile.device.pool.key", mobileDevicePoolKey);
	  }
	  
	  public static String getOrganizationId() {
	    return get("__organization.id");
	  }
	  
	  public static void setOrganizationId(String organizationId) {
	    set("__organization.id", organizationId);
	  }
	  
	  public static Boolean getMobileDevicePoolDirty() {
	    return get("__mobile.device.pool.dirty");
	  }
	  
	  public static void setMobileDevicePoolDirty(Boolean devicePoolDirty) {
	    set("__mobile.device.pool.dirty", devicePoolDirty);
	  }
	  
	  public static Boolean getMobileApplicationDirty() {
	    return get("__mobile.application.dirty");
	  }
	  
	  public static void setMobileApplicationDirty(Boolean applicationDirty) {
	    set("__mobile.application.dirty", applicationDirty);
	  }
	  
	  public static String getFrameToIgnore() {
	    return get("__frame_to_ignore");
	  }
	  
	  public static void setFrameToIgnore(String frameToIgnore) {
	    set("__frame_to_ignore", frameToIgnore);
	  }
	  


}
