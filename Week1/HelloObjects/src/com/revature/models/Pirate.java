package com.revature.models;

// Inheritance in java
// Pirate class inherits all properties and methods of Person class
public class Pirate extends Person {

	// This is a unique field of Pirate class. Parent class Person does not have
	// this field
	public int dubloon;

	// This is a unique method of Pirate class. Parent class Person does not have
	// this property.
	// Takes in one String argument
	// Returns String value
	public String pirateSpeak(String words) {
		return words;
	}

	// Overriding annotation to override sleep method in Pirate class since there's
	// an existing method with the same return type and number of arguments under
	// the Parent class.
	@Override
	public String sleep(String words) {
		return "This pirate sleep talks: " + words;
	}

	// Overloading example of sleep method in the Person class. Same method name but
	// different data type for the argument.
	public String sleep(int hours) {
		return "Sleeps for " + hours + " hours";
	}

	// No-args constructor of Pirate class. Calling super() method in order to call
	// the no-args constructor of Person class
	// Calls to Super(); are known as constructor chaining - calling a constructor
	// from within a constructor
	public Pirate() {
		super();
		this.dubloon = 4500;
	}

	// All-args constructor calling constructor of parent class plus the additional
	// fields of Pirate class
	public Pirate(String name, String hairColor, String gender, int dubloon) {
		super(name, hairColor, gender);
		this.dubloon = dubloon;
	}

}
