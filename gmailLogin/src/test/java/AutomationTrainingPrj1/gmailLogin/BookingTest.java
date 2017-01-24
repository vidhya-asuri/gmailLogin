package AutomationTrainingPrj1.gmailLogin;

import org.testng.annotations.Test;

import java.awt.AWTException;
//import java.awt.Robot;
//import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
  public void searchAndSelectProduct() {
	  File file = new File("C:/Users/asuriv/SQS-Training/SeleniumTraining/chromedriver/chromedriver.exe");
	  System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

      // Create the Chrome driver object.
      WebDriver driver = new ChromeDriver();
      driver.get("http://marbles.shopvisible.com/");
      
      driver.manage().window().maximize(); // maximize window.
      // enter destination in web element with id = ss
      driver.manage().deleteAllCookies();
      driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);  // 20 seconds to delete cookies
      
      WebDriverWait wait = new WebDriverWait(driver,20);
      WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("tSearch")));
      WebElement popOverXBtn = driver.findElement(By.className("cl"));
      
      popOverXBtn.click();
      
      searchInput.sendKeys("scrabble");
      searchInput.sendKeys(Keys.ENTER);
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);// give it 1 minute to finish loading results page.
      List<Float> listOfPrices = new ArrayList<Float>();
      
      List<WebElement>  searchResults = driver.findElements(By.xpath("//div[@class='prod-list']/div[@class='prod-item']/a[@class='first-link']")); //@id='b1'
      // searchResults is a list of web elements; specifically the 'a' tags.
      System.out.println("Number of search results: " + searchResults.size());
      
      // click on the a tag for the first result.
      if(searchResults.size() > 0){
    	  // click the first 'a' tag.
    	  WebElement aTag = searchResults.get(0);
    	  driver.get(aTag.getAttribute("href"));
    	  // wait till the page finishes loading.
          WebDriverWait waitAddToCart = new WebDriverWait(driver, 10);
          WebElement addToCart = waitAddToCart.until(ExpectedConditions.elementToBeClickable(By.id("addToCart")));
          addToCart.click();
          System.out.println("Clicked on the add to cart button.");
          WebDriverWait waitChkOutBtn = new WebDriverWait(driver,5);
          WebElement chkOutBtn = waitAddToCart.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='cart-buttons-right']/a[@class='check-link btn']")));
    	  chkOutBtn.click();
          System.out.println("Clicked on the check out button.");
      }
      else{
    	  System.out.println("Sorry, no results were found");
    	  // this should be replaced with what actually happens on the site, when there are no 
    	  // results for a specific search.

      }
      driver.close();
      driver.quit();
      
  }
  
	  
  @Test
  public void testSearch() throws AWTException {
	  File file = new File("C:/Users/asuriv/SQS-Training/SeleniumTraining/chromedriver/chromedriver.exe");
	  System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

      // Create the Chrome driver object.
      WebDriver driver = new ChromeDriver();
      driver.get("http://marbles.shopvisible.com/");
      
      // enter destination in web element with id = ss
      driver.manage().deleteAllCookies();
      driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);  // 20 seconds to delete cookies
      
      WebDriverWait wait = new WebDriverWait(driver,20);
      WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("tSearch")));
      WebElement popOverXBtn = driver.findElement(By.className("cl"));
      
      popOverXBtn.click();
      
      searchInput.sendKeys("scrabble");
      searchInput.sendKeys(Keys.ENTER);
      
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);// give it 1 minute to finish loading results page.
      List<Float> listOfPrices = new ArrayList<Float>();
      
      List<WebElement>  listOfProductPrices = driver.findElements(By.xpath("//div[@class='prod-list']/div[@class='prod-item']/div[@class='prod-item-box']/div[@class='inf']/p[@class='price']")); //@id='b1'
      for(int i=0; i < listOfProductPrices.size(); i++){
    	  List<WebElement> we = listOfProductPrices.get(i).findElements(By.tagName("span")); // span elements within the p tags with class=price contains the actual prices.
    	  // span elements are found only when there is a sale. If not, price is located in the p element with class=price
    	  int lenWe = we.size();
    	  if(lenWe > 1){
    		  // item is on sale, since a span element was found.
    		  // select span element with class='was'
    		  // there can only be original price and sale price, so it's safe to assume that there will only be on
    		  // element with class = 'was'; same logic applies for sale price.
    		  WebElement originalPrice = driver.findElement(By.xpath("//div[@class='prod-list']/div[@class='prod-item']/div[@class='prod-item-box']/div[@class='inf']/p[@class='price']/span[@class='was']"));
    		  WebElement pTagClassPrice = driver.findElement(By.xpath("//div[@class='prod-list']/div[@class='prod-item']/div[@class='prod-item-box']/div[@class='inf']/p[@class='price']"));
    		  String isItemBackOrdered = pTagClassPrice.getAttribute("data-bo");
    		  int numAvailable = Integer.parseInt(pTagClassPrice.getAttribute("data-qty"));
    		  if(originalPrice != null){
    			  String priceString = originalPrice.getText(); // priceString contains '$' as the first character.
    			  //System.out.println("Original price is " + priceString);
    		  }
    		  WebElement salePrice = driver.findElement(By.xpath("//div[@class='prod-list']/div[@class='prod-item']/div[@class='prod-item-box']/div[@class='inf']/p[@class='price']/span[@class='sale']"));
    		  if(salePrice != null){
    			  String priceString = salePrice.getText(); // priceString contains '$' as the first character.
    			  System.out.println("Sale price is " + priceString);
    			  listOfPrices.add(Float.parseFloat(priceString.substring(1,priceString.length()))); // sale price gets added to the list of prices.
    			  System.out.println(numAvailable + " no(s) available at " + priceString + " each");
    		  }
			  if(!Boolean.parseBoolean(isItemBackOrdered)){
				  // item is not out of stock, verify item quantity must be greater than 0.
				  throw new AssertionError(numAvailable > 0);
			  }

    		  //<p class="price" data-prod="6985" data-partno="11670" data-dept="152" data-qty="26" data-bo="False"><span class="was">$59.99</span><span class="sale">$29.99</span></p>
			  int prodNumber = Integer.parseInt(pTagClassPrice.getAttribute("data-prod"));
			  int partNumber = Integer.parseInt(pTagClassPrice.getAttribute("data-partno"));
			  
			  
			  
			  
    	  }
    	  else{
			  String priceString = listOfProductPrices.get(i).getText(); // priceString contains '$' as the first character.
			  listOfPrices.add(Float.parseFloat(priceString.substring(1,priceString.length()))); // no sale price, so add the only price listed for the product.
			  String backOrder = listOfProductPrices.get(i).getAttribute("data-bo");
			  int numAvailable = Integer.parseInt(listOfProductPrices.get(i).getAttribute("data-qty"));
			  System.out.println(numAvailable + " no(s) available at " + priceString + " each");
			  if(!Boolean.parseBoolean(backOrder)){
				  // item is not out of stock, verify item quantity must be greater than 0.
				  throw new AssertionError(numAvailable > 0);
			  }
    	  }
      }
      // sort the list of prices.
      Collections.sort(listOfPrices); //
      // just click on the first result for now, to proceed to buy the toy.
      // get the lowest price and index
      driver.close();
      driver.quit();
	  // XPath for the close (x) button on the pop-over. //*[@id="newsletter-box"]/div 
   }
  
  @BeforeMethod
  public void beforeMethod() {
  }

}
