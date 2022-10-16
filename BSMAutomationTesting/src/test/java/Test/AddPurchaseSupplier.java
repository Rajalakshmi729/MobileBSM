package Test;

import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import PageObjects.BaseTest;
import reusableComponents.ExcelOperations;

public class AddPurchaseSupplier extends BaseTest {
	String filename = "C:\\Users\\Robin\\eclipse-workspace\\BSMAutomationTesting\\TestData\\AddPurchaseSupplier.xlsx";
	ExcelOperations excel = new ExcelOperations(filename, "AddPurchaseSupplier");
	
	@Test (dataProvider = "TestData1")
	public void TestData(Object obj1) throws Exception {
	HashMap<String, String> testData = (HashMap<String, String>) obj1;
	test.log(Status.INFO, "Test data used for execution is: "+ testData);
	purchase.ClickOnAddPurchase();
	//addsupplier.ClickOnNewSupplier();
	//addsupplier.InputSupplierDetails(testData);
	purchase.EditSupplier();
		//addsupplier.EnterPurchase(testData);
//	    Thread.sleep(8000);
//	    aste.EnterAddSale(testData);
//	    Thread.sleep(9000);
//	    aste.addProductSale(testData);
//	    Thread.sleep(3000);	   
	   
	 	   		
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



