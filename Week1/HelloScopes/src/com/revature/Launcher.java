package com.revature;

public class Launcher {

	// Class scoped variable. Non-access modifier "static" makes it a class scope
	// variable
	// Will assign a value during declaration of variable, initializing it.
	public static int i = 5;

	// Class scoped variable
	// Uninitialized variable. Will return default value for its data type. If it's
	// integer then it's 0.
	public static int i2;

	// This is a Class scoped reference variable.
	public static Launcher Launcher; // Uninitialized reference variables will default to null.
	// This is declared with no constructor. One reason is it could reserve memory
	// space for an object that may exist.

	// Instanced scope variable. No static keywords makes it a instance scoped
	// It belongs to each object of the class.
	// All objects of the class will have a double dub variable with a value of 5.2
	double dub = 5.2;

	@SuppressWarnings("static-access")
	public static void main(String[] args) {

		System.out.println("--- Class & Instance Scoped Variables --- ");
		System.out.println(i);
		System.out.println(i2);

		// This code will give an error because dub is not static or Class scope
		// variable.
		// System.out.println(dub);

		// Change value of static variable
		i2 = -98;
		// Output new value of static variable
		System.out.println(i2);

		System.out.println(
				"--- Non-static members. Instantiated an object first and then called the instance scope variable ---");
		Launcher myLauncher = new Launcher();

		// Try outputting an object
		// Gives out a memory address (?)
		System.out.println(myLauncher);

		// Output value of dub which is a variable of myLauncher object
		System.out.println(myLauncher.dub);

		System.out.println("--- Initialize another object of Launcher class ---");
		// Initialize another object of Launcher class
		Launcher anotherLauncher = new Launcher();
		System.out.println(anotherLauncher);
		anotherLauncher.dub = 12345.6;
		System.out.println(anotherLauncher.dub);

		// static variables should be accessed in a static way (not through an object).
		// This is just for show.
		anotherLauncher.i2 = 5000;
		// Outputs the same value for different object instances because i2 is a static
		// variable.
		System.out.println(myLauncher.i2);
		System.out.println(anotherLauncher.i2);

		System.out.println("--- Method/Block Scope ---");
		scopesMethod();

	}

	public static void scopesMethod() {
		// method scope variable. only visible within this method.
		double d = 25.90;
		if (d > 5) {
			double d2 = 30.50; // this block scope variable. only visible within the if block
			// d2 variable will output since the method to output it is in the same block scope
			System.out.println(d2);
			// d variable will also output because it's declared in the parent of this block scope.
			System.out.println(d);
		}
		
		// this code will produce an error since d2 variable is within if block scope.
		// System.out.println(d2);
		
		
	}

}
