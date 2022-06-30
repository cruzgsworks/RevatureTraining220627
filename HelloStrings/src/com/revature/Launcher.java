package com.revature;

import java.util.Scanner;

public class Launcher extends Object {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("--- Strings are immutable ---");
		String myString = "I am immutable";

		// String object does not have methods can directly change a String object
		// We can only make new instance of it to make it appear it has a new value.

		// This method of String object does not change the value of the string but it
		// returns a upper case version of the characters in the String
		myString.toUpperCase();

		System.out.println(myString);

		// This will make a new instance of myString object with a new value.
		myString = myString.toUpperCase();
		System.out.println(myString);

		String s1 = "Hi Java"; // New object in the String pool.
		String s2 = "Hi Java"; // This variable points to the same object.
		String s3 = "hi java"; // New object since the value is unique.
		String s4 = new String("Hi Java"); // This will make a new object because of the "new" keyword.

		System.out.println(s1 == s2); // true. they point to the same object in the String pool.
		System.out.println(s1.equals(s3)); // .equals() method compares values and s1 and s2 are identical.
		System.out.println(s1 == s3); // false. s1 and s3 are two different objects.

		System.out.println(s1.toUpperCase() == s3.toUpperCase());
		System.out.println(s1.toUpperCase().equals(s3.toUpperCase()));
		System.out.println(s1.equals(s3));

		System.out.println(s1 == s4); // false comparing two different objects
		System.out.println(s1.equals(s4)); // true. it compares object values despite being different objects

		// == compares the memory addresses.
		// .equals() compares values in Strings.

		System.out.println("--- String methods ---");

		String myPangram = "Sphinx of Black Quartz, Judge my vow";

		System.out.println(myPangram.length()); // returns int representing length of the String

		System.out.println(myPangram.charAt(0)); // first letter
		System.out.println(myPangram.charAt(myPangram.length() - 1)); // last letter

		// returns a string where characters in the String are between 2 indices.
		// Inclusive of the characters in the indices.
		System.out.println(myPangram.substring(5, 36));

		String[] words = myPangram.split(" ");

		System.out.println(words);

		// enhanced for loop. iterate through every element in the array.
		for (String word : words) {
			System.out.println(word);
		}

		System.out.println("--- Using Stringbuilder ---");

		// Make an instance of StringBuilder class with the value of myPangram
		StringBuilder sb = new StringBuilder(myPangram);
		System.out.println(sb);

		sb.reverse(); // reverse order of characters in StringBuilder
		sb.append("BENJAMIN"); // append string at the end of the StringBuilder
		sb.insert(12, "ELLIOTT"); // add "ELLIOTT" at the beginning of the String
		sb.delete(30, 35); // delete characters from first index to second index
		sb.replace(0, 5, "Hello from the replace method"); // replace string inclusive of the 2 indices.

		System.out.println(sb);

		String newString = sb.toString(); // returns the string value of the StringBuilder object.

		System.out.println(newString);

		Scanner scan;
		try {
			scan = new Scanner(System.in); // instantiate Scanner object
			System.out.println("What's your name? ");
			String name = scan.next(); // request input of string in the console
			System.out.println(name + "? That's a lovely name");
			System.out.println("What's your age? ");
			int i = scan.nextInt(); // request input of integer in the console
			System.out.println("Your age is " + i);
			System.out.println("What is the best pokemon starter");
			String pokemon = "";

			boolean showQuestion = true;
			while (showQuestion) { // loop until user inputs Mudkip
				pokemon = scan.next();
				switch (pokemon) {
				case "Mudkip":
					System.out.println("correct");
					showQuestion = false;
					break;
				default:
					System.out.println("incorrect");
				}
			}
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			scan = null;
		}

	}

}
