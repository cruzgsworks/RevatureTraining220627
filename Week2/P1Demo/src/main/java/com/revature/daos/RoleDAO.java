package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.Role;
import com.revature.utils.ConnectionUtil;

public class RoleDAO implements RoleDAOInterface {

	@Override
	public Role getRoleById(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM roles WHERE role_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, id);

			// Submit query and executeQuery() method for it to return a ResultSet;
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Role role = new Role(rs.getInt("role_id"), rs.getString("role_title"), rs.getInt("role_salary"));
				return role;
			}

		} catch (SQLException e) {
			System.err.println("updateRoleSalary() - failed");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateRoleSalary(String title, int salary) {
		// TODO Auto-generated method stub

	}

}
