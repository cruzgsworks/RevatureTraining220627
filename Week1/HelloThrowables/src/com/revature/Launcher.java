package com.revature;

import com.revature.exceptions.MyCheckedException;

public class Launcher {
	public static void main(String[] args) throws MyCheckedException {
		System.out.println("--- Throw runtime exceptions for demonstration ---");

		System.out.println("Divide by zero");
		int i = 0;
		int i2 = 1;

		try {
			System.out.println("Result + " + (i2 / i));
		} catch (Exception e) {
			System.err.println(e.toString());
		}

		System.out.println("Access an array index that doesn't exist");

		int[] numbers = { 0, 1, 2 };

		try {
			System.out.println(numbers[3]);
		} catch (ArithmeticException e) { // Most specific exceptions first
			System.err.println(e.toString());
		} catch (Exception e) { // Most generic exceptions last. Like a catch-all block
			System.err.println(e.toString());
		}

		System.out.println("--- Try/Catch/Finally demonstration");

		try {
			System.out.println("Try block start");
			int j = 5 / 0;
			System.out.println("This part won't output because of exception " + j);
		} catch (NullPointerException e) {
			System.err.println(e.toString()); // Will not print because it will not throw this type of exception
		} catch (ArithmeticException e) {
			System.err.println(e.toString()); // Will print because it will throw an ArithmeticException
		} catch (Exception e) {
			System.err.println(e.toString()); // Will not run because it was already caught as a ArithmeticException
		} finally {
			System.out.println("Hello from finally block"); // Will always run 
		}
		
		System.out.println("--- Using custom Checked Exception ---");
		
		throwChecked();
		
		// We can either surround it with try/catch block or use "throws" declaration in the method.

	}
	
	// Creating method that throws MyCheckedException
	// Methods that throw CheckedExceptions will not let you compile unless you add the "throws" declaration
	// By using the "throw" declaration, it makes the ExceptionHandling the problem of the method that called it
	public static void throwChecked() throws MyCheckedException {
		System.out.println("I will throw a checked exception");
		
		// Throw keywords will manually throw an exception
		// Remember Exceptions are Objects.
		throw new MyCheckedException("Custom exception was thrown");
	}
	
	// Compiler checks for CheckedException before runtime
	// We need to handle them with try/catch blocks or "throws" declaration before our application run
	
}
