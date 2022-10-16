package PageObjects;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;



public class varInclusiveTaxSale extends BaseTest {
	
	 public varInclusiveTaxSale(AppiumDriver driver ) {
			//PageFactory.initElements(driver, this);
						//ObjectsRepo.driver = driver;
					PageFactory.initElements(new AppiumFieldDecorator(driver), this);
				}
		 public void orderSummaryCalcInclusive(HashMap<String, String> testData) throws InterruptedException, IOException {
		 Thread.sleep(2000);
			cm.CustomerEditButton();
			Thread.sleep(2000);
			String customerName = cm.getCustomerName();
			System.out.println("The customer name is:"+" "+customerName);
			Thread.sleep(2000);
			String customerMobile = cm.getMobileCustomer();
			System.out.println("The customer mobile no is:"+" "+customerMobile);
			Thread.sleep(2000);
			cm.saveCustomer();
			Thread.sleep(2000);
			cm.clickCustNavigatePdtpage();
			Thread.sleep(1000);
			cm.SecondPrtList();
			Thread.sleep(2000);
			cm.getProductName(testData);
	        cm.tapUsingCoordinates(355, 1352);
		   Thread.sleep(3000);
	       cm.tapUsingCoordinates(311, 467);
		 Thread.sleep(2000);
		 cm.tapUsingCoordinates(348, 1349);
		 Thread.sleep(4000);
		 cm.tapUsingCoordinates(297, 485);
		 //We can fetch selling price from application in String format 
		 String SellingPrice = cm.getPriceWithoutTax();
		 double sellPriceD = Double.parseDouble(SellingPrice);
		 System.out.println("The selling Price: for product :"+testData.get("SellingPrice"));
		 System.out.println("********************************************************************************");
		 System.out.println("The Selling Price fetched from application:" +SellingPrice );
		 System.out.println("The SellingPrice(String to double) value is:" +sellPriceD );
		 System.out.println("The Selling Price value from excel: "+testData.get("SP"));
		 Assert.assertEquals((testData.get("SP")), SellingPrice);
		 System.out.println("Selling price from application and from excel Assertion Pass");
		 System.out.println("********************************************************************************");
		 System.out.println("The Number of Quantity is :"+testData.get("qty"));
		 System.out.println("********************************************************************************");
		// Get no.of quantity from application
		 String qtyNo1 = cm.qtyNo();
		 System.out.println("The Number of qunatity from application"+qtyNo1);
		 double qtyNo = Double.parseDouble(qtyNo1.substring(0, 1));
		 System.out.println("The no.of.Qty is(split Function):"+ " "+qtyNo);
		 Assert.assertEquals((testData.get("qty")), String.valueOf(qtyNo));
		 System.out.println("Number of quantity Fetched from application matches with excel Assertion Pass");
		 System.out.println("********************************************************************************");
		 System.out.println("The Tax Rate is: "+cm.getTaxRate());
		 System.out.println("********************************************************************************");
		 Thread.sleep(2000);
		 System.out.println("Number of Tax Rate Fetched from application matches with excel Assertion Pass");
		 Thread.sleep(2000);
		 driver.navigate().back();
		 Thread.sleep(2000);
         cm.DoneMethod();
		 Thread.sleep(2000);
		 System.out.println("The Calculation of Tax for :"+testData.get("TaxType")+ "Tax at the rate :"+testData.get("Tax")+"For Product Price :"+sellPriceD);
		 
		 // SellingPrice Formula: sellPriceD/1.18
		 double SellPriceWithoutTax = cm.Roundoff((sellPriceD/1.18));
		 System.out.println("The sellPrice(without Inclusive tax) :" + SellPriceWithoutTax );
		 Assert.assertEquals(testData.get("WithoutTaxAmt"),String.valueOf(SellPriceWithoutTax) );
		  Double qtyRate = cm.Roundoff(qtyNo * SellPriceWithoutTax);
		 System.out.println("The Round off value :"+qtyRate);
		 System.out.println("The (Qty*Rate):"+"("+qtyNo+"*"+SellPriceWithoutTax+") "+"SellingPriceWithoutTax"+" :"+" "+qtyRate);
		 Double TaxAmount = cm.Roundoff(sellPriceD -SellPriceWithoutTax );
		 System.out.println("Inclusive Tax Amount is"+" "+":"+TaxAmount);
		 Assert.assertEquals(testData.get("ActualTax"), String.valueOf(TaxAmount));
		 System.out.println("Tax amount Fetched from application matches with excel Assertion Pass");
		 System.out.println("********************************************************************************");
		 System.out.println("Application: SGST :"+cm.SgstTax(TaxAmount)+"CGST :"+cm.CgstTax(TaxAmount));
		 System.out.println("Excel: SGST :"+testData.get("SGST")+"CGST :"+testData.get("CGST"));
		 System.out.println("********************************************************************************");
		Double TotalAmount = cm.Roundoff(qtyRate + TaxAmount);
		 String sgst = cm.SgstTax(TaxAmount);
		 String cgst = cm.CgstTax(TaxAmount);
		 Assert.assertEquals(testData.get("SGST"), String.valueOf(sgst));
		 Assert.assertEquals(testData.get("CGST"), String.valueOf(cgst));
		 System.out.println("Tax of CGST and SGST Assertion Pass");
		 System.out.println("********************************************************************************");
//System.out.println("The customer buy "+" "+qtyNo+":"+" quantity"+" "+"@"+AmtWithoutTax+" "+"Tax charged:"+" "+Tax+": "+"TotalAmount is:"+" "+TotalAmount);
		 Thread.sleep(2000);
		 cm.tapUsingCoordinates(355,1349);
		 cm.PaymentSettle();
String s = cm.getPageSource();
cm.takeScreenshot(driver);
//if(s.contains(AmtWithoutTax+"")) {
	System.out.println("-------------------------------------------------------------------------------");
	System.out.println("-------------------------------------------------------------------------------");
	System.out.println("Calculation Validation 1");
	System.out.println("-------------------------------------------------------------------------------");
	System.out.println("Payment Details validation against actaul values in mobile Order Summary page");
	System.out.println("Payment Details");
	String custName[] = customerName.split(",");
	for(String custnam:custName )
	System.out.println("Customer name is: "+custnam);
	String mobNo[] = customerMobile.split(",");
	for(String MobNo:mobNo )
	System.out.println("Customer MobileNo is: "+MobNo);
	System.out.println("The Product name is: "+testData.get("ProductName"));
	System.out.println("-------------------------------------------------------------------------------");
	System.out.println("-------------------------------------------------------------------------------");
	System.out.println("Quantity: "+"Actual:"+qtyNo+" & "+"Expected:"+s.contains(qtyNo+""));
	//System.out.println("Rate: "+"Actual:"+qtyRate+" & "+"Expected:"+s.contains(qtyRate+""));
	//System.out.println("Tax %: "+"Actual:"+testData.get(Tax)+" & "+"Expected:"+testData.get(Tax));
	//System.out.println("Tax amount:(InclusiveTax): "+"Actual:"+Tax+" & "+"Expected:"+s.contains(Tax+""));
	//System.out.println("TotalAmount: "+"Actual:"+TotalAmount+" & "+"Expected:"+s.contains(TotalAmount+""));
	//System.out.println("The SGST Tax is: "+"Actual:"+sgst+" & "+"Expected:"+s.contains(sgst+""));
	//System.out.println("The SGST Tax is: "+"Actual:"+cgst+" & "+"Expected:"+s.contains(cgst+""));
	System.out.println("-------------------------------------------------------------------------------");
	System.out.println("Order Summary screen captured for Reference");
	System.out.println("-------------------------------------------------------------------------------");
	System.out.println("-------------------------------------------------------------------------------");
	System.out.println("Calculation Validation 2");
	System.out.println("-------------------------------------------------------------------------------");
	System.out.println("Payment Details validation against Excel calculation");
	System.out.println("Payment Details");
	String custName1[] = customerName.split(",");
	for(String custnam:custName )
	System.out.println("Customer name is: "+custnam);
	String mobNo1[] = customerMobile.split(",");
	for(String MobNo:mobNo )
	System.out.println("Customer MobileNo is: "+MobNo);
	System.out.println("The Product name is: "+testData.get("ProductName"));
	System.out.println("-------------------------------------------------------------------------------");
	System.out.println("-------------------------------------------------------------------------------");
	System.out.println("Quantity: "+"Actual:"+qtyNo+" & "+"Expected:"+testData.get("qty"));
	//System.out.println("Rate: "+"Actual:"+qtyRate+" & "+"Expected:"+testData.get("TaxRate"));
	//System.out.println("Tax %: "+"Actual:"+testData.get(Tax)+" & "+"Expected:"+testData.get(Tax));
	//System.out.println("Tax amount:(InclusiveTax): "+"Actual:"+Tax+" & "+"Expected:"+testData.get("ActualTax"));
	//System.out.println("TotalAmount: "+"Actual:"+TotalAmount+" & "+"Expected:"+testData.get("SellingPrice"));
	System.out.println("-------------------------------------------------------------------------------");
	System.out.println("-------------------------------------------------------------------------------");
	System.out.println("Order Summary screen captured for Reference");
	//System.out.println("The SGST Tax is: "+"Actual:"+sgst+" & "+"Expected:"+testData.get("SGST"));
	//System.out.println("The SGST Tax is: "+"Actual:"+cgst+" & "+"Expected:"+testData.get("CGST"));
	System.out.println("-------------------------------------------------------------------------------");
	System.out.println("-------------------------------------------------------------------------------");

//cm.tapUsingCoordinates(353, 1349);
//Thread.sleep(1000);
//System.out.println("The source is:" +s);
//byte[] content = s.getBytes();
//String contentdesc = content.toString();
//System.out.println("content is" + contentdesc);
//System.out.println("payment settled in cash is:"+" "+TotalAmount);
//double NRound = cm.decimal(TotalAmount);
//System.out.printf("%8.6f\n",NRound);
//System.out.println("Decimal portion of a number is:"+" "+NRound);
//Double decimalIs = TotalAmount - NRound;
//System.out.println("Extracted decimal is: "+decimalIs);
//System.out.println("The rounded value is"+ cm.Roundoff(NRound));
//String Total = CommonMethods.roundupOrdown(TotalAmount, cm.Roundoff(NRound));
//if(cm.Roundoff(NRound)< 0.50) {
//double d1 = 0.49;
//double d2 = 0.50;
//double temp = 450.60;
//if(cm.Roundoff(NRound)<d2) {
	df.setRoundingMode(RoundingMode.HALF_UP);
  //    System.out.println("Customer Pay Rs: " + dd.format(TotalAmount));
}
//else {
	//df.setRoundingMode(RoundingMode.FLOOR);
  //    System.out.println("Customer Pay Rs: " + df.format(TotalAmount));
//}
//cm.PaymentSettle();
//cm.Reports();
		 	 
	 }
	 
	// }


