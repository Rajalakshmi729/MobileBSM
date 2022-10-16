package PageObjects;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;


public class AddSaleMap extends BaseTest {
	 public AddSaleMap(AppiumDriver driver ) {
			//PageFactory.initElements(driver, this);
						//ObjectsRepo.driver = driver;
					PageFactory.initElements(new AppiumFieldDecorator(driver), this);
				}
		
		public  void EnterAddSale(HashMap<String, String> testData) throws Exception {
			WebElement AddCustomer = driver.findElement(By.xpath("//android.widget.Button[@index='6']"));
			
		    
			 
			 
			 //WebElement AddProduct = driver.findElement(By.xpath("//android.view.View[@content-desc='ADD'])[1]"));

		AddCustomer.click();Thread.sleep(3000);
		//AddCustomer.sendKeys(testData.get(AddCustomer));
		WebElement AddMobileno = driver.findElement(By.xpath("//android.widget.EditText[@text='Enter mobile number']"));
		AddMobileno.click();Thread.sleep(5000);
		AddMobileno.sendKeys(testData.get("MobileNo")); Thread.sleep(5000);
		 WebElement AddName = driver.findElement(By.xpath("//android.widget.EditText[@text='Enter customer name']"));
		AddName.click();
		AddName.sendKeys(testData.get("CustomerName"));	Thread.sleep(5000);
		WebElement AddEmail =driver.findElement(By.xpath("//android.widget.EditText[@text='Enter email address']"));
	   	AddEmail.click(); 		Thread.sleep(5000);
	    AddEmail.sendKeys(testData.get("EmailId"));	Thread.sleep(5000);
	    WebElement Save =driver.findElement(By.xpath("//android.view.View[@content-desc='Save Customer']"));
	    Save.click();Thread.sleep(5000);		
	}
	public  void SaveAddSale() {
		  System.out.println("Enter Sales");
		WebElement AddSale = driver.findElement(By.id("+ Add Sale"));
		   AddSale.click();
	}
}
