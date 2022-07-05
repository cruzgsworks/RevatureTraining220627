package com.revature.models;

public class Cat implements Animal {

	@Override
	public void makeNoise() {
		System.out.println("The cat purred");

	}

	@Override
	public void walk() {
		System.out.println("The cat made its signature walk");
	}

	@Override
	public void feed() {
		System.out.println("The cat drinks lactose free milk");
	}

}
