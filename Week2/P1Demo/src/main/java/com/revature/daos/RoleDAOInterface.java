package com.revature.daos;

import com.revature.models.Role;

public interface RoleDAOInterface {
	
	// return role object
	Role getRoleById(int id);
	
	// updates salary for a given role
	void updateRoleSalary(String title, int salary);
	
	
}
