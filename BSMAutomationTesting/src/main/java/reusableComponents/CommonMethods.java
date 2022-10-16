package reusableComponents;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.util.concurrent.Uninterruptibles;

import PageObjects.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidTouchAction;

import static io.appium.java_client.android.AndroidTouchAction.*;
import static io.appium.java_client.touch.TapOptions.*;

import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import static io.appium.java_client.touch.offset.ElementOption.*;

import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

public class CommonMethods extends BaseTest {
	public String b;
	//public int i=3;

	public void selectDropdownOption(WebElement state) throws Exception {
		state.click();
		Thread.sleep(3000);
		Select os = new Select(state);

		try {
			os.selectByVisibleText("Assam");
		} catch(Exception e) {
			throw new Exception("Value is not present in dropdown for WebElement: "+state + "Value to be selected is: ");
		}
	}
	public static void tapUsingCoordinates(int x, int y) {
		new AndroidTouchAction(driver).tap(PointOption.point(x,y)).perform();
	}

	public void clickAddSaleOption() throws InterruptedException {
		MobileElement addSale = (MobileElement) driver.findElement(By.id("+ Add Sale"));
		Thread.sleep(3000);
		addSale.click();		
	}
	public void CustomerEditButton() throws InterruptedException {
		Thread.sleep(1000);
		tapUsingCoordinates(430, 444);
	}
	public String getCustomerName() throws InterruptedException {
		Thread.sleep(2000);
		MobileElement CustomerName = (MobileElement) driver.findElement(By.xpath("//android.widget.EditText[@index = '3']"));
		CustomerName.click();
		String customerName = CustomerName.getText();
		return customerName;
	}
	public String getMobileCustomer() throws InterruptedException {
		Thread.sleep(2000);
		MobileElement Mobile = (MobileElement) driver.findElement(By.xpath("//android.widget.EditText[@index = '1']"));
		Mobile.click();
		String mobileNo = Mobile.getText();
		return mobileNo;
	}
	public void saveCustomer() throws InterruptedException {
		Thread.sleep(1000);
		MobileElement saveCustomer = (MobileElement) driver.findElementByAccessibilityId("Save Customer");
		saveCustomer.click();
	}

	public void clickFirstCustomer() throws InterruptedException {
		Thread.sleep(3000);
		MobileElement firstCustomer = (MobileElement) driver.findElementByXPath("//android.widget.ImageView[@index = '0']");
		firstCustomer.click();
	}
	public void addFirstProduct() throws InterruptedException {
		Thread.sleep(3000);
		WebElement firstProduct = driver.findElement(By.xpath("//android.view.View[@content-desc=\"ADD\" and @index = '0']"));
		firstProduct.click();
		Thread.sleep(2000);
		//String s =getPageSource();
		//System.out.println("The product Page source is:"+s);
		//cm.tapUsingCoordinates(354, 1357);
	}
	public static Double orderSummaryCalc() throws InterruptedException {
		Thread.sleep(2000);
		cm.tapUsingCoordinates(255, 423);
		Thread.sleep(3000);
		WebElement InclusivePrice = driver.findElement(By.xpath("//android.widget.EditText[@index = '3']"));
		//Ex amount Inclusiveamt = 390.00 
		String Inclusiveamt = InclusivePrice.getText();
		System.out.println("The selling price fetched from application is: "+" "+ Inclusiveamt);
		WebElement Taxvalue = driver.findElement(By.xpath("//android.view.View[@content-desc=\"GST @ 18%\"]"));
		//Ex: TaxRate = 18%
		String TaxRate = Taxvalue.toString();
		String [] TaxRate1 = TaxRate.split("@ ", 2);
		for(String a: TaxRate1)
			System.out.println("Tax fetched from application is:"+" " + a.toString().substring(0, 2)+" "+ Taxvalue.isDisplayed());	
		float inlAmt = Float.parseFloat(Inclusiveamt);
		//System.out.println("The"+Inclusiveamt+ "converted To Float value to perform calculation is:"+" "+ inlAmt);
		double amtWithoutTax = inlAmt/1.18;
		double RdamtWithoutTax = Roundoff(amtWithoutTax);
		System.out.println("The purchase amt divided with Inclusive TaxRate & rounded value is:"+" "+RdamtWithoutTax);
		//double RamtWithoutTax= amtWithoutTax;
		//System.out.println("Amount without tax"+ " "+ RamtWithoutTax);
		double TaxAmount = inlAmt - RdamtWithoutTax;
		double ta = Roundoff(TaxAmount);
		System.out.println("The Inclusive tax is:"+"(SP)"+inlAmt+"-"+RdamtWithoutTax+"(SPwithouttax)"+"="+ta);
		//Double Tax = truncateNumber(TaxAmount, 2);
		Double TotalAmount = amtWithoutTax + ta;
		System.out.println("Customer will pay TotalAmount Rs:" + " " + df.format(Math.ceil(TotalAmount)));
		String source = driver.getPageSource();
		//System.out.println("The Page Source is"+ " " +source);
		if(source.contains(Inclusiveamt)) {
			//assert.assertEquals(Inclusiveamt, df.format(Math.ceil(TotalAmount)), "Amount not matched");
			Assert.assertEquals(Inclusiveamt, df.format(Math.ceil(TotalAmount)), "Amount not matched");
			System.out.println("Verify amount actual amt mataches with expected amount using assertion functionality - Pass:"+" "+"Actual:"+" "+Inclusiveamt+" "+"Expected:"+" "+df.format(Math.ceil(TotalAmount)));
		}
		else {
			//Assert.assertEquals(Inclusiveamt+"0", Roundoff(TotalAmount), "Amount not matached");
			System.out.println("Total Amount is Wrong");
		}	
		byte[] sourceBytes = source.getBytes();
		//Thread.sleep(5000);
		return ta;
	}
	public void PaymentSettle() {
		WebElement settle = driver.findElementByAccessibilityId("SETTLE");
		settle.click();
	}
	public void DoneMethod() {
		WebElement done = driver.findElementByAccessibilityId("DONE");
		done.click();
	}
	public void ListofTextPage() {
		List<AndroidElement> list = driver.findElements(By.xpath("//*"));
		System.out.println(list.size());
		for(int i=0;i<list.size();i++)
		{ if (list.get(i).getText()!= null) {
			String SeenText = list.get(i).getText();
			System.out.println(SeenText);
		}
		}
	}
	public String pageSource() {
		return driver.getPageSource();

	}
	public void Reports() {
		cm.tapUsingCoordinates(223, 1357);
	}
	public void salesInvoiceReport() throws InterruptedException {
		//driver.findElementByAccessibilityId("Sales Invoices").click();
		driver.findElementByAccessibilityId("Sales Invoices").click();
		Thread.sleep(3000);
		//driver.findElement(By.xpath("//android.view.View[@index = '0']")).click();
	}
	public static Double truncateNumber(double n, int decimalplace)   
	{   
		//moves the decimal to the right   
		n = n* Math.pow(10, decimalplace);   
		//determines the floor value  
		n = Math.floor(n);   
		//dividing the floor value by 10 to the power decimalplace  
		n = n / Math.pow(10, decimalplace);   
		//prints the number after truncation  
		System.out.println("The number after Roundoff is: "+n);  
		return n;
	}   
	public static Double Roundoff(double number) {
		//DecimalFormat df = new DecimalFormat("0.00");
		double input = number;
		// DecimalFormat, default is RoundingMode.HALF_EVEN
		//  System.out.println("Tax : " + df.format(input));
		//or
		// df.setRoundingMode(RoundingMode.UP); //1205.6358
		// System.out.println("salary : " + df.format(input));      //1205.64
		String value = df.format(input)+"";
		// System.out.println("The value is by round off" +" "+value );
		Double tax = Double.parseDouble(value);

		return tax;

	}
	public static Double RoundUp(Double number) {
		Double input = number;
		df.setRoundingMode(RoundingMode.UP);
		System.out.println("salary : " + df.format(input));
		return input;
	}

	public static Double RoundDown(Double number) {
		Double input = number;
		df.setRoundingMode(RoundingMode.DOWN);
		System.out.println("salary : " + df.format(input));
		return input;
	}
	public String CgstTax(double number) {
		Double cgst = number/2;
		String value = df.format(cgst)+"";
		return value;

	}
	public String SgstTax(double number) {
		Double sgst = number/2;
		String value = df.format(sgst)+"";
		return value;
	}
	public void clickAddSale() throws InterruptedException {
		Thread.sleep(3000);
		cm.clickAddSaleOption();	
	}
	public Double orderSummaryCalcExclusive() throws InterruptedException, IOException {
		cm.tapUsingCoordinates(202, 423);
		Thread.sleep(3000);
		String SellingPrice = cm.getPriceWithoutTax();
		System.out.println("The Selling Price Without Tax:"+" "+SellingPrice);
		Float SP = Float.parseFloat(SellingPrice);
		String PageSource = driver.getPageSource();
		String ExtractedTax[] = PageSource.split("@ ", 2);
		for(String a:ExtractedTax) {
			System.out.println("Tax fetched from application:"+" "+a.toString());
		}
		String PriceWithoutTax = getPriceWithoutTax();
		Double PriceWithoutTax1 = Double.parseDouble(PriceWithoutTax);
		System.out.println("Price without Exclusive Tax is:"+" "+PriceWithoutTax);
		String noOfQty = qtyNo();
		noOfQty = noOfQty.substring(0,1);
		DoneMethod();
		System.out.println("Price without Exclusive Tax is:"+" "+noOfQty);
		Double Price = Double.parseDouble(noOfQty) * truncateNumber(PriceWithoutTax1,2);
		System.out.println("The Qty*Rate is:"+ noOfQty+"*"+ PriceWithoutTax +"="+truncateNumber(Price, 2));
		//Tax Calculation
		Double Tax = Price * 18/100; 
		System.out.println("The Exclusive tax amount is:"+" "+ Tax);
		Double TotalAmount = PriceWithoutTax1 + Tax;
		System.out.println("The Total amount is:"+" "+PriceWithoutTax1+ "+"+Tax+" = "+TotalAmount);
		String cgst = CgstTax(Tax);
		String sgst = SgstTax(Tax);
		System.out.println("The CGST and SGST amount is:"+"CGST:"+cgst+"&"+"SGST"+sgst);
		//String getPageSource();
		takeScreenshot(driver);
		cm.tapUsingCoordinates(348, 1352);
		return Tax;


	}
	public String getTaxRate() throws InterruptedException {		
		//WebElement Tax = driver.findElementByAccessibilityId("GST @ 18%");
		//or
		Thread.sleep(3000);
		MobileElement Tax = (MobileElement) driver.findElement(By.xpath("//android.view.View[@content-desc=\"GST @ 18%\"]"));
		Tax.click();
		WebElement Taxvalue = driver.findElement(By.xpath("//android.view.View[@content-desc=\"GST @ 18%\"]"));
		//Ex: TaxRate = 18%
		String TaxRate = Taxvalue.toString();
		String [] TaxRate1 = TaxRate.split("@ ", 2);
		for(String a: TaxRate1)

			System.out.println("Tax fetched from application is:"+" " +"and"+ a.toString().substring(0, 3)+" "+ Taxvalue.isDisplayed());	

		//String TaxContentDesc = Tax.getAttribute("@content-desc");

		//System.out.println("The Tax contentDesc"+Tax.getSize());
		//WebElement Taxvalue = driver.findElement(By.xpath("//android.view.View[@content-desc=\"GST @ 18%\"]"));
		return Tax.toString();
	}
	public void getProductName(HashMap<String, String> testData) throws InterruptedException {
		String Productname = testData.get("Product");
		WebElement clickOnPrdt = driver.findElement(By.xpath("//android.view.View[@content-desc=\""+Productname+"\"]"));
		Thread.sleep(2000);
		clickOnPrdt.click();
		System.out.println("The product Details is "+clickOnPrdt.toString());
		if(clickOnPrdt.toString().contains("iPhone 11"))
			System.out.println("The Product name is "+testData.get("ProductName"));
		else
			System.out.println("The product name is different");
	}
	public String getPriceWithoutTax() throws InterruptedException {
		Thread.sleep(2000);
		WebElement priceWithoutTax = driver.findElement(By.xpath("//android.widget.EditText[@index = '3']"));
		priceWithoutTax.click();
		return priceWithoutTax.getText();
	}
	public String qtyNo() {
		WebElement noOfQty = driver.findElement(By.xpath("//android.widget.EditText[@index = '6']"));
		return noOfQty.getText();
	}
	public void takeScreenshot(AppiumDriver driver) throws IOException 
	{ 		
		String filePath = System.getProperty("user.dir")+"\\target\\OrderSummary.png";
		// Convert web driver object into TakeScreenshot. 
		TakesScreenshot ts = (TakesScreenshot)driver; 
		// Call getScreenshotAs() method to create image file. 
		File scrFile = ts.getScreenshotAs(OutputType.FILE); 
		// Create an object of the file to move the image file to the new destination and pass the file path as an argument. 
		File desFile = new File(filePath); 
		// Call copyFile() method to save the file at destination. 
		FileUtils.copyFile(scrFile, desFile); 
		System.out.println("Taking Screenshots"); 

	}
	public String getPageSource() {
		return driver.getPageSource();
	}
	//	public void testScroll()throws Exception
	//    {
	//        for(int i=0;i<4;i++)
	//        {
	//            Thread.sleep(2000);
	//            if (driver.findElement(By.name("end_item")).isDisplayed())
	//            {
	//                driver.findElement(By.name("end_item")).click();
	//                break;
	//            }
	//            else
	//            {
	//                verticalScroll();
	//            }
	//
	//        }
	//}
	//	 public void verticalScroll()
	//	    {
	//	        Dimension size=driver.manage().window().getSize();
	//	        int y_start=(int)(size.height*0.60);
	//	        int y_end=(int)(size.height*0.30);
	//	        int x=size.width/2;
	//	        driver.s(x,y_start,x,y_end,4000);
	//	    }
	//	public void scrollDown() {
	//		Dimension dimension = driver.manage().window().getSize();
	//		int start_x = (int) (dimension.width * 1000);
	//		int start_y = (int) (dimension.height * 0.8);
	//		int end_x = (int) (dimension.width * 0.2);
	//		int end_y = (int) (dimension.height * 0.2);
	//		
	//		TouchAction Tact = new TouchAction(driver);
	//		Tact.longPress(PointOption.point(start_x, start_y)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
	//		.moveTo(PointOption.point(end_x, end_y)).release().perform();
	//		
	//		
	//	}
	public void scrollDown() {

		Dimension dimension = driver.manage().window().getSize();
		System.out.println("The Dimension is"+dimension);

		Double scrollHeightStart = dimension.getHeight() * 0.5;
		System.out.println("The start height is" +scrollHeightStart);
		int scrollStart = scrollHeightStart.intValue();
		Double scrollHeightEnd = dimension.getHeight() * 0.2;
		System.out.println("The end height is" +scrollHeightEnd);
		int scrollEnd = scrollHeightStart.intValue();

		//		TouchAction Tact = new TouchAction(driver);
		//		Tact.press(PointOption.point(0, scrollStart))
		//		.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
		//		.moveTo(PointOption.point(0,scrollEnd )).release().perform();
		TouchAction Tact = new TouchAction(driver);
		Tact.press(PointOption.point(0, scrollStart))
		.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
		.press(PointOption.point(0,scrollEnd )).longPress(PointOption.point(scrollStart, scrollEnd));

	}
	public void calendarApply() {
		WebElement Apply = driver.findElementByAccessibilityId("APPLY");
		Apply.click();
	}
	public int firstReportselect(int k ) {
		int j = k++;
		System.out.println("The value of k is:"+" "+k+"The value of i is:"+ j);
		//WebElement firstReport = driver.findElement(By.xpath("//android.view.View[@index = "+ "'"+j+"'"+"]"));
		WebElement firstReport = driver.findElement(By.xpath("//android.view.View[contains(@"
				+ "-desc='INV') and @index = "+ "'"+j+"'"+"]"));
		firstReport.click();
		System.out.println("After clicking value of k is:"+" "+ k);
		return k;
	}
	//	public int firstCounterZero() {
	//		int i=0;
	//		return i;
	//	}
	public void clickCustNavigatePdtpage() {
		MobileElement clickcustNavpdtpage = (MobileElement) driver.findElement(By.xpath("//android.widget.ImageView[@index = '0']"));
		clickcustNavpdtpage.click();
	}
	public void SecondPrtList() throws InterruptedException  {
		tapUsingCoordinates(357, 361);
		Thread.sleep(3000);
		WebElement firstPdt = driver.findElement(By.xpath("//android.view.View[contains(@content-desc, 'ADD') and @index = '0']"));
		firstPdt.click();
		WebElement capacity = driver.findElementByAccessibilityId("64GB");
		capacity.click();
		Thread.sleep(1000);
		WebElement color = driver.findElementByAccessibilityId("Green");
		color.click();
		Thread.sleep(2000);
		tapUsingCoordinates(370, 1352);
	}

	public static double decimal(double d){
		int dd = (int)d;
		// d -=dd;
		double m;
		m = d-dd;
		System.out.printf("%8.6f\n",m);
		return m;
	}
	public static String roundupOrdown(double Totalamt, double decimal) {
		long point = (long) .50;
		Totalamt = Totalamt * 1000;
		decimal = decimal *1000;
		if((decimal<point) ){
			df.setRoundingMode(RoundingMode.DOWN);
			String amt = String.valueOf(Totalamt);
			String amt1 = df.format(amt);

			//System.out.println("The formated total amount is:"+amt1 );
			return amt1;
		}
		else {
			df.setRoundingMode(RoundingMode.UP);
			String amt = String.valueOf(Totalamt);
			String amt1 = df.format(amt);
			System.out.println("The formated total amount is:"+amt1 );
			return amt1;
		}

	}
	public void clickProduct(By by) throws InterruptedException {

		//WebElement addProduct =driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='+ Items' and @index='4']"));

		//addProduct.click();
		Thread.sleep(3000);
		cm.tapUsingCoordinates(96, 759);
		Thread.sleep(2000);
		//WebElement newProduct = driver.findElement(By.xpath("//android.widget.Button[@content-desc='New Product' and @index='4' and and @text='']"));
		cm.tapUsingCoordinates(519, 1364);
		Thread.sleep(2000);
		//newProduct.click();
		WebElement selectCategory = driver.findElement(By.xpath("//android.widget.Button[@content-desc='Select Category' and @index='7' ]"));
		//WebElement selectCategory = driver.findElementByAccessibilityId("Select Category");
		selectCategory.click();
		Thread.sleep(4000);
		WebElement categoryList = driver.findElement(By.xpath("//android.view.View[@content-desc='Cakes']"));
		categoryList.click();
		Thread.sleep(4000);
		WebElement Units = driver.findElement(By.xpath("//android.view.View[@content-desc='PCS']"));
		Units.click();
		Thread.sleep(3000);
		driver.navigate().back();
		Thread.sleep(3000);
		WebElement units = driver.findElement(By.xpath("//android.view.View[@content-desc='CAN']"));
		//Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
		Thread.sleep(4000);
		//longPress(units);
		while(driver.findElements(by).isEmpty()) {
		Dimension windowSize = driver.manage().window().getSize();
		Double screenHeightStart =windowSize.getHeight()*0.5;
		int scrollStart = screenHeightStart.intValue();
		Double screenHeightEnd = windowSize.getHeight()*0.2;
		int scrollEnd = screenHeightEnd.intValue();
		int center = (int) (windowSize.width*0.5);
		new AndroidTouchAction(driver)//.press(PointOption.point(113,653))
				//.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
		.press(PointOption.point(center,scrollStart)).
		waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).
		moveTo(PointOption.point(center,scrollEnd)).release().perform();
		}
		if(!driver.findElements(by).isEmpty())
		{
		driver.findElement(by).click();
		}
		
	}
	public static void tapUsingWebElement(WebElement element) {
		new TouchAction(driver).tap(tapOptions().withElement(element(element))).perform();
	}
	
	public static void tapWithCoordinates(int x, int y) {
		//new TouchAction(driver);
		//find the center point which should be compatible with different mobile
		//new AndroidTouchActionta.tap(PointOption.point(x, y)).perform();
		new AndroidTouchAction(driver).tap(PointOption.point(x,y)).perform();
		
			
	}
	public static void longPress(WebElement element) {
		
		new AndroidTouchAction(driver).longPress(LongPressOptions.longPressOptions()
				.withElement(ElementOption.element(element)))
		.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(10))).perform();
		Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
	}
	//mistaken code
	public static void swipeOption(WebElement source, WebElement destination) {
		// same as drag and drop
new AndroidTouchAction(driver).tap(TapOptions.tapOptions().withElement(ElementOption.element(source)))
.moveTo(ElementOption.element(destination))
.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3))).release().perform();
	}
	//correct code
	// tap is used to just click on the option, long press used to hold the element for some time and swipe
	public static void swipeOptioncorrect(WebElement source, WebElement destination) {
		Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
		new AndroidTouchAction(driver).longPress(LongPressOptions.longPressOptions()
		.withElement(ElementOption.element(source))).moveTo(ElementOption.element(destination)).release().perform();
		Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
	}
    public static void dragAndDropOneWay(WebElement source, WebElement destination) {
    	
    	new AndroidTouchAction(driver).longPress(LongPressOptions.longPressOptions()
    	.withElement(ElementOption.element(source)))
    	.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
    	.moveTo(ElementOption.element(destination))
    	.release()
    	.perform();
    }
    //Instead waitAction we can use withDuration
    public static void dragAndDropSecondWay(WebElement source, WebElement destination) {
    	new AndroidTouchAction(driver).longPress(LongPressOptions.longPressOptions()
    			.withDuration(Duration.ofSeconds(2))
    			.withElement(ElementOption.element(source)))
    	        .moveTo(ElementOption.element(destination))
    	        .release().perform();
    	    }
    public static void scroll(By by) {
    	while(driver.findElements(by).isEmpty()) {
    		Dimension windowSize = driver.manage().window().getSize();
    		Double screenHeightStart =windowSize.getHeight()*0.5;
    		int scrollStart = screenHeightStart.intValue();
    		Double screenHeightEnd = windowSize.getHeight()*0.2;
    		int scrollEnd = screenHeightEnd.intValue();
    		int center = (int) (windowSize.width*0.5);
    		new AndroidTouchAction(driver)//.press(PointOption.point(113,653))
    				//.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
    		.press(PointOption.point(center,scrollStart)).
    		waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).
    		moveTo(PointOption.point(center,scrollEnd)).release().perform();
    		}
    		if(!driver.findElements(by).isEmpty())
    		{
    		driver.findElement(by).click();
    		}

    }
    
    
}