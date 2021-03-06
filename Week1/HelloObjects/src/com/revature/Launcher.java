package com.revature;

// imports go here so we can access classes from different packages.
import com.revature.models.Person;
import com.revature.models.Pirate;

public class Launcher {
	public static void main(String args[]) {

		// Initialize object 'gerard' of class 'Person'
		// no need to pass arguments in the constructor since it's only the default
		// no-args constructor
		Person gerard = new Person();

		// Output return value of sleep method (with passing any string value in the
		// argument) from Person class.
		System.out.println(gerard.name + " is " + gerard.gender + " and has a hair color of " + gerard.hairColor);
		System.out.println(gerard.sleep("Later World"));

		// Initialize a new object of Person class using the all-args constructor
		Person dane = new Person("Mary Dane", "Pink", "Female");
		// Output return value of sleep method (with passing any string value in the
		// argument) from Person class.
		System.out.println(dane.name + " is " + dane.gender + " and has a hair color of " + dane.hairColor);
		System.out.println(dane.sleep("Good day"));
		
		// Initialize a new object of Pirate class
		Pirate blackBeard = new Pirate();
		
		// Output fields of the object of the Pirate class in the console
		System.out.println(blackBeard.name);
		System.out.println(blackBeard.dubloon);
		System.out.println(blackBeard.sleep("argh"));
		
		Pirate whiteBeard = new Pirate("White Beard", "White", "Male", 100000);
		System.out.println(whiteBeard.name);
		System.out.println(whiteBeard.dubloon);
		System.out.println(whiteBeard.sleep("ay caramba"));
	}
}
