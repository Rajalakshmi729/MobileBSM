package PageObjects;

import java.net.URL;
import java.text.DecimalFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import reusableComponents.CommonMethods;
import reusableComponents.ExtentSetup;

public class BaseTest{
	
	public static AndroidDriver<AndroidElement>driver = null;
	public static InclusiveTaxSale ITS;
	public static AddSaleMap asm;
	public static AddSaleEndToEnd aste;
	public static PurchasefromSupplier purchase;
	public static CommonMethods cm;
	public static ObjectRepo or;
	public static DecimalFormat df;
	public static ExclusiveTaxSale ETS;
	public static SalesInvoiceReport SIR;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static varExclusiveTaxSale vet;
	public static varInclusiveTaxSale vit;
	public static DecimalFormat dd;
	public static AddProduct addPrdt;
	public static TouchAction ta;
	 
	
	
	
//	public static AppiumDriver driver;
//	//public static SaleAdd sa;
//	public static AddSaleMap asm;
	//  SelectBrowser select = new SelectBrowser();
	  //ReadPropertiesFile prop = new ReadPropertiesFile();
	  
	  //protected WebDriver driver = null;
	  
	  @BeforeMethod
	  public static void launchBrowser() throws Exception{
		  DesiredCapabilities caps = new DesiredCapabilities();
			//caps.setCapability(AndroidMobileCapabilityType.BROWSER_NAME, "chrome");
			//	caps.setCapability("automationName", "UiAutomator1");
				caps.setCapability("automationName", "UiAutomator1");
			//	driver.manage().timeouts().implicitlyWait(600, TimeUnit.SECONDS);
			//	caps.setCapability("deviceName", "vivo1812");
				caps.setCapability("deviceName", "vivo1812");
			//	caps.setCapability("udid", "4H9DZPWCWOBAMZRO"); 
				caps.setCapability("udid", "4H9DZPWCWOBAMZRO");//Give Device ID of your mobile phone
				//caps.setCapability("platformName", "Android");
				caps.setCapability("platformName", "Android");
				caps.setCapability("platformVersion", "8.1.0");
				//caps.setCapability("platformVersion", "11");
				//caps.setCapability("appPackage", "com.infinix.infinix");
				caps.setCapability("appPackage", "com.infinix.infinix");
				//caps.setCapability("appActivity", "com.infinix.infinix.MainActivity");
				caps.setCapability("appActivity", "com.infinix.infinix.MainActivity");	//caps.setCapability("noReset", "true");
				//caps.setCapability("no",true);
				caps.setCapability("no",true);
		        //caps.setCapability("newCommandTimeout", 100000);
		        caps.setCapability("newCommandTimeout", 100000);
		        //caps.setCapability("noReset", true);
		        caps.setCapability("noReset", true);
		  //      caps.setCapability(MobileCapabilityType.APP, "C:\\Users\\Robin\\Desktop\\AndroidAPK");
		       //URL url = new URL("http://0.0.0.0:4723/wd/hub");
		        URL url = new URL("http://127.0.0.1:4723/wd/hub");
		    	driver = new AndroidDriver<AndroidElement>(url, caps);	  
		    	
	  }
	  @BeforeMethod
	  public static void setup() throws Exception {
		  
		  launchBrowser();
		//sa = new SaleAdd(driver);
		// asm = new AddSaleMap(driver);
		  
		  //aste = new AddSaleEndToEnd(driver);
		  purchase = new PurchasefromSupplier(driver);
		  cm = new CommonMethods();
		 // or = new ObjectRepo();
		  ITS = new InclusiveTaxSale(driver);
		  df = new DecimalFormat("0.00");
		  dd = new DecimalFormat("0.");
		  ETS= new ExclusiveTaxSale(driver);
		  SIR = new SalesInvoiceReport(driver); 	  
		  extent = new ExtentReports();
		  vet = new varExclusiveTaxSale(driver);
		  vit = new varInclusiveTaxSale(driver);
		  addPrdt = new AddProduct(driver);
		  //test = new ExtentSetup();
	  	  }
	  @AfterMethod
	  public void closeBrowser(){
//	    
//	    driver.close();
//	    driver.quit();
//	    
	  }

}