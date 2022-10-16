package Test;

import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.decimal4j.util.DoubleRounder;
import org.testng.annotations.Test;

public class doubleValues {
	
	@Test (enabled = false)
	public void doublevalue() {
		Double input = 49900.00;
		//BigDecimal salary3 = new BigDecimal(input).setScale(0, RoundingMode.);
	      //System.out.println("BigDecimal: " + salary3.doubleValue()*1.0);
	     // System.out.println("Decimal 0.00"+salary3.);
	      BigDecimal bigDecimal = BigDecimal.valueOf(Double.valueOf("123.42"));
	      System.out.println("BigDecimal: " + bigDecimal);
	      assertEquals(new BigDecimal(123.42).setScale(2, BigDecimal.ROUND_HALF_UP), bigDecimal);

	      // convert BigDecimal back to double
	     // double salary4 = salary3.doubleValue();
	 //     System.out.println("double : " + salary4);
	}
	@Test(enabled = true)
		public void doubleval() {
	double input = 49900.00;
    System.out.println("double : " + input);
    DoubleRounder.round(49900.00, 2);

  //  BigDecimal bd = new BigDecimal(input).setScale(2, RoundingMode.HALF_EVEN);
   // double newInput = bd.precision();

    System.out.println("double : " + LongRounder.round(49900.00d, 3));
	}
}
