package com.revature;

import com.revature.controller.EmployeeController;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.revature.daos.EmployeeDAO;
import com.revature.models.Employee;
import com.revature.models.Role;
import com.revature.utils.ConnectionUtil;

import com.revature.daos.RoleDAO;

import io.javalin.Javalin;

public class Launcher {

	public void TestQueries() {
		System.out.println("--- Krusty Krab Employee Management System ---");

		try (Scanner scan = new Scanner(System.in)) {

			System.out.println(
					"1 - testConnection\n" + "2 - insertEmployee()\n" + "3 - getRoleById()\n" + "4 - getEmployees()");
			int choice = scan.nextInt();

			switch (choice) {
			case 1:
				// try-with-resources block. test our connection
				// catch an exception if the resources fails to open
				try (Connection conn = ConnectionUtil.getConnection()) {
					System.out.println("Connection successful");
				} catch (SQLException e) {
					System.err.println("Connection failed");
					e.printStackTrace();
				}
				break;
			case 2: // Instantiate an EmployeeDAO
				EmployeeDAO edao = new EmployeeDAO();

				Employee newEmp = new Employee("Ben", "Fishman", null);

				edao.insertEmployee(newEmp, 2);
				break;
			case 3:
				RoleDAO rdao = new RoleDAO();
				System.out.println("Enter role number:");
				int roleId = scan.nextInt();
				Role resultRole = rdao.getRoleById(roleId);
				System.out.println(resultRole.getRole_title());
				break;
			case 4:
				EmployeeDAO edao2 = new EmployeeDAO();
				ArrayList<Employee> myEmp = edao2.getEmployees();
				for (Employee e : myEmp) {
					System.out.println(e.getFirst_name());
				}

				break;
			default:
				System.out.println("Selected nothing");
				break;
			}
		}
	}

	public static void main(String[] args) {
		// Launcher launch = new Launcher();
		// TestQueries();

		Javalin myApp = Javalin.create(config -> {
			config.enableCorsForAllOrigins();
		}).start(8080); // port number

		EmployeeController ec = new EmployeeController();

		// GET Requests
		// myApp.get("/", ctx -> ctx.result("Hello World"));
		myApp.get("/employees", ec.getEmployeeHandler);

	}

}
