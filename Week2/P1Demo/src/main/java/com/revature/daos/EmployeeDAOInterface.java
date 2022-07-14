package com.revature.daos;

import java.util.ArrayList;
import com.revature.models.*;

// abstract methods only
// to be implemented later on in EmployeeDAO class
public interface EmployeeDAOInterface {

	// DAO classes get very long. this will be a way for us to easily the available
	// methods
	// quick reference

	// method to insert new employee

	// method to get employee by role title

	boolean insertEmployee(Employee employee);
	
	boolean deleteEmployee(String pathParam);

	ArrayList<Employee> getEmployees();

}
