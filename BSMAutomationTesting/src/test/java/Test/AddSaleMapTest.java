package Test;



import java.io.File;
import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import PageObjects.BaseTest;
import reusableComponents.ExcelOperations;




public class AddSaleMapTest extends BaseTest{
	String filename = "C:\\Users\\Robin\\eclipse-workspace\\BSMAutomationTesting\\TestData\\AddSaleTestData2.xlsx";
	ExcelOperations excel = new ExcelOperations(filename,"AddSaleTestData");
	
	@Test (dataProvider = "TestData1")
	public void TestData(Object obj1) throws Exception {
	HashMap<String, String> testData = (HashMap<String, String>) obj1;
	test.log(Status.INFO, "Test data used for execution is: "+ testData);
	//	HashMap<String, String> testData = excel.getTestDataInMap(1);	
//	System.out.println(testData);
//System.out.println(CustomerName);
//System.out.println(EmailId);
Thread.sleep(5000);
	    asm.SaveAddSale();
	    Thread.sleep(5000);
	    asm.EnterAddSale(testData);
		
	}


//	  @Test(dataProvider = "TestData1")
//	  public void LoginTestCase(String CustomerName, String EmailId) throws InterruptedException{
//	  //  System.out.println(MobileNo);
//	    System.out.println(CustomerName);
//	    System.out.println(EmailId);
//	    Thread.sleep(5000);
//	    sa.SaveAddSale();
//	    sa.AddCustomer(CustomerName, EmailId);
//	    Thread.sleep(5000);
//		WebElement AddCustomer = driver.findElement(By.xpath("//android.widget.Button[@index='6']"));
//		AddCustomer.click();
//		//AddCustomer.sendKeys(CustomerName);
//		WebElement AddMobileno = driver.findElement(By.xpath("//android.widget.EditText[@text='Enter mobile number']"));
//		AddMobileno.sendKeys(CustomerName);
//		Thread.sleep(5000);
//		WebElement AddName = driver.findElement(By.xpath("//android.widget.EditText[@text='Enter customer name']"));
//		AddName.sendKeys(EmailId);
		   
		 // static WebElement AddEmail = driver.findElement(By.xpath("//android.widget.EditText[@text='Enter email address']"));

//	    HomePage.LoginLinkWebElement(driver).click();
//	    LoginPage.EmailFieldWebElement(driver).sendKeys(username);
//	    LoginPage.PasswordFieldWebElement(driver).sendKeys(password);
//	    System.out.println(driver.getTitle());
	  

//	  @DataProvider(name = "TestData1")
//	  public Object[][] LoginTestData() throws Exception{
//	    ExcelReader ER = new ExcelReader();
//	    return ER.getExceData();
//	  }
	  @DataProvider (name = "TestData1")
		public Object[][] testDataSupplier() throws Exception {
			Object[][] obj = new Object[excel.getRowCount()][1];
			for (int i = 1; i <= excel.getRowCount(); i++) {
				HashMap<String, String> testData = excel.getTestDataInMap(i);
				obj[i-1][0] = testData;
			}
			return obj;
			
		}

	}
