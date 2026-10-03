package testBase;

import java.net.MalformedURLException;
import java.util.Objects;

import org.openqa.selenium.WebDriver;

/**
* The Driver class is a utility class for initializing and quitting the WebDriver instance for web automation.
* It provides methods to initialize the driver based on the specified driver type and to quit the driver.
* The class is designed as final and cannot be instantiated.
*/
public final class Driver {

	/**
	 * Private constructor to prevent instantiation of the Driver class.
	 */
	private Driver() {
	}

	/**
	 * Initializes the WebDriver instance for web automation based on the specified driver type.
	 *
	 * @param driverType the type of the driver to initialize
	 * @throws MalformedURLException if the driver URL is malformed
	 */
	public static void initDriverForWeb(String driverType) throws MalformedURLException {
		if (Objects.isNull(ThreadUtils.getDriverRef())) {
			WebDriver driver = BrowserFactory.initBrowser(driverType);
			ThreadUtils.setDriverRef(driver);
		}
	}

	/**
	 * Quits the WebDriver instance if it is not null and removes the driver reference from the thread.
	 * Enhanced with better error handling.
	 */
	public static void quitDriver() {
		if (Objects.nonNull(ThreadUtils.getDriverRef())) {
			try {
				ThreadUtils.getDriverRef().quit();
			} catch (Exception e) {
				System.err.println("Error quitting WebDriver: " + e.getMessage());
				// Continue with cleanup even if quit fails
			} finally {
				ThreadUtils.removeDriverRef();
			}
		}
	}
}
