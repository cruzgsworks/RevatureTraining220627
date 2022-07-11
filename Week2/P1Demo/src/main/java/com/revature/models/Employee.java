package com.revature.models;

// this class models the employees table in db
public class Employee {
	// variables for the employee class which must match the same of the columns of the employee db table
	private int employee_id;
	private String first_name;
	private String last_name;
	// Every employee has a role
	Role role;
	
	// no-args constructor
	public Employee() {
		super();
	}

	public Employee(int employee_id, String first_name, String last_name, Role role) {
		super();
		this.employee_id = employee_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.role = role;
	}

	public Employee(String first_name, String last_name, Role role) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.role = role;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Employee [employee_id=" + employee_id + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", role=" + role + "]";
	}

}
