package PageObjects;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.Duration;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Test extends BaseTest {
	//public static AppiumDriver driver;
	
	public static void RoundNearestGreaterValue() {
		DecimalFormat df = new DecimalFormat("0.00");
		double input = 460.2;
		String fr = input+"";
	//String vl = (String) fr.split(".")[1];	
	System.out.println("The split value is"+ fr.split(".").toString());
				double d = 1.548 ;
        System.out.println( d % 1 );

	     // System.out.println("salary : " + input);

	      // DecimalFormat, default is RoundingMode.HALF_EVEN
	      System.out.println("Tax : " + df.format(input));
		String value = df.format(input)+"";
		System.out.println("The value is" + " "+ value);
		if(input<.50) {
//			String sp[] = fr.split(".");	
//			for(String a:sp)
//			System.out.println("The amount roundedup"+ a);
		}
		else {
			System.out.println();
		}
	}
	public static void touchActions() throws InterruptedException {
		cm.Reports();
		Thread.sleep(3000);
		TouchAction Tact = new TouchAction(driver);
		Tact.longPress(PointOption.point(92, 577)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
		.moveTo(PointOption.point(573, 1371)).release().perform();
	}
	static void decimal(double d){
		int dd = (int)d;
		d -=dd;
		//return d;
		
         System.out.printf("%8.6f\n",d);
	}

	public void doublevalue() {
		Double input = 49900.00;
		BigDecimal salary3 = new BigDecimal(input).setScale(2, RoundingMode.HALF_UP);
	      System.out.println("BigDecimal: " + salary3);

	      // convert BigDecimal back to double
	      double salary4 = salary3.doubleValue();
	      System.out.println("double : " + salary4);
	}
	public static void main(String[] args) {
		RoundNearestGreaterValue();
		double d = 6.328941;
		decimal(d);
	}
	

			
}
