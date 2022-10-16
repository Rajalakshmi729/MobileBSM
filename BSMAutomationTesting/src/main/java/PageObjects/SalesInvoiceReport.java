package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SalesInvoiceReport extends BaseTest {
	
	public SalesInvoiceReport(AppiumDriver driver ) {
		//PageFactory.initElements(driver, this);
		//ObjectsRepo.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public void yesterdayReport() throws InterruptedException {
		cm.tapUsingCoordinates(125, 218);
		Thread.sleep(3000);
		cm.tapUsingCoordinates(514, 593);
	}
	
	

}
