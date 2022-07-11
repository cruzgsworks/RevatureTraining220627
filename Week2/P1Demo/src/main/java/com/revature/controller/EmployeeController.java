package com.revature.controller;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.revature.daos.EmployeeDAO;
import com.revature.models.Employee;

import io.javalin.http.Handler;

public class EmployeeController {

	// We need an EmployeeDAO object to use its methods
	EmployeeDAO eDAO = new EmployeeDAO();

	// This Handler will get the HTTP GET Request for all employees, then collect
	// the data and send it back in an HTTP Response
	public Handler getEmployeeHandler = (ctx) -> {

		// what is ctx? it's the Context object!
		// This object contains a bunch of method that we can use to take in HTTP
		// Requests and send HTTP Responses

		// We need an ArrayList of Employees, courtesy of our EmployeeDAO
		ArrayList<Employee> employees = eDAO.getEmployees();

		// create a GSON object, which has methods to convert our Java object into JSON
		Gson gson = new Gson();

		// use the GSON .toJson() method to turn our Java into JSON String (JSON is
		// always in String format on the Java side)
		String JSONemployees = gson.toJson(employees); // employees is the ArrayList of our employee data

		// use ctx to provide an HTTP response containing our JSON string of employees
		// (which is what was requested)
		
		ctx.contentType("application/json");

		ctx.result(JSONemployees); // ctx.result() sends a response back (this is where our data goes)

		ctx.status(200); // ctx.status() sets the HTTP status code. 200 stands for "OK", the generic
							// success code.

	}; // semicolon after curly brace? That's lambdas for you.

}
