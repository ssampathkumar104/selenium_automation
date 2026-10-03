package scripts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.Homepages2;

//@Test(groups = { "CS1", "P1" })
public class TC_ContextPassing3  {
	

	@Test
	public void validateExport() throws Exception {
		
		WebDriverManager.chromedriver().clearResolutionCache().setup();
		WebDriverManager.chromedriver().clearDriverCache().setup();
//		WebDriverManager wdm = WebDriverManager.chromedriver().driverVersion("122");
//		WebDriver dr = WebDriverManager.chromedriver().capabilities(chromeOptions()).create();
		WebDriver dr = new ChromeDriver(chromeOptions());
		dr.get("https://www.google.com/");
		Homepages2 p = new Homepages2(dr);
		System.out.println(p.txtDown.toString());
//		dr.close();
		dr.quit();
	}


	private static ChromeOptions chromeOptions() {
		List<String> experimentalFlags = new ArrayList<>();
		experimentalFlags.add("same-site-by-default-cookies@2");
		experimentalFlags.add("cookies-without-same-site-must-be-secure@2");


		HashMap<String, Object> chromePrefs = new HashMap<>();
		chromePrefs.put("profile.default.content_settings.popups", 0);
		
		chromePrefs.put("download.prompt_for_download", false);
		chromePrefs.put("browser.enabled_labs_experiments", experimentalFlags);
		chromePrefs.put("safebrowsing.enabled", true); // need to set to 'true' to download XML files @ssanapti
		chromePrefs.put("extendedDebugging", false);

		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
//		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		options.addArguments("--test-type");
		options.addArguments("--disable-software-rasterizer");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--disable-extensions");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--allow-file-access-from-files");
		options.addArguments("--remote-allow-origins=*");

		return options;
	}
}