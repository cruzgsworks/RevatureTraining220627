package com.revature;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeMap;

import com.revature.models.Pokemon;

public class Launcher {

	public static void main(String[] args) {
		System.out.println("--- Lists ---");

		// "ArrayLists" are the most common implementations of the "Lists" Interface
		// QC may ask different between Arrays and ArrayLists

		ArrayList<Pokemon> myPokemonList = new ArrayList<Pokemon>(); // An empty ArrayList to be filled later on

		// ArrayList.add() method let us add elements to the ArrayList.
		myPokemonList.add(new Pokemon("Charmander", "Fire"));
		myPokemonList.add(new Pokemon("Piplup", "Water"));
		myPokemonList.add(new Pokemon("Snorlax", "Normal"));
		myPokemonList.add(new Pokemon("Sableye", "Ghost"));
		myPokemonList.add(new Pokemon("Rattata", "Normal"));
		// Lists can contain duplicates
		myPokemonList.add(new Pokemon("Rattata", "Normal"));

		// Attempt to print ArrayList
		System.out.println(myPokemonList);

		// Use forEach method of ArrayLists to output each object in the list
		myPokemonList.forEach(aPokemon -> System.out.println(aPokemon));

		System.out.println("The pokemon at index #3 is " + myPokemonList.get(3));

		System.out.println("--- Sets ---");

		// Instantiate empty HashSet. Most common Set
		HashSet<Pokemon> myPokemonSet = new HashSet<Pokemon>();
		
		Pokemon charmander = new Pokemon("Charmander", "Fire");
		Pokemon rattata = new Pokemon("Rattata", "Normal");
		
		myPokemonSet.add(charmander);
		myPokemonSet.add(charmander); // Sets do not allow duplicates. This will still run.
		myPokemonSet.add(rattata);
		
		System.out.println(myPokemonSet);
		myPokemonSet.forEach(pokemon -> System.out.println(pokemon));
		
		// .contains() returns a boolean if the Set contains the specified object
		
		if(myPokemonSet.contains(charmander)) {
			System.out.println("If charmander exists in the set and used flamethrower!!");
		}

		System.out.println("--- Maps ---");
		// Instantiate a TreeMap, commonly used Map.
		// This TreeMap takes an integer for the key and a pokemon for the value
		
		TreeMap<Integer, Pokemon> pokeMap = new TreeMap<Integer, Pokemon>();
		// Why are we using "Integer"?
		// Maps and Collections can only use Objects! We have to use wrapper classes in place of primitive type variables.
		// In this case, we'll be using Wrapper class Integer.
		
		// We can put any value for the keys parameter in the put method.
		pokeMap.put(1, charmander);
		pokeMap.put(2, charmander);
		pokeMap.put(15, charmander);
		pokeMap.put(4, rattata);
		
		System.out.println(pokeMap);
		// We can find value using .get
		System.out.println(pokeMap.get(4));
		System.out.println(pokeMap.get(15));
		
	}

}
