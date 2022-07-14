package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.revature.models.Employee;
import com.revature.models.Role;
import com.revature.utils.ConnectionUtil;

// DAO - Data Access Object. Layer of classes that directly talks with the database
// CRUD commands
public class EmployeeDAO implements EmployeeDAOInterface {

	@Override
	public boolean insertEmployee(Employee employee) {

		// at the top of every DAO method, a connection must be opened
		try (Connection conn = ConnectionUtil.getConnection()) {
			String SQL = "INSERT INTO employee (first_name, last_name, role_id_fk) VALUES (?, ?, ?);";

			// Instantiate a PreparedStatement to fill in the variables (?)
			PreparedStatement ps = conn.prepareStatement(SQL);

			// Fill in values using PreparedStatement
			ps.setString(1, employee.getFirst_name());
			ps.setString(2, employee.getLast_name());
			ps.setInt(3, employee.getRole_id_fk());

			System.out.println(ps.toString());

			int insertedRows = ps.executeUpdate();
			if (insertedRows > 0) {
				System.out.println("Inserted " + insertedRows + " records");
				System.out.println(employee.toString());
				return true;
			} else {
				System.out.println("Inserted none");
				return false;
			}

		} catch (SQLException e) {
			System.err.println("insertEmployee() - failed");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ArrayList<Employee> getEmployees() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			// query all employees
			String sql = "SELECT * FROM employee;";

			// Statement class from java.sql
			Statement s = conn.createStatement();

			ResultSet rs = s.executeQuery(sql);

			ArrayList<Employee> employeeList = new ArrayList<Employee>();
			while (rs.next()) {
				// System.out.println(rs.getInt("employee_id") + ", " +
				// rs.getString("first_name") + ", " + rs.getString("last_name"));
				// create Employee model object by using the all-args constructor
				Employee emp = new Employee(
						rs.getInt("employee_id"),
						rs.getString("first_name"),
						rs.getString("last_name"),
						null);

				// store role_id_fk value
				int roleFK = rs.getInt("role_id_fk");
				// System.out.println(roleFK);
				// create RoleDAO object
				RoleDAO rDAO = new RoleDAO();

				// get Role using the role_id_fk
				Role r = rDAO.getRoleById(roleFK);

				// Update role property of emp object
				emp.setRole(r);

				// Add emp object to arraylist
				employeeList.add(emp);
			}

			// return arraylist
			return employeeList;

		} catch (SQLException e) {
			System.err.println("getEmployees() - failed");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteEmployee(String pathParam) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "DELETE FROM employee WHERE employee_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, Integer.parseInt(pathParam));

			int updated = ps.executeUpdate();

			if (updated > 0) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			System.err.println("deleteEmployee() - failed");
			e.printStackTrace();
		}
		return false;
	}

}
