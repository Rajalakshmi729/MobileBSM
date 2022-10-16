package PageObjects;

import java.io.IOException;
import java.math.RoundingMode;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class InclusiveTaxSale extends BaseTest {
	
	public InclusiveTaxSale( AppiumDriver driver) {
		//PageFactory.initElements(driver, this);
		//ObjectsRepo.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	//Click on purchase change Product To Inclusive tax, and place sale request for customer.
	public void itemsClick() throws InterruptedException {
		Thread.sleep(2000);
		cm.clickAddSaleOption();
		Thread.sleep(2000);
	}
	public void clickAddSale() throws InterruptedException {
		Thread.sleep(3000);
		cm.clickAddSaleOption();	
	}
	public void AddfirstProduct() throws InterruptedException {
		cm.addFirstProduct();
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
			cm.addFirstProduct();
			Thread.sleep(3000);
		 cm.tapUsingCoordinates(355, 1352);
		 Thread.sleep(3000);
		 cm.tapUsingCoordinates(311, 467);
		 Thread.sleep(2000);
		 String SellingPrice1 = cm.getPriceWithoutTax();
		 double SellingPrice = Double.parseDouble(SellingPrice1);
		 System.out.println("The SellingPrice is:"+ " "+SellingPrice);
		 String qtyNo1 = cm.qtyNo();
		 double qtyNo = Double.parseDouble(qtyNo1.substring(0, 1));
		 System.out.println("The no.of.Qty is:"+ " "+qtyNo);
		 Thread.sleep(2000);
		 cm.DoneMethod();
		 Double AmtWithoutTax = cm.Roundoff(SellingPrice/1.18);
		 Double qtyRate = cm.Roundoff(AmtWithoutTax);
		 System.out.println("The (Qty):"+qtyNo+"*"+SellingPrice+" "+"(Rate)"+":"+" "+qtyRate);
		 Double Tax = cm.Roundoff(SellingPrice- qtyRate);
		 System.out.println("Inclusive Tax is"+" "+":"+Tax);
		 Double TotalAmount = cm.Roundoff(qtyRate + Tax);
		 String sgst = cm.SgstTax(Tax);
		 String cgst = cm.CgstTax(Tax);
System.out.println("The customer buy "+" "+qtyNo+":"+" quantity"+" "+"@"+AmtWithoutTax+" "+"Tax charged:"+" "+Tax+": "+"TotalAmount is:"+" "+TotalAmount);
String s = cm.getPageSource();
cm.takeScreenshot(driver);
if(s.contains(AmtWithoutTax+"")) {
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
	System.out.println("Rate: "+"Actual:"+qtyRate+" & "+"Expected:"+s.contains(qtyRate+""));
	System.out.println("Tax %: "+"Actual:"+testData.get("Tax")+" & "+"Expected:"+testData.get("Tax"));
	System.out.println("Tax amount:(InclusiveTax): "+"Actual:"+Tax+" & "+"Expected:"+s.contains(Tax+""));
	System.out.println("TotalAmount: "+"Actual:"+TotalAmount+" & "+"Expected:"+s.contains(TotalAmount+""));
	System.out.println("The SGST Tax is: "+"Actual:"+sgst+" & "+"Expected:"+s.contains(sgst+""));
	System.out.println("The SGST Tax is: "+"Actual:"+cgst+" & "+"Expected:"+s.contains(cgst+""));
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
	System.out.println("Rate: "+"Actual:"+qtyRate+" & "+"Expected:"+testData.get("TaxRate"));
	System.out.println("Tax %: "+"Actual:"+testData.get("Tax")+" & "+"Expected:"+testData.get("Tax"));
	System.out.println("Tax amount:(InclusiveTax): "+"Actual:"+Tax+" & "+"Expected:"+testData.get("ActualTax"));
	System.out.println("TotalAmount: "+"Actual:"+TotalAmount+" & "+"Expected:"+testData.get("SellingPrice"));
	System.out.println("-------------------------------------------------------------------------------");
	System.out.println("-------------------------------------------------------------------------------");
	System.out.println("Order Summary screen captured for Reference");
	System.out.println("The SGST Tax is: "+"Actual:"+sgst+" & "+"Expected:"+testData.get("SGST"));
	System.out.println("The SGST Tax is: "+"Actual:"+cgst+" & "+"Expected:"+testData.get("CGST"));
	System.out.println("-------------------------------------------------------------------------------");
	System.out.println("-------------------------------------------------------------------------------");
}
cm.tapUsingCoordinates(353, 1349);
Thread.sleep(1000);
System.out.println("The source is:" +s);
byte[] content = s.getBytes();
String contentdesc = content.toString();
System.out.println("content is" + contentdesc);
System.out.println("payment settled in cash is:"+" "+TotalAmount);
double NRound = cm.decimal(TotalAmount);
System.out.printf("%8.6f\n",NRound);
//System.out.println("Decimal portion of a number is:"+" "+NRound);
Double decimalIs = TotalAmount - NRound;
//System.out.println("Extracted decimal is: "+decimalIs);
System.out.println("The rounded value is"+ cm.Roundoff(NRound));
//String Total = CommonMethods.roundupOrdown(TotalAmount, cm.Roundoff(NRound));
//if(cm.Roundoff(NRound)< 0.50) {
double d1 = 0.49;
double d2 = 0.50;
if(cm.Roundoff(NRound)<d2) {
	df.setRoundingMode(RoundingMode.HALF_UP);
      System.out.println("Customer will pay Rs: " + dd.format(TotalAmount));
}
else {
	df.setRoundingMode(RoundingMode.FLOOR);
      System.out.println("Customer will pay Rs: " + df.format(TotalAmount));
}
//cm.PaymentSettle();
//cm.Reports();

}
}