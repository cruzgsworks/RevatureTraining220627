package HelloTesting;

import com.revature.Calculator;

import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;

public class LibraryTest {
	
	static Calculator calc;
	
	@BeforeClass //makes a method run before test are done
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
		System.out.println("Testing add");
		int result = calc.add(5,15);
		assertTrue(result==20);
	}
}
