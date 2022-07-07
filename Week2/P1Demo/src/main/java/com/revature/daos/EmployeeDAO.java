package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import com.revature.models.Employee;
import com.revature.utils.ConnectionUtil;

// DAO - Data Access Object. Layer of classes that directly talks with the database
// CRUD commands
public class EmployeeDAO implements EmployeeDAOInterface {

	@Override
	public void insertEmployee(Employee employee, int role_id) {

		// at the top of every DAO method, a connection must be opened
		try (Connection conn = ConnectionUtil.getConnection()) {
			String SQL = "INSERT INTO employee (first_name, last_name, role_id_fk) VALUES (?, ?, ?);";

			// Instantiate a PreparedStatement to fill in the variables (?)
			PreparedStatement ps = conn.prepareStatement(SQL);

			// Fill in values using PreparedStatement
			ps.setString(1, employee.getFirst_name());
			ps.setString(2, employee.getLast_name());
			ps.setInt(3, role_id);

			int insertedRows = ps.executeUpdate();
			if (insertedRows > 0) {
				System.out.println("Inserted " + insertedRows + " records");
				System.out.println(employee.toString());
			} else {
				System.out.println("Inserted none");
			}

		} catch (SQLException e) {
			System.err.println("insertEmployee() - Connection failed");
			e.printStackTrace();
		}

	}

	@Override
	public ArrayList<Employee> getEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

}
