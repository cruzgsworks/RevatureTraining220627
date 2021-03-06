package com.revature;

public class Launcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("--- Common primitives ---");

		// a int type variable
		int myInt = 50000000;

		// a long type variable
		long myLong = 5000000000000000000L;

		// concatenate int and long to string and print it in console
		System.out.println("Int type variable value: " + myInt);
		System.out.println("Long type variable value: " + myLong);
		System.out.println("Add long and int: " + (myInt + myLong));

		// a char type variable. must be enclosed in single quote.
		char c = 'c';
		// a char type variable can be a instantiated using unicode values
		char cUnicode = 15000;

		// normally output char type variables in console
		System.out.println(c);
		System.out.println(cUnicode);

		// unicode values add and displays new character
		System.out.println(c + cUnicode);

		// a double char type variable
		Double myDouble = Math.PI;

		// out double in console
		System.out.println("Double type variable value: " + myDouble);

		// a boolean value
		boolean myBool = true;

		// Compare two values
		boolean myBool2 = 5 > 10;

		// print boolean in console
		System.out.println(myBool);
		System.out.println(myBool2);

		// if else statement sample
		if (myBool) {
			System.out.println("If boolean or statement is true");
		} else {
			System.out.println("If boolean or statement is false");
		}

		int sum = 3 + 2;
		int difference = 3 - 2;
		int product = 3 * 2;
		int quotient = 20 / 2;
		int remainder = 30 % 20;
		System.out.println("Sum " + sum + "\nDifference " + difference + "\nProduct " + product + "\nQuotient " + quotient
				+ "\nRemainder " + remainder);

		// check for even
		int testNumber = 10;
		if (testNumber % 2 == 0) {
			System.out.println("even");
		} else {
			System.out.println("odd");
		}
		
		// increment and decrement
		int originalNum = 1;
		System.out.println("original " + originalNum);
		originalNum++;
		System.out.println("increment " + originalNum);
		originalNum--;
		System.out.println("decrement " + originalNum);

	}

}
