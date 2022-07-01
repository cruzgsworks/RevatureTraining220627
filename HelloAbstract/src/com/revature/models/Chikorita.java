package com.revature.models;

// this concrete class extends pokemon abstract class
// more specific of generic pokemon abstract class
// pokemon also implements creature interface giving it all access to creature and pokemon fields.
public class Chikorita extends Pokemon {
	
	// some values unique to chikorita
	public String name = "Chikorita";
	public String type = "Grass";

	// eat method from creature interface
	@Override
	public void eat(String food) {
		System.out.println(this.name + " eats a " + food);
	}

	// fight method from pokemon abstract class
	@Override
	public void fight() {
		System.out.println(this.name + " used vine whip");
	}
	
	@Override
	public void gainLevel() {
		this.level++; //increment the level by one.
		System.out.println(this.name + " has grown a level: " + this.level);
	}
	
	public Chikorita() {
		super();
	}

	//
	public Chikorita(String name, String type) {
		// no-args constructor. super() calls no no-args constructor in Pokemon abstract class.
		super();
		this.name = name;
		this.type = type;
	}
	
	
	
}
