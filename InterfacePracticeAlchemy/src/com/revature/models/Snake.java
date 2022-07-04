package com.revature.models;

public class Snake implements Animal {

	@Override
	public void makeNoise() {
		System.out.println("The snake hissed");

	}

	@Override
	public void walk() {
		System.out.println("The snake slithers");
	}

	@Override
	public void feed() {
		System.out.println("The snake hunted and ate a rodent");
	}

}
