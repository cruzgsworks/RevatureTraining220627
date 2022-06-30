package com.revature.days;

public class Days {
	private String daysOfTheWeek[];

	public Days() {
		this.daysOfTheWeek = new String[] { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday",
				"Sunday" };
	}

	public void outputDays() {
		for (String days : this.daysOfTheWeek) {
			System.out.println(days);
			if (days.charAt(0) == 'T' || days.charAt(0) == 'S') {
				System.out.println("GO TO THE GYM");
			} else {
				System.out.println("STAY HOME");
			}
		}
	}
}
