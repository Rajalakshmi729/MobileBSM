package Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.google.common.util.concurrent.Uninterruptibles;

import PageObjects.BaseTest;
import io.appium.java_client.android.AndroidElement;

public class SettingsTimeChange extends BaseTest {
	
	@Test
	public void settings() throws InterruptedException {
		//Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
		Thread.sleep(7000);
		//WebElement settings = driver.findElement(By.xpath("//android.view.View[@content-desc='Settings Tab 4 of 5' and @index ='4']"));
		WebElement settings = driver.findElement(By.xpath("//android.widget.FrameLayout//android.widget.FrameLayout//android.view.View//android.view.View//android.view.View//android.view.View[contains(@content-desc,'Settings')]"));
		Thread.sleep(2000);
		settings.click();
		Thread.sleep(4000);
		WebElement onlinestoreSet = driver.findElement(By.xpath("//android.widget.FrameLayout//android.widget.FrameLayout//android.widget.FrameLayout//android.view.View//android.view.View[contains(@content-desc,'Online')]"));
		Thread.sleep(2000);
		onlinestoreSet.click();
		By openingTime = By.xpath("//android.widget.ScrollView//android.view.View[contains(@content-desc, 'Opening time')]");
		cm.scroll(openingTime);
		
		Thread.sleep(2000);
		WebElement startTime = driver.findElement(By.xpath("//android.widget.FrameLayout//android.view.View//android.widget.Button[contains(@content-desc, 'SELECT TIME')]"));
		Thread.sleep(2000);
		startTime.click();
		Thread.sleep(2000);
		cm.tapUsingCoordinates(509, 832);
		Thread.sleep(2000);
		cm.tapUsingCoordinates(509, 835);
		Thread.sleep(2000);
		cm.tapUsingCoordinates(209, 832);
		WebElement ok = driver.findElement(By.xpath("//android.widget.Button[@content-desc='OK']"));
		cm.tapUsingWebElement(ok);
		
				//cm.tapUsingWebElement(settings);
		//cm.swipeOption(null, null);
	}
	
	

}
