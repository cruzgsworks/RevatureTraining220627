package com.revature.models;

// interface contains field and abstract methods
// to be implemented by classes by taking abstract methods and implement/override by giving them a body.
public interface Creature {

	// interface variables are public, static, final by default. we don't have to specify them.
	int maxLevel = 100;
	
	// abstract method - no method body/implementation. public abstract by default.
	void eat(String food);
	
	// with the default body, we can write concrete methods in an interface
	// this is just for example
	default void someMethod() {
		// some code here
	}
	
}
