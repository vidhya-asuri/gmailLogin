package AutomationTrainingPrj1.gmailLogin;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


public class gmailLoginTest {

  @Test
  public void invalidLogin() {
	  File file = new File("C:/Users/asuriv/SQS-Training/SeleniumTraining/chromedriver/chromedriver.exe");
	  System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

      // Create the Chrome driver object.
      WebDriver driver = new ChromeDriver();
      driver.get("http://mail.google.com");
      // delete cookies (& clear cache?)
      driver.manage().deleteAllCookies();
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      // get the email input text box.
      WebElement emailInput = driver.findElement(By.id("Email"));
      emailInput.sendKeys("vid.auto.test@gmail.com"); // enter email.
      
      // grab the next button by ID
      WebElement nextButton = driver.findElement(By.id("next"));
      // http://toolsqa.com/selenium-webdriver/findelement-and-findelements-command/
      driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
      nextButton.click();
      driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
      WebElement passwordInput = driver.findElement(By.id("Passwd")); // #Passwd password-shown
      passwordInput.sendKeys("piOctI$*"); // enter incorrect password.

      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      
      // grab the next button by ID
      WebElement signInBtn = driver.findElement(By.id("signIn"));
      driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
      // http://toolsqa.com/selenium-webdriver/findelement-and-findelements-command/
      signInBtn.click();
      //driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
      
      // verify there is an error message since an incorrect password was entered.
      // xpath for the error message span element. //*[@id="errormsg_0_Passwd"]
//      WebElement errorMessage = driver.findElement(By.xpath("//*[@id=\"errormsg_0_Passwd\"]"));
      
    WebElement errorMessage = driver.findElement(By.id("errormsg_0_Passwd"));
      
      
      


	  
  }
	
  @Test
  public void f() {
	  File file = new File("C:/Users/asuriv/SQS-Training/SeleniumTraining/chromedriver/chromedriver.exe");
	  System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

      // Create the Chrome driver object.
      WebDriver driver = new ChromeDriver();
	  
//	  File file = new File("C:/Users/asuriv/SQS-Training/SeleniumTraining/geckodriver/geckodriver.exe");
//	  System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
//
//      WebDriver driver = new FirefoxDriver();


      // And use it to visit Google
      //driver.get("https://accounts.google.com/ServiceLogin?service=mail&continue=https://mail.google.com/mail/#identifier");
      driver.get("http://mail.google.com");
      
      //driver.manage().window().maximize(); // maximize window.
      
      // delete cookies (& clear cache?)
      driver.manage().deleteAllCookies();
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      // get the email input text box.
      WebElement emailInput = driver.findElement(By.id("Email"));
      emailInput.sendKeys("vid.auto.test@gmail.com");
      
      
      // grab the next button by ID
      WebElement nextButton = driver.findElement(By.id("next"));
      // http://toolsqa.com/selenium-webdriver/findelement-and-findelements-command/
      driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
      nextButton.click();
      driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
      WebElement passwordInput = driver.findElement(By.id("Passwd")); // #Passwd password-shown
      passwordInput.sendKeys("piOctIj8");

      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      
      // grab the next button by ID
      WebElement signInBtn = driver.findElement(By.id("signIn"));
      driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
      // http://toolsqa.com/selenium-webdriver/findelement-and-findelements-command/
      signInBtn.click();
      driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
      driver.close();
      driver.quit();

	  
  }
  @BeforeMethod
  public void beforeMethod() {
  }

  @AfterMethod
  public void afterMethod() {
  }

}
