package com.revature.models;

public class Role {
	private int role_id;
	private String role_title;
	private String role_salary;

	public Role(int role_id, String role_title, String role_salary) {
		super();
		this.role_id = role_id;
		this.role_title = role_title;
		this.role_salary = role_salary;
	}
	
	public Role(String role_title, String role_salary) {
		super();
		this.role_title = role_title;
		this.role_salary = role_salary;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getRole_title() {
		return role_title;
	}

	public void setRole_title(String role_title) {
		this.role_title = role_title;
	}

	public String getRole_salary() {
		return role_salary;
	}

	public void setRole_salary(String role_salary) {
		this.role_salary = role_salary;
	}

	@Override
	public String toString() {
		return "Role [role_id=" + role_id + ", role_title=" + role_title + ", role_salary=" + role_salary + "]";
	}
	
}
