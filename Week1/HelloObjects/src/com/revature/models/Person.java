package com.revature.models;

// class meant to model a person. template or blueprint for all objects that falls under person class
// defines variables (properties) and methods (behavior) that a person can have

public class Person {

	// properties of a person
	public String name;
	public String hairColor;
	public String gender;

	// behavior of a person. takes one argument called words
	public String sleep(String words) {
		return "This person sleep talks: " + words;
	}

	// Constructors' purpose is to initialize fields in a given class
	// No-args constructor is constructor without arguments. It is the default
	// constructor.
	public Person() {
		this.name = "Mike Myers";
		this.hairColor = "Black";
		this.gender = "Male";
	}

	// All args constructor - initializes all fields in the given class
	public Person(String name, String hairColor, String gender) {
		this.name = name;
		this.hairColor = hairColor;
		this.gender = gender;
	}

}
