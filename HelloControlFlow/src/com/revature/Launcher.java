package com.revature;

public class Launcher {

	public static void main(String[] args) {
		// Using Math class to get a random number
		int randomNum = (int) Math.random();
		// '(int)' is a typecasting. Double to int typecasting removes the decimal
		// points.
		// Math.random() static method returns a double between 0.0 to 1.0
		// This will output zero
		System.out.println("Regular Random " + randomNum);

		// Output random value between 0 to 100 by multiplying random method return
		// value by 100.
		randomNum = (int) (Math.random() * 100);

		System.out.println("randomNum " + randomNum);

		System.out.println("--- if else statement ---");
		if (randomNum > 75) {
			System.out.println("Number is greater than 75");
		} else if (randomNum > 50) {
			System.out.println("Number is greater than 50");
		} else {
			System.out.println("randomNum is small");
		}

		System.out.println("--- while/do-while loops");

		int smallNum = 2;
		// while the expression in parenthesis evaluates to true the codes inside the
		// while block will always run.
		while (smallNum < 500) {
			smallNum += 100;
			System.out.println("smallNum has increased to: " + smallNum);
		}
		System.out.println("While loop has completed. Enjoy your new number");

		// Do-While will execute once and loops until while expression is false.
		do {
			smallNum -= 100;
			System.out.println("smallNum has decreased to: " + smallNum);
		} while (smallNum > 10);
		System.out.println("Do-While loop has completed. Enjoy your new number");

		System.out.println("--- For loop ---");

		int loopVictim = 0;

		// For loop will execute until it satisfies the condition.
		for (int i = 0; i < 10; i++) {
			loopVictim++;
			System.out.println("Value of loopVictim is " + loopVictim);
			System.out.println("iterator i is now " + i);
		}

		// For loop will run until the index is less than the length of the char array
		char[] myName = { 'G', 'E', ' ', 'R', 'A', 'R', 'D', 'Z', 'Y' };

		for (int i = 0; i < myName.length; i++) {

			// skip to the next iteration if current item in the array is either a space, Z
			// or Y character.
			if (myName[i] == ' ' || myName[i] == 'Z' || myName[i] == 'Y') {
				continue;
			}

			System.out.print(myName[i]);
		}
		
		System.out.println("\n --- Switch statement ---");

		// declare variable for switch evaluation
		int age = 30;

		// depending on the value of the "age" variable, one of the following cases will
		// run
		// anything that doesn't fall in any specific case, it will execute the default
		// block.
		switch (age) {
		case 1:
			break;
		case 24:
			System.out.println("You are 24 years old");
			// will continue to execute other cases below it without the break statement.
		case 21:
			System.out.println("You are 21 years old");
			break;
		case 22:
			System.out.println("You are 22 years old");
			break;
		default:
			System.out.println("Your age does not match any of the cases");
			break;
		}

	}

}
