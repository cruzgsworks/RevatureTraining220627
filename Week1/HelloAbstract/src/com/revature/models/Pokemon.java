package com.revature.models;

// Abstract class because of abstract keyword. It will implement Creature interface.
// At least one abstract method
// Has access to maxLevel variable and eat method() of the creature interface.
// Abstract Classes are classes with at least one abstract method

public abstract class Pokemon implements Creature {

	// abstract class can have variables
	public boolean fainted = false; // we'll give a default value
	public String type; // this will be null by default and filled in each unique pokemon object.
	public int level = 5; // this a default value of 5
	
	// concrete method. has implementation
	public void flee() {
		System.out.println("It got away");
	}
	
	// abstract method. has no implementation. All classes that extends this abstract class should implement fight method.
	public abstract void fight();
	
	// Another abstract method.
	public abstract void gainLevel();
	
	// no args constructor exists
	
}
