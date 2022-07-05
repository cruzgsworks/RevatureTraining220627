package com.revature;

import com.revature.models.Chikorita;

public class Launcher {

	public static void main(String[] args) {
		// instantiate a chikorita object
		Chikorita chick = new Chikorita();

		// concrete method from abstract class
		chick.flee();
		
		// Overridden abstract method from the abstract class
		chick.fight();
		
		// another overridden abstract method
		chick.gainLevel();
		
		// overridden abstract method from the interface
		chick.eat("berry");
		
		System.out.println(Chikorita.maxLevel); // maxlevel is a static variable
		System.out.println(chick.fainted);
		
	}

}
