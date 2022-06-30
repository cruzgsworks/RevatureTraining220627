package com.revature.models;

// Abstract class because of abstract keyword. It will implement Creature interface.
// Has access to maxLevel variable and eat method() of the creature interface.
// Abstract Classes are classes with at least one abstract method

public abstract class Pokemon implements Creature {

	// abstract class can have variables
	boolean fainted = false; // we'll give a default value
	public String type; // this will be null by default and filled in each unique pokemon object.
	
	// concrete method
	public void flee() {
		System.out.println("It got away");
	}
	
	// abstract method. All classes that extends this abstract class should implement fight method.
	public abstract void fight();
	
}
