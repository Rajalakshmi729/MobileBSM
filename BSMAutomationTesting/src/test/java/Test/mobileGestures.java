package Test;

import org.testng.annotations.Test;

import PageObjects.BaseTest;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class mobileGestures extends BaseTest {
	
	AndroidDriver<AndroidElement>driver;
	
	@Test
	public void touchactions() {
		
		//TouchAction ta = new TouchAction(driver); or 
	//	TouchAction ta = new AndroidTouchAction(driver);
		//or use as anoynoumous inner class.
		new AndroidTouchAction(driver)
		.tap(TapOptions.tapOptions().withElement(ElementOption.element(null));
		
		
	}

}
