package HelloTesting;

import com.revature.Calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.AfterClass;

public class LibraryTest {

	static Calculator calc;

	@BeforeClass // makes a method run before test are done
	public static void initialize() {
		System.out.println("In the @beforeClass method");
		calc = new Calculator();
	}

	@AfterClass
	public static void uninitialize() {
		System.out.println("In the @beforeClass method");
		calc = null;
	}

	@Test
	public void testAddition() {
		System.out.println("Testing add method");
		int result = calc.add(5, 15);
		assertTrue(result == 20);
	}

	@Test
	public void testSubtraction() {
		System.out.println("Testing add method");
		int result = calc.subtract(20, 5);
		assertEquals(15, result);
	}

	/*
	 * Above are two positive tests - which are tests that take in valid input and
	 * test for valid output
	 */

	/*
	 * Below is a negative test which takes in invalid/erroneous input and tests for
	 * appropriate output
	 */

	@Test(expected = ArithmeticException.class)
	public void testZeroDivision() {
		System.out.println("TESTING DIVIDE BY ZERO");
		calc.divide(5, 0);
	}

	/*
	 * in JUnit 5, we would use: assertThrows(ArithmeticException.class, () ->
	 * {calc.divide(5, 0);}); INSTEAD of expected = ArithmeticException.class
	 */

}
