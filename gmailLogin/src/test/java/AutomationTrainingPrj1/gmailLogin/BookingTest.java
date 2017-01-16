package AutomationTrainingPrj1.gmailLogin;

import org.testng.annotations.Test;

import java.awt.AWTException;
//import java.awt.Robot;
//import java.awt.event.KeyEvent;
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;

public class BookingTest {
  @Test
  public void f() throws AWTException {
	  File file = new File("C:/Users/asuriv/SQS-Training/SeleniumTraining/chromedriver/chromedriver.exe");
	  System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

      // Create the Chrome driver object.
      WebDriver driver = new ChromeDriver();
      driver.get("http://marbles.shopvisible.com/");
//      driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);// give it a minute to finish loading.
      
      // enter destination in web element with id = ss
      driver.manage().deleteAllCookies();
      driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);  // 20 seconds to delete cookies
      
      WebDriverWait wait = new WebDriverWait(driver,20);
      WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("tSearch")));
      //WebElement popOverXBtn = driver.findElement(By.xpath("//*[@id=\"newsletter-box\"]/div"));
      WebElement popOverXBtn = driver.findElement(By.className("cl"));
      
      popOverXBtn.click();
      
    		//*[@id="newsletter-box"]/div
      searchInput.sendKeys("scrabble");
      searchInput.sendKeys(Keys.ENTER);
      //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);// 5 seconds to finish typing.

      // press enter in the search box
//      Robot r = new Robot();
//      r.keyPress(KeyEvent.VK_ENTER);
//      r.keyRelease(KeyEvent.VK_ENTER);
      
      driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);// give it 1 minute to finish loading results page.
      // get number of elements with class - prod-item. This is the number of resuts.
      
      WebElement results = driver.findElement(By.className("prod-item"));
      
	  // XPath for the close (x) button on the pop-over. //*[@id="newsletter-box"]/div 
      
      
  }
  @BeforeMethod
  public void beforeMethod() {
  }

}
