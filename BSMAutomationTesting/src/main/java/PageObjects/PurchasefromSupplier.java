package PageObjects;

import static org.testng.Assert.assertTrue;

import java.awt.Point;
import java.io.File;
import java.io.PrintStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.apache.commons.io.FileUtils;
import org.decimal4j.util.DoubleRounder;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.collect.ImmutableList;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.clipboard.ClipboardContentType;
import io.appium.java_client.clipboard.HasClipboard;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

public class PurchasefromSupplier extends BaseTest{
	String FTA = null;

	public PurchasefromSupplier(AppiumDriver driver ) {
		//PageFactory.initElements(driver, this);
		//ObjectsRepo.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	public void ClickOnAddPurchase() throws InterruptedException {
		Thread.sleep(5000);
		// driver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"+ Purchase\"]")).click();

		WebElement AddPurchase = driver.findElement(By.id("+ Purchase"));
		AddPurchase.click();
		System.out.println("Clicked on Purchase option");
	}
	public void ClickOnNewSupplier() throws InterruptedException {
		Thread.sleep(3000);
		WebElement AddSupplier = driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"New Supplier\"]"));
		AddSupplier.click();
	}
	//public void InputSupplierDetails(HashMap<String,String> testData) throws Exception {
	//	Thread.sleep(3000);
	//	List<MobileElement> supplierName = (List<MobileElement>) driver.findElementsByClassName("android.widget.EditText");
	//	Thread.sleep(2000);
	//	MobileElement SendValueSupplierName= (MobileElement) supplierName.get(1);
	//	Thread.sleep(2000);
	//	SendValueSupplierName.sendKeys(testData.get("SupplierName"));
	//	MobileElement SendValueSupplierMobile= (MobileElement) supplierName.get(2);
	//	Thread.sleep(2000);
	//	SendValueSupplierMobile.sendKeys(testData.get("Mobile"));
	//	driver.navigate().back();
	//	Thread.sleep(3000);
	//		cm.tapUsingCoordinates(271, 846);
	//		Thread.sleep(3000);
	//		WebElement state = driver.findElementByAccessibilityId("Select State");
	//		Thread.sleep(4000);
	//		cm.selectDropdownOption(state);
	//		Thread.sleep(2000);
	//		driver.switchTo().parentFrame();
	//		//state.sendKeys("Assam");
	//		Thread.sleep(3000);
	//		//driver.switchTo().frame("android:id/content");
	//		cm.tapUsingCoordinates(129, 166);
	//		//driver.findElement(By.xpath("//android.view.View[@content-desc='Andhra Pradesh']")).click();
	//		//List<MobileElement> states= driver.findElements(By.xpath("//android.view.View"));
	//		//for(MobileElement e:states ) {
	//			Dimension dimension = driver.manage().window().getSize();
	//		int start_x = (int) (dimension.width * 0.2);
	//		int start_y = (int) (dimension.height * 0.8);
	//		int end_x = (int) (dimension.width * 0.2);
	//		int end_y = (int) (dimension.height * 0.2);
	//		TouchAction touch = new TouchAction(driver);
	//		touch.press(PointOption.point(start_x,start_y)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
	//		.moveTo(PointOption.point(end_x,end_y)).release().perform(); 
	//	//	}
	public void EditSupplier() throws Exception {
		//Select one supplier from the Supplier list
		List<MobileElement> Suppliers = (List<MobileElement>) driver.findElementsByClassName("android.view.View");
		Thread.sleep(2000);
		MobileElement selectSupplier= (MobileElement) Suppliers.get(1);
		selectSupplier.click();
		//Enter value in invoice field text box
		List <MobileElement> Invoiceno = (List<MobileElement>) driver.findElementsByClassName("android.widget.EditText");
		MobileElement invoiceField = Invoiceno.get(0);
		invoiceField.click();
		Thread.sleep(2000);
		//Enter Text field invoice number as "INV1"
		invoiceField.sendKeys("INV1");
		Thread.sleep(3000);
		//Click on Next button
		WebElement next = driver.findElementByAccessibilityId("NEXT");
		next.click();
		Thread.sleep(4000);
		//Read the purchase price without tax, (if want to change purchase value)
		List<MobileElement> add = (List<MobileElement>) driver.findElementsByClassName("android.view.View");
		MobileElement clickAdd = add.get(0);
		clickAdd.click();
		Thread.sleep(3000);
		//price(without tax) - text box
		WebElement purpriceNtax = driver.findElement(By.xpath("//android.widget.EditText[@text='296.61']"));
		purpriceNtax.click();
		// using getText() method get the price(without tax)
		System.out.println(purpriceNtax.getText());
		// save the copied purchase price in variable
		double StoredpurpriceNtax = Double.parseDouble (purpriceNtax.getText());
		System.out.println("Price without tax" + " " +StoredpurpriceNtax );
		Thread.sleep(5000);
		driver.navigate().back();
		//Thread.sleep(3000);
		//MobileElement tax = (MobileElement) driver.findElementByXPath("//android.view.View[@content-desc='53.39']");
		//Thread.sleep(3000);
		//MobileElement  addproduct =  (MobileElement) driver.findElementByXPath("//android.view.View[@content-desc=\"ADD\" and @index = '0']");
		//addproduct.click();
		//	Thread.sleep(3000);
		//cm.tapUsingCoordinates(353, 1353);
		Thread.sleep(3000);
		//Mentioned webelement present or not, verify using isDisplayed method.
		WebElement TotalAmtWeb = driver.findElement(By.xpath("//android.view.View[@content-desc=\"350.00\"]"));
		WebElement TaxWeb = driver.findElement(By.xpath("//android.view.View[@content-desc=\"Tax(GST @ 18%)\"]"));
		WebElement ProductTotalWeb = driver.findElementByAccessibilityId("296.61");
		boolean TotalAmt = TotalAmtWeb.isDisplayed();
		boolean ProductTotal = ProductTotalWeb.isDisplayed();
		boolean tax = TaxWeb.isDisplayed();
		//	Assert.assertEquals(true, ProductTotalWeb.isDisplayed());
		//Assert.assertEquals(true, TaxWeb.isDisplayed());
		System.out.println("State of Total Amount" +" "+TotalAmt);
		System.out.println("State of Product Total" +" "+ProductTotal);
		System.out.println("State of tax" +" "+tax);
		//System.out.println(content.getAttribute("content-desc"));
		//Convert extracted web element as text and store in extractedTotalAmt 
		String extractedTotalAmt = TotalAmtWeb.toString();
		System.out.println("Extracted Total Amount" + extractedTotalAmt);
		// Split function to extract Total Amount 350
		String[] TA = extractedTotalAmt.split("content-desc=");
		//System.out.println("The first value is"+TA[0]);
		for (String a : TA) {
			System.out.println("The value is"+a.toString());
			FTA = a.toString();
		}
		//Extract correctly amount 350
		String Totalamount = FTA.substring(1, 7);
		//Extracted the total amount and stored in Amt
		Double Amt = Double.parseDouble(Totalamount);
		System.out.println("The Total amount is" +Totalamount);
		System.out.println("Amt is"+ Amt);
		Double AmtWithouttax =(Double) (Amt/1.18);
		Double withoutrounded = Amt/1.18;
		System.out.println("The Amount without tax" + withoutrounded);
		//long rounded = (long) (Math.round(AmtWithouttax*1000.0)/1000.0);
		Double trucateamt = cm.truncateNumber(withoutrounded, 2);
		System.out.println("Final Truncated amount" +trucateamt);
		System.out.println("After rounding off 2 decimal places" + AmtWithouttax);

		Assert.assertEquals(trucateamt, StoredpurpriceNtax, "The amount is not true");
		System.out.println("Actual(total) amount 350 matches with Expected(total) ");

		System.out.println(Totalamount);
		List<MobileElement> list = driver.findElements(By.xpath("//*"));
		System.out.println(list.size());
		for(int i=0;i<list.size();i++)
		{ if (list.get(i).getText()!= null) {
			String SeenText = list.get(i).getText();
			System.out.println(SeenText);
		}
		}
		Thread.sleep(2000);
		WebElement done = driver.findElementByAccessibilityId("DONE");
		done.click();
		//	Thread.sleep(3000);
		driver.getPageSource();
		//		if(driver.getPageSource().contains("Text to check")){
		//			System.out.println("Text is present");
		//			}else{
		//			System.out.println("Text is absent");
		//	}
		//
		Thread.sleep(3000);
		//MobileElement tax1 = (MobileElement) driver.findElementByXPath("//android.view.View[@content-desc='53.39']");
		//Thread.sleep(3000);
// If want to select next product from the cart.
		//MobileElement  addproduct =  (MobileElement) driver.findElementByXPath("//android.view.View[@content-desc=\"ADD\" and @index = '0']");
		//addproduct.click();
		//Thread.sleep(3000);
		cm.tapUsingCoordinates(353, 1353);
	//WebElement placePurchase = driver.findElement(By.xpath("//android.view.View[@content-desc=\"Place Purchase\"]"));
		//placePurchase.click();
	//	WebDriverWait wait = new WebDriverWait(driver,30);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.View[@content-desc=\"Place Purchase\"]"))).click();
		Thread.sleep(6000);
		// WebElement Tamt = driver.findElementByXPath("//android.view.View[@content-desc=\"350.00\"]");
		By TamtTamt = MobileBy.AccessibilityId("350.00");
		By placepurchase = MobileBy.xpath("//android.view.View[@content-desc=\"Place Purchase\"]");
		
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence actions = new Sequence(finger, 1);
		actions.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), 90, (int) -5.0));
		//TouchAction act = new TouchAction(driver);
		//act.tap(TapOptions.tapOptions().withElement(ElementOption.element(driver.findElement(placepurchase)))).perform();
		//act.longPress(PointOption.point(637, 1166)).tap(TapOptions.tapOptions().withElement(ElementOption.element(driver.findElement(TamtTamt)))).perform();
		Thread.sleep(3000);
		//TouchActions act1 = new TouchActions(driver);
		//act1.clickAndHold(driver.findElement(TamtTamt)).
		//((HasClipboard) driver).getClipboard(ClipboardContentType.PLAINTEXT); // get plaintext
		//System.out.println("The context is" +driver.getContext());
	      //  MobileElement element = (MobileElement) new WebDriverWait(driver, 30).
	        //        until(elementToBeClickable(MobileBy.AccessibilityId("Tamt")));
		String n = null;
		PerformPasteTextToEmptyElement(n);
	//	TouchActions ac = new TouchActions(driver);
      //  ac.click(driver.findElement(TamtTamt)).doubleClick(driver.findElement(TamtTamt)).contextClick(driver.findElement(TamtTamt)).perform();
      //   String ct = ac.longPress(PointOption.point(637, 1166)).perform().toString();
       //  System.out.println("CT is" +ct.toString());
		MobileElement tam = (MobileElement) driver.findElement(By.xpath("//android.view.View[@content-desc=\"350.00\"]"));
		TouchAction ac = new TouchAction(driver);
		TakesScreenshot ts = (TakesScreenshot)driver; 
		//WebElement srcFile = (WebElement) ac.longPress(PointOption.point(595, 1166)).tap(TapOptions.tapOptions().withElement(ElementOption.element((WebElement) driver.findElement(TamtTamt))));
		//System.out.println("The pointed value is"+ srcFile.toString());
			//	File srcFile1 = ts.getScreenshotAs(OutputType.FILE);
		//String desfile = "C:\\Users\\Robin\\eclipse-workspace\\BSMAutomationTesting\\target\\google.png";
		//File desfile1 = new File(desfile);
		
		//FileUtils.getFile(srcFile1, desfile);
		System.out.println("Started to get values");
		Thread.sleep(3000);
		//WebElement ctd = driver.findElement(By.xpath("//android.view.View[contains(@content-desc ,'350.00')]"));
		//System.out.println(ctd.getAttribute("content-desc"));
		if(driver.getPageSource().contains("350.00")){
			System.out.println("Text is present");
			String arr = driver.getPageSource().toString();
			System.out.println("The source is" +arr);
			//arr.contains(arr)
			arr.contains("350.00");
			System.out.println("Text is present 350");
			}else{
			System.out.println("Text is absent");
			}
//		String parent=driver.getWindowHandle();
//		Set<String>s=driver.getWindowHandles();
//
//		// Now iterate using Iterator
//		Iterator<String> I1= s.iterator();
//
//		while(I1.hasNext())
//		{
//
//		String child_window=I1.next();
//
//
//		if(!parent.equals(child_window))
//		{
//		driver.switchTo().window(child_window);
//
//		System.out.println(driver.switchTo().window(child_window).getTitle());
//
//		}
//		}
//		  List<MobileElement> list1 = driver.findElements(By.id("//*"));
//			System.out.println(list1.size());
//			for(int i=0;i<list1.size();i++)
//			{ if (list1.get(i).getText()!= null) {
//				String SeenText = list1.get(i).getText();
//				System.out.println(SeenText);
//			}
//			}

		//System.out.println("The custom element is" + contdesc.toString());
	}
	
	public static double roundAvoid(double value, int places) {
		double scale = Math.pow(10, places);
		return Math.round(value * scale) / scale;
	}
	
	public static void PerformPasteTextToEmptyElement(String elem)
    {
        WebElement element = driver.findElement(By.xpath("//android.view.View[@content-desc=\"350.00\"]"));

        //Long press at center of empty text field for paste option
        PerformLongPressOnCenterofElement(element);

        //touch action on paste option above input field
                //System.out.println("The copied text is" +elem);
    }

       public static void PerformLongPressOnCenterofElement(WebElement elem)
    {
        TouchAction action = new TouchAction(driver);
        action.longPress(PointOption.point(637,1166)).perform();
    }

}
