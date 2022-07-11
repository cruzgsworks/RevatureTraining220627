package com.revature;

import static org.junit.Assert.*;

import org.junit.Test;

import com.revature.daos.EmployeeDAO;

public class EmployeeDAOTest {

	@Test
	public void testGetEmployee() {
		EmployeeDAO eDAO = new EmployeeDAO();
		assertNotNull(eDAO.getEmployees());
		fail("Not yet implemented");
	}

}
