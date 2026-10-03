package testBase;

import org.openqa.selenium.WebDriver;

/**
* The BasePageObject class is a generic class that serves as the base class for page objects in the test framework.
* It provides a WebDriver instance and getter method to access the driver.
* The class is parameterized with the type B, which represents the specific subclass of BasePageObject.
*/
public class BasePageObject<B extends BasePageObject<B>>{
	private WebDriver driver;

	/**
	 * Returns the WebDriver instance associated with the page object.
	 *
	 * @return the WebDriver instance
	 */
	public WebDriver getDriver() {
		return this.driver;
	}

	/**
	 * Constructs a new instance of BasePageObject and initializes the driver with the driver reference from the thread.
	 */
	public BasePageObject() {
		this.driver = ThreadUtils.getDriverRef();
	}
}