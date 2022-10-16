package Test;

import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import PageObjects.BaseTest;
import reusableComponents.ExcelOperations;

public class varExclusiveTaxTest extends BaseTest {
	String filename = "C:\\Users\\Robin\\eclipse-workspace\\BSMAutomationTesting\\TestData\\AddSaleForVarExclusiveTax.xlsx";
	ExcelOperations excel = new ExcelOperations(filename, "ExclusiveProducts");
	
	@Test (dataProvider = "TestData1")
	public void TestData(Object obj1) throws Exception {
	HashMap<String, String> testData = (HashMap<String, String>) obj1;
	//test.log(Status.INFO, "Test data used for execution is: "+ testData);
	Thread.sleep(3000);
	cm.clickAddSaleOption();
	Thread.sleep(2000);
	vet.orderSummaryCalcExclusive(testData);
	
	//ETS.orderSummaryCalc(testData);
	//Thread.sleep(3000);
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



