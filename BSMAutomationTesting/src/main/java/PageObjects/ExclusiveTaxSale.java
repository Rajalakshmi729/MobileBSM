package PageObjects;

import java.io.IOException;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import reusableComponents.CommonMethods;

public class ExclusiveTaxSale extends BaseTest {
	
	
	public ExclusiveTaxSale(AppiumDriver driver) {
		//PageFactory.initElements(driver, this);
		//ObjectsRepo.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	public void orderSummaryCalcExclusive(HashMap<String, String> testData) throws InterruptedException, IOException {
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
		Double Taxamt1 = cm.Roundoff(SellingPrice*18/100);
		Double Taxamt = cm.Roundoff(Taxamt1);
		Double NoQty = qtyNo*SellingPrice;
		System.out.println("The (Qty):"+qtyNo+"*"+SellingPrice+" "+"(Rate)"+":"+" "+NoQty);
		Double TotalAmount = cm.Roundoff(SellingPrice+Taxamt);
		System.out.println("Inclusive Tax is"+" "+":"+Taxamt);
		String sgst = cm.SgstTax(Taxamt);
		String cgst = cm.CgstTax(Taxamt);
		System.out.println("The customer buy "+" "+qtyNo+":"+" quantity"+" "+"@"+SellingPrice+" "+"Tax charged:"+" "+Taxamt+": "+"TotalAmount is:"+" "+TotalAmount);
		String s = cm.getPageSource();
		//test.log(Status.INFO, "Test data used for execution is: "+ testData);
		cm.takeScreenshot(driver);
		if(s.contains(SellingPrice+"")) {
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
			System.out.println("Rate: "+"Actual:"+SellingPrice+" & "+"Expected:"+s.contains(SellingPrice+""));
			System.out.println("Tax %: "+"Actual:"+testData.get("Tax")+" & "+"Expected:"+testData.get("Tax"));
			System.out.println("Tax amount:(ExclusiveTax): "+"Actual:"+Taxamt+" & "+"Expected:"+s.contains(Taxamt+""));
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
			System.out.println("Rate: "+"Actual:"+SellingPrice+" & "+"Expected:"+testData.get("SellingPrice"));
			System.out.println("Tax %: "+"Actual:"+testData.get("Tax")+" & "+"Expected:"+testData.get("Tax"));
			System.out.println("Tax amount:(ExclusiveTax): "+"Actual:"+Taxamt+" & "+"Expected:"+testData.get("ActualTax"));
			System.out.println("TotalAmount: "+"Actual:"+TotalAmount+" & "+"Expected:"+testData.get("TotalAmount"));
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
		//byte[] content = s.getBytes();
//		String contentdesc = content.toString();
//		System.out.println("content is" + contentdesc);
		//Double roundoff = cm.RoundDown(TotalAmount);
		//System.out.println("Amount before roundoff:"+" "+roundoff);
		
	//	Double ActualAmt = TotalAmount - roundoff;
		//System.out.println("Rounded difference is:"+" "+ActualAmt);
		//Double actualamt = TotalAmount - ActualAmt;
		//System.out.println("Customer paid amount:"+" "+actualamt);
		double NRound = cm.decimal(TotalAmount);
		System.out.printf("%8.6f\n",NRound);
		System.out.println("Decimal portion of a number is:"+" "+NRound);
		Double decimalIs = TotalAmount - NRound;
		//System.out.println("Extracted decimal is: "+decimalIs);
		System.out.println("The rounded value is"+ cm.Roundoff(NRound));
	//String Total = CommonMethods.roundupOrdown(TotalAmount, cm.Roundoff(NRound));
		//if(cm.Roundoff(NRound)< 0.50) {
		double d1 = 0.49;
		double d2 = 0.50;
		
		if(cm.Roundoff(NRound)<d2) {
			df.setRoundingMode(RoundingMode.HALF_UP);
		      System.out.println("Customer will pay Rs : " + dd.format(TotalAmount));
		}
		else {
			df.setRoundingMode(RoundingMode.FLOOR);
		      System.out.println("Customer will pay Rs : " + df.format(TotalAmount));
		}
		//Double Totalamt = Double.parseDouble(Total);
//	System.out.println("Customer will pay: "+ TotalAmount);
		Thread.sleep(2000);
		//cm.PaymentSettle();
		//cm.Reports();

	}
}
