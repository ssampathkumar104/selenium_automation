//package scripts;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.concurrent.TimeUnit;
//
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.testng.annotations.Test;
//
//import io.appium.java_client.windows.WindowsDriver;
//
//public class Outlook_desktop {
//	
//	  private static WindowsDriver outlookSession = null;
//	  private static WebElement outlookResult = null;
//  @Test
//  public void f() {
////	  outlookSession.findElementByName("Maximize").click();
////	  outlookSession.findElementByXPath("//TreeItem[contains(@Name,'Drafts: 4 items')]").click();
//	  
//	  outlookSession.findElementByXPath("//Tree Item[@Name='Drafts: 4 items']").click();
//	  outlookSession.findElementByName("Close").click();
//  }
//
////  @BeforeSuite
//  public void beforeSuite() {
//	  try {
//          DesiredCapabilities capabilities = new DesiredCapabilities();
//          capabilities.setCapability("app", "C:\\Program Files\\Microsoft Office\\root\\Office16\\OUTLOOK.EXE");
//          outlookSession = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
//          outlookSession.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
//
////          outlookResult = outlookSession.findElementByAccessibilityId("CalculatorResults");
////          Assert.assertNotNull(outlookResult);
//      }catch(Exception e){
//          e.printStackTrace();
//      } finally {
//      }
//  }
//  
//  
////  if(outlookSession.findElementByName("Folder Pane Minimized").isDisplayed()) {
////  outlookSession.findElementByName("Folder Pane Minimized").click();
////}
//
////outlookSession.findElementByXPath("//Button[@LegacyIAccessible.Name='Maximize']").click();
////Thread.sleep(TimeUnit.SECONDS,5);
//
////  @Test
// public void rdpConnection() throws MalformedURLException, InterruptedException {
//	  
//	  WindowsDriver rdpSession = null;
//	  
//	  DesiredCapabilities rdpCapabilities = new DesiredCapabilities();
//	  rdpCapabilities.setCapability("app", "C:\\Windows\\System32\\mstsc.exe");
//      rdpSession = new WindowsDriver(new URL("http://127.0.0.1:4723"), rdpCapabilities);
//
//      rdpSession.findElementByName("Computer:").sendKeys("REMOTE-HOST-NAME");
//      rdpSession.findElementByName("Connect").click();
//      
//      Thread.sleep(5000);
//      rdpSession.findElementByName("Password").sendKeys("your-password-here");
//      rdpSession.findElementByName("OK").click();
//      Thread.sleep(10000);
//      DesiredCapabilities rdpCapabilities1 = new DesiredCapabilities();
//		rdpCapabilities1.setCapability("app", "C:\\Program Files\\Microsoft Office\\root\\Office16\\WINWORD.EXE");
//		WindowsDriver driver1 = new WindowsDriver(new URL("http://127.0.0.1:4723"), rdpCapabilities);
//
//Thread.sleep(2000);
//		
//driver1.findElementByName("Blank document").click();
//	  
//	  
//  }
//}
