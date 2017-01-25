package AutomationTrainingPrj1.gmailLogin;

import org.testng.annotations.Test;



import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterMethod;

// apache poi related imports.
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


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
	  /*
	   * This test fails due to the following issue - as soon as the signin button is clicked
	   * the previous page where the email is entered gets loaded. i.e. as soon as the signin button is clicked,
	   * the previous page is loaded instead of either staying in the same page.
	   * The expected result is that it should stay in the same page; in that case the span with the error message 
	   * would be accessible.
	   * But since we get kicked back to the previous page as soon as the signin button is clicked, 
	   * the span with the error message becomes inaccessible and so this test fails.
	   * 
	   * 
	   */
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
      
//      WebElement error = driver.findElement(By.xpath("//span[@id='errormsg_0_Passwd']"));
//      System.out.println(error.getText());
      // verify there is an error message since an incorrect password was entered.
      // xpath for the error message span element. //*[@id="errormsg_0_Passwd"]
      WebDriverWait wait = new WebDriverWait(driver, 10);
      WebElement errorAlert = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@id='errormsg_0_Passwd']")));
      System.out.println(errorAlert.getText());
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
  public void validLoginExcel() {
	  // Open excel file in read mode.
	  // read username and save it.
	  // read password and save it.
	  // continue as in valid login test (function named 'f' in this file.
	  File file =    new File("C:\\Users\\asuriv\\SQS-Training\\SeleniumProjects\\AutomationTrainingPrj1\\Credentials.xlsx");
	  try{
		  FileInputStream inputStream = new FileInputStream(file);
		  XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		  XSSFSheet sheetWithCredentials = wb.getSheet("valid_credentials");
		  String gmailLogin = sheetWithCredentials.getRow(0).getCell(0).getStringCellValue();
		  String gmailPasswd = sheetWithCredentials.getRow(0).getCell(1).getStringCellValue();
		  System.out.println("login email: " + gmailLogin);
		  System.out.println("password: " + gmailPasswd);
		  wb.close();
		  File cd = new File("C:/Users/asuriv/SQS-Training/SeleniumTraining/chromedriver/chromedriver.exe");
		  System.setProperty("webdriver.chrome.driver", cd.getAbsolutePath());
	      // Create the Chrome driver object.
	      WebDriver driver = new ChromeDriver();
	      driver.get("http://mail.google.com");
	      driver.manage().window().maximize(); // maximize window.
	      // delete cookies (& clear cache?)
	      driver.manage().deleteAllCookies();
	      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	      // get the email input text box.
	      WebElement emailInput = driver.findElement(By.id("Email"));
	      emailInput.sendKeys(gmailLogin);
	      // grab the next button by ID
	      WebElement nextButton = driver.findElement(By.id("next"));
	      driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	      nextButton.click();
	      driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	      WebElement passwordInput = driver.findElement(By.id("Passwd")); // 
	      passwordInput.sendKeys(gmailPasswd);
	      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	      // grab the next button by ID
	      WebElement signInBtn = driver.findElement(By.id("signIn"));
	      driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	      signInBtn.click();
	      driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	      driver.close();
	      driver.quit();
	  }
	  catch(FileNotFoundException fnfe){
		  System.out.println("Excel file not found...");
	  }
	  catch(IOException ioe){
		  System.out.println("IOException raised!");
	  }
	  
	  
  }  
  
  @Test
  public void invalidEmailExcel() {
	  // Open excel file in read mode.
	  // read username and save it.
	  // read password and save it.
	  // continue as in valid login test (function named 'f' in this file.
	  File file =    new File("C:\\Users\\asuriv\\SQS-Training\\SeleniumProjects\\AutomationTrainingPrj1\\Credentials.xlsx");
	  try{
		  FileInputStream inputStream = new FileInputStream(file);
		  XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		  XSSFSheet sheetWithCredentials = wb.getSheet("invalid_email");
		  String gmailLogin = sheetWithCredentials.getRow(0).getCell(0).getStringCellValue();
		  String gmailPasswd = sheetWithCredentials.getRow(0).getCell(1).getStringCellValue();
		  System.out.println("login email: " + gmailLogin);
		  System.out.println("password: " + gmailPasswd);
		  wb.close();
		  File cd = new File("C:/Users/asuriv/SQS-Training/SeleniumTraining/chromedriver/chromedriver.exe");
		  System.setProperty("webdriver.chrome.driver", cd.getAbsolutePath());
	      // Create the Chrome driver object.
	      WebDriver driver = new ChromeDriver();
	      driver.get("http://mail.google.com");
	      // delete cookies (& clear cache?)
	      driver.manage().deleteAllCookies();
	      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	      // get the email input text box.
	      WebElement emailInput = driver.findElement(By.id("Email"));
	      emailInput.sendKeys(gmailLogin); // enter email.
	      // grab the next button by ID
	      WebElement nextButton = driver.findElement(By.id("next"));
	      nextButton.click();
	      // Now expect an error message "Please enter a valid email address." to be displayed.
	      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	      WebElement errorMessage = driver.findElement(By.id("errormsg_0_Email")); 
	      System.out.println("Error message " + errorMessage.getText());

	      FileOutputStream fout=new FileOutputStream(file);
	      
	      driver.close();
	      driver.quit();
	  }
	  catch(FileNotFoundException fnfe){
		  System.out.println("Excel file not found...");
	  }
	  catch(IOException ioe){
		  System.out.println("IOException raised!");
	  }
	  
	  
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
  public void validLogin() {
	  File file = new File("C:/Users/asuriv/SQS-Training/SeleniumTraining/chromedriver/chromedriver.exe");
	  System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

      // Create the Chrome driver object.
      WebDriver driver = new ChromeDriver();

      // And use it to visit Google
      //driver.get("https://accounts.google.com/ServiceLogin?service=mail&continue=https://mail.google.com/mail/#identifier");
      driver.get("http://mail.google.com");

      // delete cookies (& clear cache?)
      //driver.manage().deleteAllCookies();
      // get the email input text box.
      WebElement emailInput = driver.findElement(By.id("Email"));
      emailInput.sendKeys("vid.auto.test@gmail.com");
      
      // grab the next button by ID
      WebElement nextButton = driver.findElement(By.id("next"));
      // http://toolsqa.com/selenium-webdriver/findelement-and-findelements-command/
      nextButton.click();
	  WebDriverWait wait = new WebDriverWait(driver,10);
	  WebElement passwordInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("Passwd"))); 
      passwordInput.sendKeys("piOctIj8");
      
      WebElement signinButton = driver.findElement(By.id("signIn"));
      signinButton.click();
      
      // z0
	  WebDriverWait waitForCompose = new WebDriverWait(driver,10);
	  WebElement composeBtn = waitForCompose.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='z0']/div[@class='T-I J-J5-Ji T-I-KE L3']"))); 
      composeBtn.click();
      
      // close the new message window.
      // <img class="Ha" id=":69" src="images/cleardot.gif" alt="Close" aria-label="Save &amp; Close" data-tooltip-delay="800" data-tooltip="Save &amp; Close">
      // find the x button.
	  WebDriverWait waitForXBtn = new WebDriverWait(driver,3);
	  WebElement xBtn = waitForXBtn.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@class='Ha']"))); 
 
	  // compose window is open.
	  WebDriverWait waitForToField = new WebDriverWait(driver,3);
	  WebElement toField = waitForToField.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@class='vO']"))); 

//	  WebElement toField = driver.findElement(By.xpath("//div[@class='oL aDm']"));
	  toField.sendKeys("vid.auto.test@gmail.com");
	  // enter something in the subject input. input element with name = subjectbox
	  WebElement subjectInput = driver.findElement(By.xpath("//input[@name='subjectbox']"));
	  subjectInput.sendKeys("Assignment 4 - Vidhya.");
	  
	  // find the send button - id = :8v ?
	  WebElement sendBtn = driver.findElement(By.xpath("//td[@class='gU Up']/child::div[position()=1]"));
	  sendBtn.click();
	  
      // xBtn.click(); // commenting out the closing of the compose window since i'm trying to send an email.

	  WebElement signOutPopUp = driver.findElement(By.xpath("//a[@class='gb_b gb_eb gb_R']"));
	  signOutPopUp.click();
	  WebDriverWait waitForSignOutBtn = new WebDriverWait(driver,3);
	  WebElement signOutBtn = waitForSignOutBtn.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='gb_71']"))); 
	  signOutBtn.click();
      
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
