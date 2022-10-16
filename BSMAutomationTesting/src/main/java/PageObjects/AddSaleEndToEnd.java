package PageObjects;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;

public class AddSaleEndToEnd extends BaseTest{
	
	
	 public AddSaleEndToEnd(AppiumDriver driver ) {
			//PageFactory.initElements(driver, this);
						//ObjectsRepo.driver = driver;
					PageFactory.initElements(new AppiumFieldDecorator(driver), this);
				}

	
		public  void EnterAddSale(HashMap<String, String> testData) throws Exception {
			
		//	WebElement AddCustomer = driver.findElement(By.xpath("//android.widget.Button[@index='6']"));
				//		Thread.sleep(10000);
			WebDriverWait wait = new WebDriverWait(driver, 2000);
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//android.widget.Button[@index='6']")))).click();
	//	AddCustomer.click();
			Thread.sleep(2000);
		WebElement AddMobileno = driver.findElement(By.xpath("//android.widget.EditText[@text='Enter mobile number']"));
	//	WebDriverWait wait = new WebDriverWait(driver, 5000);
	//	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//android.widget.EditText[@text='Enter mobile number']")))).click();

		//AddCustomer.sendKeys(testData.get(AddCustomer));
		AddMobileno.click();Thread.sleep(2000);
		AddMobileno.sendKeys(testData.get("MobileNo")); Thread.sleep(2000);
		 WebElement AddName = driver.findElement(By.xpath("//android.widget.EditText[@text='Enter customer name']"));
		AddName.click();
		AddName.sendKeys(testData.get("CustomerName"));	Thread.sleep(2000);
		 WebElement AddEmail =driver.findElement(By.xpath("//android.widget.EditText[@text='Enter email address']"));
	   	AddEmail.click(); 		Thread.sleep(2000);
	    AddEmail.sendKeys(testData.get("EmailId"));	Thread.sleep(2000);
	    WebElement Save =driver.findElement(By.xpath("//android.view.View[@content-desc='Save Customer']"));
	    Save.click();Thread.sleep(2000);		
	}
	public  void SaveAddSale() {
		  System.out.println("Enter Sales");
		WebElement AddSale = driver.findElement(By.id("+ Add Sale"));
		   AddSale.click();
		   
	}
	public void addProductSale(HashMap<String,String> testData) throws InterruptedException {
		Thread.sleep(2000);
	//	WebElement AddProduct = driver.findElement(By.xpath("//android.view.View[@content-desc= 'ADD' or @index='0')]"));
		//List AddProducts= driver.findElementsByClassName("android.view.View");
		List<MobileElement> AddProducts = (List<MobileElement>) driver.findElementsByClassName("android.view.View");
Thread.sleep(2000);
MobileElement AddProduct1= (MobileElement) AddProducts.get(0);
		
		 	
AddProduct1.click();
		Thread.sleep(2000);
		//android.view.View[@content-desc="39000.00"]		//WebElement VarStorage = driver.findElement(By.xpath("//android.view.View[@content-desc='128GB/8GB']"));
		WebElement submit = driver.findElement(By.xpath("//android.view.View[@content-desc='24500.00']"));
		submit.click();
		Thread.sleep(2000);				
			//WebElement next=driver.findElement(By.xpath("//android.view.View[@content-desc='1 Items&#10;24500.00']"));
			Thread.sleep(3000);
//next.click();
			int x, y; x= 269; y=1349;
			tapUsingCoordinates(269, 1349);  
			Thread.sleep(2000);
			tapUsingCoordinates(250,1347);
		
			Thread.sleep(3000);
WebElement settle = driver.findElement(By.xpath("//android.view.View[@content-desc='SETTLE']"));
settle.click();

            		 //WebElement Varcolor = driver.findElement(By.xpath("//android.view.View[@content-desc='128GB/8GB']"));
		//Varcolor.click();
		Thread.sleep(3000);
	//	driver.findElement(By.linkText(@contains ,'Items'));
		
		
		}
	
	private void tapUsingCoordinates(int x, int y) {
		new AndroidTouchAction(driver).tap(PointOption.point(x,y)).perform();
	}
		
		
}
