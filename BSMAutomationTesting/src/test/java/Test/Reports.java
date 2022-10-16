package Test;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import PageObjects.BaseTest;
import reusableComponents.ExcelOperations;

public class Reports extends BaseTest {
	String filename = "C:\\Users\\Robin\\eclipse-workspace\\BSMAutomationTesting\\TestData\\AddSaleForInclusiveTax.xlsx";
	ExcelOperations excel = new ExcelOperations(filename, "InclusiveProducts");
	
	@Test (dataProvider = "TestData1")
	public void TestData(Object obj1) throws Exception {
	HashMap<String, String> testData = (HashMap<String, String>) obj1;
	//test.log(Status.INFO, "Test data used for execution is: "+ testData);
	//Thread.sleep(3000);
	//ITS.clickAddSale();
	Thread.sleep(3000);
	cm.Reports();
	Thread.sleep(3000);
	cm.salesInvoiceReport();
	Thread.sleep(3000);
	SIR.yesterdayReport();
	Thread.sleep(2000);
	cm.calendarApply();
	Thread.sleep(4000);
	//int j = cm.indexCounter();
	//cm.firstReportselect(3);
//	WebElement firstReport = driver.findElement(By.xpath("//android.view.View[contains(@content-desc, 'INV22' )and @index ='4']"));
//	TouchActions action = new TouchActions(driver);
//	action.scroll(firstReport, 627, 1367);
//	action.perform();
	cm.scrollDown();

	//firstReport.click();
	

	}
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
