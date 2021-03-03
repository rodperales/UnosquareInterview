package stepDefinition;

import static org.junit.Assert.assertEquals;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.*;

import pageObject.Scenario1_2;
import pageObject.Scenario3;

public class Steps{
	Properties prop = new Properties();
	InputStream configurations = null;
	WebDriver driver = null;
	String nameFromAPI = null;
	
	Scenario1_2 Sce12 = null;
	Scenario3 Sce3 = null;
	
	String PriceItemToCheck;
	
	public Steps() throws IOException{
		configurations = new FileInputStream("D:/Development/Eclipse/Testing/Cucumber/src/test/resources/Configurations/App.config");
		prop.load(configurations);
		
		System.setProperty("webdriver.chrome.driver","src/test/resources/Files/chromedriver.exe");
		
		Sce12 = new Scenario1_2(driver);
		Sce3 = new Scenario3(driver);
		
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Given("Go to Amazon")
	public void go_to_amazon() {
		driver.navigate().to( prop.getProperty("URL") );
		try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
	}
	
	@When("Landed")
	public void Landed() {
		String strUrl = driver.getCurrentUrl();
		assertEquals(strUrl, prop.getProperty("URL") );
		try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
	}
	
	@Then("^Seach for \"([^\"]*)\"$")
	public void Seach_for(String S1) {
		Sce12.SearchFor(S1);
		try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
		
	}	
	
	@And("Verify item")
	public void verify_item() {
		WebElement PriceItemOVerview = driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[2]/div/span[3]/div[2]/div[3]/div/span/div/div/div[2]/div[2]/div/div[2]/div[1]/div/div[1]/div/div/div/a/span[1]/span[2]/span[2]"));
		if( PriceItemOVerview.isDisplayed() ) {
			PriceItemToCheck = PriceItemOVerview.getText();
			try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
		} else {
			System.out.println( "Item not found 111" );
		}
	}
		
	@Then("Select First Result")
	public void select_first_result() {
		WebElement PriceItemOVerview = driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[2]/div/span[3]/div[2]/div[3]/div/span/div/div/div[2]/div[2]/div/div[2]/div[1]/div/div[1]/div/div/div/a/span[1]/span[2]/span[2]"));
		if( PriceItemOVerview.isDisplayed() ) {
			PriceItemOVerview.click();
			try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
		}
	}
	
	@When("Detail Page Loaded")
	public void detail_page_loaded() {
		WebElement PriceItem = driver.findElement(By.id("priceblock_ourprice"));
		if( PriceItem.isDisplayed() ) {
			PriceItemToCheck = PriceItem.getText();
		}
		try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
	}

	@Then("Compare Price")
	public void compare_price() {
		WebElement PriceItem = driver.findElement(By.id("priceblock_ourprice"));
		if( PriceItem.isDisplayed() ) {
			assertEquals(PriceItemToCheck, PriceItem.getText() );
		}
		try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
	}

	@Then("Add to cart")
	public void add_to_cart() {
		WebElement PriceItem = driver.findElement(By.id("add-to-cart-button"));
		if( PriceItem.isDisplayed() ) {
			PriceItem.click();
		}
		try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
	}

	@When("Go to Cart")
	public void go_to_cart() {
		WebElement PriceItem = driver.findElement(By.id("nav-cart"));
		if( PriceItem.isDisplayed() ) {
			PriceItem.click();
		}
		try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
	}	

	@Then("Verify Price on Cart")
	public void verify_price_on_cart() {
		WebElement PriceItem = driver.findElement(By.id("sc-subtotal-amount-buybox"));
		if( PriceItem.isDisplayed() ) {
			assertEquals(PriceItemToCheck, PriceItem.getText() );
		}
		try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
	}	

	@When("Proceed to Checkout")
	public void proceed_to_checkout() {
		WebElement PriceItem = driver.findElement(By.name("proceedToRetailCheckout"));
		if( PriceItem.isDisplayed() ) {
			PriceItem.click();
			driver.navigate().back();
			try { Thread.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }
		}
		try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
	}

	@Then("Delete Item")
	public void delete_item() {
		WebElement PriceItem = driver.findElement(By.xpath("//*[@value='Delete']"));		
		if( PriceItem.isDisplayed() ) {
			PriceItem.click();
		}
		try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
	}
	
	@And("Close")
	public void close() throws IOException {
		driver.close();
		configurations.close();
	}
	
	
	////////////////////////// Scenario3  /////////////////
	@Then("Sign In")
	public void sign_In() {
		WebElement Item = driver.findElement(By.id("nav-link-accountList"));		
		if( Item.isDisplayed() ) {
			Item.click();
		}
		try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
	}
	
	@When("Landed on Sign In")
	public void land_on_sogn_in() {
		WebElement Item = driver.findElement(By.id("auth-create-account-link"));
		if( Item.isDisplayed() ) {
			Item.click();
		}
		try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
	}
	
	@Then("Fill new user info")
	public void Fill_new_user_info() throws IOException {
		  	URL urlForGetRequest = new URL( prop.getProperty("API_URL") );
		    String readLine = null;
		    HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
		    conection.setRequestMethod("GET");		    
		    int responseCode = conection.getResponseCode();
		    if (responseCode == HttpURLConnection.HTTP_OK) {
		        BufferedReader in = new BufferedReader( new InputStreamReader(conection.getInputStream()) );
		        StringBuffer response = new StringBuffer();
		        while ((readLine = in .readLine()) != null) {
		            response.append(readLine);
		        } in .close();
		        JSONParser parser = new JSONParser();
		        try {
					JSONObject json = (JSONObject) parser.parse( response.toString() );
					JSONObject jsonData = (JSONObject) json.get("data");
					nameFromAPI = jsonData.get("employee_name").toString();
				} catch (ParseException e) { e.printStackTrace(); }
		    } else {
		        System.out.println("GET NOT WORKED");
		    }
		
		WebElement UserName = driver.findElement(By.id("ap_customer_name"));			
		if( UserName.isDisplayed() ) {
			UserName.sendKeys( nameFromAPI );
		} else {
			System.out.println( "Item not found 5555555555" );
		}
		
		WebElement UserEmail = driver.findElement(By.id("ap_email"));
		if( UserEmail.isDisplayed() ) {
			UserEmail.sendKeys( nameFromAPI.replaceAll("\\s+","") + "@fake.com" );
		} else {
			System.out.println( "Item not found 5555555555" );
		}
		
		WebElement UserPassword = driver.findElement(By.id("ap_password"));
		if( UserPassword.isDisplayed() ) {
			UserPassword.sendKeys("Password12355123s");
		} else {
			System.out.println( "Item not found 5555555555" );
		}
		
		WebElement UserPasswordRe = driver.findElement(By.id("ap_password_check"));
		if( UserPasswordRe.isDisplayed() ) {
			UserPasswordRe.sendKeys("Password12355123s");
		} else {
			System.out.println( "Item not found 5555555555" );
		}
		try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
	}
	
	@And("Select confitions of use")
	public void select_confitions_of_use() {
		WebElement Item = driver.findElement(By.cssSelector("#legalTextRow>a:nth-child(1)"));
		if( Item.isDisplayed() ) {
			Item.click();
		}
		try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
	}
	
	@When("Landed conditions of use")
	public void landed_conditions_of_use() {
		WebElement Item = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/div/div[3]/h1"));
		assertEquals("Conditions of Use", Item.getText());
	}
	
	@Then("^Seach for \"([^\"]*)\" on help page$")
	public void Seach_for_on_help_page(String S1) {
		WebElement elementName = driver.findElement(By.id("helpsearch"));
		elementName.sendKeys(S1);
		elementName.sendKeys(Keys.ENTER);
		try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
	}
	
	@And("^Select \"([^\"]*)\" on help page$")
	public void Select_item_on_help_page(String S1) {
		List<WebElement> ListElements = driver.findElements(By.xpath("/html/body/div[2]/div[2]/div[1]/div/div[3]/div/div/h2/a"));
		for (WebElement temp : ListElements) {
			String TempText = temp.getText();
			if ( TempText.toLowerCase().equals(S1.toLowerCase()) ) {
				temp.click(); 
				break; 
			}
        }
		try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
	}
	
	@And("Display sub menus")
	public void display_sub_menus() {
		List<WebElement> ListElements = driver.findElements(By.xpath("/html/body/div[2]/div[2]/div[1]/div/div[3]/div/div/div[1]/h4"));
		for (int i = 0; i < ListElements.size()-1  ; i++) {
			System.out.println( ListElements.get(i).getText() );
		}
		try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
	}	
}