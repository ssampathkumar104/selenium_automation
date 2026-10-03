package scripts;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Docker_Standalone_Chrome {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ChromeOptions options = new ChromeOptions();
		RemoteWebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
		driver.get("https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
		System.out.println("Title of the environment is "+driver.getTitle());
		driver.findElement(By.id("identifierId")).sendKeys("test.user@example.com");
		System.out.println("Username is typed");
		driver.findElement(By.xpath("//span[text()='Next']")).click();

	}

}
