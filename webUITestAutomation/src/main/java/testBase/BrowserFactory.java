package testBase;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
* The BrowserFactory class is a utility class for initializing web browsers and configuring browser options.
* It provides methods to initialize the browser based on the specified browser type and to configure browser options.
* The class is designed as final and cannot be instantiated.
*/
public final class BrowserFactory extends BaseClass {
	
	/**
	 * Private constructor to prevent instantiation of the BrowserFactory class.
	 */
	private BrowserFactory() {
	}

	/**
	 * Initializes the web browser based on the specified browser type.
	 *
	 * @param browser the type of the browser to initialize
	 * @return the WebDriver instance
	 * @throws MalformedURLException if the remote URL is malformed
	 */
	public static WebDriver initBrowser(String browser) throws MalformedURLException {
		WebDriverListener listener;
		WebDriver dr = null;
		if (Objects.isNull(getDriver()) && Objects.equals(getParameter("remote"), "true")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setBrowserName(browser);
			log().info("Running on Hub");
			if (browser.equalsIgnoreCase("Chrome")) {
				if (!Objects.isNull(getParameter("version"))) {
					capabilities.setVersion(getParameter("version"));
				}
				capabilities.setCapability("recordVideo", Boolean.parseBoolean(getParameter("recording", "false")));
				capabilities.setCapability("idleTimeout", 300);
				capabilities.setCapability("name", ThreadUtils.getITestContext().getName().trim());
				capabilities.merge(chromeOptions());
			} else if (browser.equalsIgnoreCase("firefox")) {
				capabilities.merge(firefoxOptions());
			}
			dr = new RemoteWebDriver(new URL(getParameter("remoteURL", "http://localhost:4444/wd/hub")), chromeOptions());
		} else if (Objects.isNull(getDriver())) {
			log().info("Not Running on Hub");
			if (browser.equalsIgnoreCase("Chrome")) {
				// Clear caches to ensure we get the proper driver version
				WebDriverManager.chromedriver().clearResolutionCache().setup();
				WebDriverManager.chromedriver().clearDriverCache().setup();
				
				WebDriverManager wdm =null;
				if(StringUtils.isNoneBlank(getParameter("CHROME_VERSION")))
					wdm = WebDriverManager.chromedriver().browserVersion(getParameter("CHROME_VERSION")).capabilities(chromeOptions());
				else
					wdm = WebDriverManager.chromedriver().capabilities(chromeOptions());
				
				wdm.setup();
				dr = new ChromeDriver(chromeOptions());
				System.setProperty("webdriver.chrome.silentOutput", "true");
			} else if (browser.equalsIgnoreCase("firefox")) {
				dr = WebDriverManager.firefoxdriver().capabilities(firefoxOptions()).create();
			}
		}

		listener = new testBase.listners.EventHandler();
		return new EventFiringDecorator(listener).decorate(dr);
	}

	/**
	 * Closes the web browser if it is not null and removes the driver reference from the thread.
	 */
	public static void closeBrowser() {
		if (Objects.nonNull(ThreadUtils.getDriverRef())) {
			ThreadUtils.getDriverRef().quit();
		}
		ThreadUtils.removeDriverRef();

	}

	/**
	 * Configures the options for the Firefox browser.
	 *
	 * @return the configured FirefoxOptions instance
	 */
	private static FirefoxOptions firefoxOptions() {
		FirefoxOptions option = new FirefoxOptions();
		option.addPreference("browser.download.folderList", 2);
		option.addPreference("browser.download.dir", ThreadUtils.getTempDirectoryPath() + "download");
		option.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
		option.addPreference("browser.download.manager.showWhenStarting", false);
		option.addPreference("pdfjs.disabled", true);
		return option;
	}

	/**
	 * Configures the options for the Chrome browser.
	 *
	 * @return the configured ChromeOptions instance
	 */
	private static ChromeOptions chromeOptions() {
		List<String> experimentalFlags = new ArrayList<>();
		experimentalFlags.add("same-site-by-default-cookies@2");
		experimentalFlags.add("cookies-without-same-site-must-be-secure@2");

		String isJenkinsExecutions = getParameter("Jenkins_Execution");

		HashMap<String, Object> chromePrefs = new HashMap<>();
		chromePrefs.put("profile.default.content_settings.popups", 0);
		
		chromePrefs.put("download.prompt_for_download", false);
		chromePrefs.put("download.directory_upgrade", true); // Automatically overwrite downloads
		chromePrefs.put("browser.enabled_labs_experiments", experimentalFlags);
		chromePrefs.put("safebrowsing.enabled", true); // need to set to 'true' to download XML files @ssanapti
		chromePrefs.put("extendedDebugging", false);
		// Setting up the default download path 
		if (Objects.isNull(getDriver()) && Objects.equals(getParameter("remote"), "true")) {
			chromePrefs.put("download.default_directory",
					File.separator + "home" + File.separator + "seluser" + File.separator + "Downloads");
			System.out.println("%%%%%%%%%%%% Logged into remote executors ");
		} else {
			chromePrefs.put("download.default_directory", ThreadUtils.getDownloadDirectoryPath());
			System.out.println("%%%%%%%%%%%% Download directory is set to Local");

		}

		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
//		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		options.addArguments("--test-type");
		options.addArguments("--disable-software-rasterizer");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--disable-renderer-backgrounding");
		options.addArguments("--disable-extensions");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--allow-file-access-from-files");
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--safebrowsing-disable-download-protection"); // delete after trail
		options.addArguments("safebrowsing-disable-extension-blacklist");
//		options.addArguments("--disable-headless-mode");
		
		
		
		// headless execution and resolution setup for Jenkins in VM(windows OS)
		if (!StringUtils.isBlank(isJenkinsExecutions)
				&& (isJenkinsExecutions.equalsIgnoreCase("yes") || isJenkinsExecutions.equalsIgnoreCase("true"))) {
			options.addArguments("--headless");
			options.addArguments("--window-size=1980,1080");
			System.out.println("====== Server execution =====");
		} else {
			options.addArguments("start-maximized");
//			System.out.println("====== Local execution =====");
		}

		// TO-DO: Delete or Update once it is discussed 
		if (getParameter("browser_zoom_scale") != null) {
			options.addArguments("--force-device-scale-factor=" + getParameter("browser_zoom_scale"));
		}

		return options;
	}

}
