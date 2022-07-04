package com.revature.models;

public class Pokemon {

	// Variables for "Pokemon" class.
	// These variables will be private to demonstrate encapsulation.

	private String name;
	private String type;

	// One method
	public void fight() {
		System.out.println(this.name + " attacked");
	}
	
	// Boilerplate code - Code which most model classes will have
	// Example - constructors, getters & setters, toString method
	
	/**
	 * No-args constructor
	 */
	public Pokemon() {
		super();
	}
	
	// Prints out variables when you try to output the object.
	@Override
	public String toString() {
		return "Pokemon [name=" + name + ", type=" + type + "]";
	}

	/**
	 * All-args constructor
	 * @param name
	 * @param type
	 */
	
	// Getters and setters enables us to view and manipulate private variables.
	// This is part of Encapsulation concepts, one of the key concepts for data security.
	// We do not want certain variables to be viewed or manipulated from everywhere (different class etc). 
	// They have to make an instance of the class first which gives you access to the getters and setter methods limiting the scope.
	
	public String getName() {
		return name;
	}
	
	public Pokemon(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
