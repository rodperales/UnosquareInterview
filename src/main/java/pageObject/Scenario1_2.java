package pageObject;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Scenario1_2{
	WebDriver driver = null;
	
	public Scenario1_2(WebDriver driver2) {
		driver = driver2;
	}
	
	//General Interface 
	interface GeneralFunction {
		public void Function();
	}
	
	
	//Abstract Click Functionality
	abstract class ClickFunction implements GeneralFunction {
		public void Function() {
			System.out.println("ClickFunction being executed");
		}
	}
	
	//Abstract Send Keys
	abstract class SendKeysFunction implements GeneralFunction {
		public void Function() {
			System.out.println("SendKeysFunction being executed");
		}
	}
	
	//Search For
	class SearchFor extends SendKeysFunction {
		public void Function(String S1) {
			System.out.println("Function being executed");			
		}
	}
	
	@FindBy(how = How.XPATH, using = "/html/body/div[1]/header/div/div[1]/div[2]/div/form/div[2]/div[1]/input") 
	private WebElement elementNameSearchFor;
	
	public void SearchFor(String S1) {   
		elementNameSearchFor.sendKeys(S1);
		elementNameSearchFor.sendKeys(Keys.ENTER);
	}
}