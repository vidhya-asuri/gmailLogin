package AutomationTrainingPrj1.gmailLogin;

import org.testng.annotations.Test;



import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// XPath tutorial - http://zvon.org/xxl/XPathTutorial/Output/examples.html
public class gmailLoginTest {

  @Test
  public void invalidPassword() {
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
      // http://toolsqa.com/selenium-webdriver/findelement-and-findelements-command/
      driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
      // grab the next button by ID
      WebElement nextButton = driver.findElement(By.id("next"));

      nextButton.click();
      driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
      WebElement passwordInput = driver.findElement(By.id("Passwd")); // #Passwd password-shown
      passwordInput.sendKeys("piOctI$*"); // enter incorrect password.

      driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
      
      // grab the sign-in button by ID
      WebElement signInBtn = driver.findElement(By.id("signIn"));
      driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
      // http://toolsqa.com/selenium-webdriver/findelement-and-findelements-command/
      signInBtn.click();
      //driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
      
      // verify there is an error message since an incorrect password was entered.
      // xpath for the error message span element. //*[@id="errormsg_0_Passwd"]
      WebDriverWait wait = new WebDriverWait(driver, 10);
      WebElement errorAlert = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@id='errormsg_0_Passwd']")));

      
      //WebElement errorMessage = driver.findElement(By.xpath("//span[@id='errormsg_0_Passwd']"));
      
      
      System.out.println(errorAlert.getText());
//      WebElement errorMessage = driver.findElement(By.id("errormsg_0_Passwd"));
      driver.close();
      driver.quit();
  }
  
  @Test
  public void validLoginToYahoo() {
	  //login-username MeadGotHech2 
	  // pwd = vid_auto_test@yahoo.com/starpOmgeed3
	  File file = new File("C:/Users/asuriv/SQS-Training/SeleniumTraining/geckodriver/geckodriver.exe");
	  System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());

      WebDriver driver = new FirefoxDriver();

      driver.get("https://login.yahoo.com/?.src=ym&.intl=us&.lang=en-US&.done=https%3a//mail.yahoo.com");
      Set<Cookie> cookies = driver.manage().getCookies();
      Iterator<Cookie> ci = cookies.iterator();
      while(ci.hasNext()){
    	  Cookie c = ci.next();
    	  driver.manage().deleteCookie(c);
      }
      
      
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      // get the email input text box.
      WebElement emailInput = driver.findElement(By.id("login-username"));
      emailInput.sendKeys("vid_auto_test"); // enter email.
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      
      // uncheck "Stay signed in " checkbox
      WebElement staySignedInChkBox = driver.findElement(By.xpath("//*[@id=\"persistency\"]/div/label"));
      staySignedInChkBox.click();
      
//      if(staySignedInChkBox.isDisplayed() && staySignedInChkBox.isEnabled() && staySignedInChkBox.isSelected()){
//    	  staySignedInChkBox.click();
//      }
      
      // grab the next button by ID
      WebElement nextButton = driver.findElement(By.id("login-signin"));
      // http://toolsqa.com/selenium-webdriver/findelement-and-findelements-command/
      driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
      nextButton.click();
      driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
      WebElement passwordInput = driver.findElement(By.id("login-passwd")); 
      passwordInput.sendKeys("starpOmgeed3"); // enter correct password.

      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      
      // grab the next button by ID
      WebElement signInBtn = driver.findElement(By.id("login-signin"));
      driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
      // http://toolsqa.com/selenium-webdriver/findelement-and-findelements-command/
      signInBtn.click();
      driver.close();
      driver.quit();


	  
  }  
  
  
  @Test
  public void invalidEmail() {
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
      emailInput.sendKeys("vid.auto@#$@#.test@gmail.com"); // enter email.
      
      // grab the next button by ID
      WebElement nextButton = driver.findElement(By.id("next"));
      nextButton.click();
      
      // Now expect an error message "Please enter a valid email address." to be displayed.
      WebElement errorMessage = driver.findElement(By.id("errormsg_0_Email")); 

      System.out.println("Error message " + errorMessage.getText());
      driver.close();
      driver.quit();

      
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
