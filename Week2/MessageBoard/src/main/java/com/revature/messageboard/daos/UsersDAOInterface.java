package com.revature.messageboard.daos;

import com.revature.messageboard.models.Response;
import com.revature.messageboard.models.Users;

public interface UsersDAOInterface {
	Response registerNewUser(Users user);

	Response createSuperAdmin(Users user);

	Users viewUserByUserName(String user_name);
	
	Users viewUserByUserID(int user_id);

	Response updateUser(Users user);
	
	Response deleteUser(String user_name);

	int getUserId(String user_auth_token);
}
