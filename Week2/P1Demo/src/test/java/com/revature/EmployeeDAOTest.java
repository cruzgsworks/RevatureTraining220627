package com.revature;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import com.revature.daos.EmployeeDAO;
import com.revature.utils.ConnectionUtil;

public class EmployeeDAOTest {

	@Test
	public void testGetEmployee() {
		EmployeeDAO eDAO = new EmployeeDAO();
		assertNotNull(eDAO.getEmployees());
	}
	
	@Test
	public void testConnection() {
		try {
			assertNotNull(ConnectionUtil.getConnection());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
