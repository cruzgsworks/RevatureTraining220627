package com.revature;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.daos.EmployeeDAO;
import com.revature.models.Employee;
import com.revature.utils.ConnectionUtil;

public class Launcher {

	public static void main(String[] args) {
		System.out.println("--- Krusty Krab Employee Management System ---");

		// Instantiate an EmployeeDAO
		EmployeeDAO edao = new EmployeeDAO();
		Employee newEmp = new Employee("Ben", "Fishman", null);
		
		edao.insertEmployee(newEmp, 2);

		// try-with-resources block. test our connection
		// catch an exception if the resources fails to open
		try (Connection conn = ConnectionUtil.getConnection()) {
			System.out.println("Connection successful");
		} catch (SQLException e) {
			System.err.println("Connection failed");
			e.printStackTrace();
		}

	}

}
