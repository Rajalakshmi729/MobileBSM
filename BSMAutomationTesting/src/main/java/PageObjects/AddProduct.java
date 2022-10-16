package PageObjects;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AddProduct {
	
	public AddProduct(AppiumDriver driver ) {
		//PageFactory.initElements(driver, this);
					//ObjectsRepo.driver = driver;
				PageFactory.initElements(new AppiumFieldDecorator(driver), this);
			}
	
	

}
