package com.revature;

public class Launcher {

	// declaring class variables. private so it's only accessible within the class
	private String helloWorldText;
	// declaring class variables. public so it's only accessible in any classes within the project
	public int aNumber;

	// A constructor - initialize data when an object of the class is instantiated
	public Launcher() {
		helloWorldText = "Hello World! \nHello Java! \nI'm excited to Learn!";
		aNumber = 9000;
	}
	
	// a static method. Can be called without instantiating an object of the class
	public static void printText() {
		System.out.println("I'm from a static method");
	}

	// A void method - doesn't return any value
	public void printText(String myText) {
		System.out.println(myText);
	}

	// A integer method - must return an int value
	private int addNumbers(int num1, int num2) {
		return num1 + num2;
	}

	// The main method
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Launcher myLauncher = new Launcher();
		
		// print some string value in the console
		myLauncher.printText(myLauncher.helloWorldText);
		myLauncher.printText(Integer.toString(myLauncher.aNumber));
		
		// variables within the method scope
		// generate random numbers (returns double value) and type cast to int.
		int num1 = (int) Math.random();
		int num2 = (int) Math.random();

		// add numbers, convert integer to string and print to console.
		myLauncher.printText(Integer.toString(myLauncher.addNumbers(num1, num2)));
		
		// call a static method
		Launcher.printText();
		
		// invoke garbage collector
		System.gc();
	}

}
