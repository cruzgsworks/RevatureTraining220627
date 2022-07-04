package com.revature;

import java.util.ArrayList;

import com.revature.models.*;

public class Launcher {

	public static void main(String[] args) {

		// ArrayList with type Animal interface
		ArrayList<Animal> animals = new ArrayList<Animal>();
		animals.add(new Cat());
		animals.add(new Dog());
		animals.add(new Snake());
		
		// Loop through the ArrayList and call their inherited and overridden methods
		animals.forEach(curAnimals -> animalActions(curAnimals));
	}
	
	public static void animalActions(Animal curAnimals) {
		System.out.println("--- " + curAnimals.getClass().getSimpleName() + " entity ---");
		curAnimals.makeNoise();
		curAnimals.walk();
		curAnimals.feed();
	}

}
