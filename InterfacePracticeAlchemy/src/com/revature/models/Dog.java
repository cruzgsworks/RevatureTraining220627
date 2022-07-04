package com.revature.models;

public class Dog implements Animal {


	@Override
	public void makeNoise() {
		System.out.println("The dog barked");

	}

	@Override
	public void walk() {
		System.out.println("The dog ambles");
	}

	@Override
	public void feed() {
		System.out.println("The dog savors a bone");
	}

}
