package com.revature.messageboard.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.commons.lang3.StringUtils;

import com.revature.messageboard.controller.AuthController;
import com.revature.messageboard.models.Response;
import com.revature.messageboard.models.Users;
import com.revature.messageboard.utils.ConnectionUtil;

public class UsersDAO implements UsersDAOInterface {

	@Override
	public Response createSuperAdmin(Users user) {
		// at the top of every DAO method, a connection must be opened
		try (Connection conn = ConnectionUtil.getConnection()) {
			String SQL = "SELECT COUNT(*) ct FROM messageboard.users WHERE user_name = ?";

			// Instantiate a PreparedStatement to fill in the variables (?)
			PreparedStatement ps = conn.prepareStatement(SQL);

			ps.setString(1, user.getUser_name());

			ResultSet checkUser = ps.executeQuery();

			if (checkUser.next()) {
				if (checkUser.getInt("ct") < 1) {
					SQL = "INSERT INTO messageboard.users"
							+ "(user_name, user_pass, user_first_name, user_last_name, user_email, user_auth_token, user_auth_expiration, is_superadmin) "
							+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?);";

					ps = conn.prepareStatement(SQL);

					// Fill in values using PreparedStatement
					ps.setString(1, user.getUser_name());
					ps.setString(2, user.getUser_pass());
					ps.setString(3, user.getUser_first_name());
					ps.setString(4, user.getUser_last_name());
					ps.setString(5, user.getUser_email());
					ps.setNull(6, Types.VARCHAR); // Cookie String Null
					ps.setNull(7, Types.TIMESTAMP_WITH_TIMEZONE); // Cookie Expiration Null
					ps.setBoolean(8, user.isIs_superadmin());

					int insertedRows = ps.executeUpdate();
					if (insertedRows > 0) {
						// System.out.println("Success. User was added");
						return new Response(200, "Created new superadmin.");
					}
				} else {
					return new Response(400, "Username already exists.");
				}
			} else {
				throw new SQLException("Failed creating new superadmin.");
			}

		} catch (SQLException e) {
			System.err.println("SQL error in createSuperAdmin().");
			e.printStackTrace();
		}
		return new Response(400, "Failed in creating superadmin.");
	}

	@Override
	public Response registerNewUser(Users user) {
		// at the top of every DAO method, a connection must be opened
		try (Connection conn = ConnectionUtil.getConnection()) {
			String SQL = "SELECT COUNT(*) ct FROM messageboard.users WHERE user_name = ?";

			// Instantiate a PreparedStatement to fill in the variables (?)
			PreparedStatement ps = conn.prepareStatement(SQL);

			ps.setString(1, user.getUser_name());

			ResultSet checkUser = ps.executeQuery();

			if (checkUser.next()) {
				if (checkUser.getInt("ct") < 1) {
					SQL = "INSERT INTO messageboard.users"
							+ "(user_name, user_pass, user_first_name, user_last_name, user_email, user_auth_token, user_auth_expiration, is_superadmin) "
							+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?);";

					ps = conn.prepareStatement(SQL);

					// Fill in values using PreparedStatement
					ps.setString(1, user.getUser_name());
					ps.setString(2, user.getUser_pass());
					ps.setString(3, user.getUser_first_name());
					ps.setString(4, user.getUser_last_name());
					ps.setString(5, user.getUser_email());
					ps.setNull(6, Types.VARCHAR); // Cookie String
					ps.setNull(7, Types.TIMESTAMP_WITH_TIMEZONE); // Cookie Expiration
					ps.setBoolean(8, user.isIs_superadmin());

					int insertedRows = ps.executeUpdate();
					if (insertedRows > 0) {
						// System.out.println("Success. User was added");
						return new Response(200, "Created new user.");
					}
				} else {
					return new Response(400, "Username already exists.");
				}
			} else {
				throw new SQLException("Failed creating new user.");
			}

		} catch (SQLException e) {
			System.err.println("SQL error in registerNewUser().");
			e.printStackTrace();
		}
		return new Response(400, "Failed in creating new user.");
	}

	@Override
	public Users viewUserByUserName(String user_name) {
		// at the top of every DAO method, a connection must be opened
		try (Connection conn = ConnectionUtil.getConnection()) {
			String SQL = "SELECT * FROM messageboard.users WHERE user_name = ?";

			// Instantiate a PreparedStatement to fill in the variables (?)
			PreparedStatement ps = conn.prepareStatement(SQL, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			// Fill in values using PreparedStatement
			System.out.println(user_name);
			ps.setString(1, user_name);

			ResultSet rs = ps.executeQuery();

			Users user = null;

			if (rs.next()) {
				user = new Users(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("user_pass"),
						rs.getString("user_first_name"), rs.getString("user_last_name"), rs.getString("user_email"),
						rs.getString("user_auth_token"), rs.getTimestamp("user_auth_expiration"),
						rs.getBoolean("is_superadmin"));
				return user;
			} else {
				throw new SQLException("User not found.");
			}

			// int size = 0;
			// if (rs != null) {
			// rs.last();
			// size = rs.getRow();
			// if (size > 0) {
			// user = new Users(rs.getInt("user_id"), rs.getString("user_name"),
			// rs.getString("user_pass"),
			// rs.getString("user_first_name"), rs.getString("user_last_name"),
			// rs.getString("user_email"),
			// rs.getString("user_auth_token"), rs.getTimestamp("user_auth_expiration"),
			// rs.getBoolean("is_superadmin"));
			// }
			// }
			//
			// if (size > 0) {
			// System.out.println("User found");
			// return user;
			// } else {
			// System.out.println("User not found");
			// return null;
			// }

		} catch (SQLException e) {
			System.err.println("Error in viewUserByUserName().");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Users viewUserByUserID(int user_id) {
		// at the top of every DAO method, a connection must be opened
		try (Connection conn = ConnectionUtil.getConnection()) {
			String SQL = "SELECT * FROM messageboard.users WHERE user_id = ?";

			// Instantiate a PreparedStatement to fill in the variables (?)
			PreparedStatement ps = conn.prepareStatement(SQL, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			// Fill in values using PreparedStatement
			System.out.println(user_id);
			ps.setInt(1, user_id);

			ResultSet rs = ps.executeQuery();

			Users user = null;

			if (rs.next()) {
				user = new Users(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("user_pass"),
						rs.getString("user_first_name"), rs.getString("user_last_name"), rs.getString("user_email"),
						rs.getString("user_auth_token"), rs.getTimestamp("user_auth_expiration"),
						rs.getBoolean("is_superadmin"));
				return user;
			} else {
				throw new SQLException("User not found.");
			}

			// int size = 0;
			// if (rs != null) {
			// rs.last();
			// size = rs.getRow();
			// if (size > 0) {
			// user = new Users(rs.getInt("user_id"), rs.getString("user_name"),
			// rs.getString("user_pass"),
			// rs.getString("user_first_name"), rs.getString("user_last_name"),
			// rs.getString("user_email"),
			// rs.getString("user_auth_token"), rs.getTimestamp("user_auth_expiration"),
			// rs.getBoolean("is_superadmin"));
			// }
			// }
			//
			// if (size > 0) {
			// System.out.println("User found");
			// return user;
			// } else {
			// System.out.println("User not found");
			// return null;
			// }

		} catch (SQLException e) {
			System.err.println("Error in viewUserByUserID().");
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public Response updateUser(Users user) {
		if (user.getUser_name() != null) {
			Users fullUserInfo = this.viewUserByUserID(user.getUser_id());
			try (Connection conn = ConnectionUtil.getConnection()) {
				String SQL = "UPDATE messageboard.users SET user_name = ?, user_pass = ?, user_first_name = ?, user_last_name = ?, user_email = ? WHERE user_id = ?";

				// Instantiate a PreparedStatement to fill in the variables (?)
				PreparedStatement ps = conn.prepareStatement(SQL, ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);

				ps.setString(1, StringUtils.isNotEmpty(user.getUser_name()) ? user.getUser_name()
						: fullUserInfo.getUser_name());
				ps.setString(2,
						StringUtils.isNotEmpty(user.getUser_pass())
								? new AuthController().doEncrypt(user.getUser_pass())
								: fullUserInfo.getUser_pass());
				ps.setString(3, StringUtils.isNotEmpty(user.getUser_first_name()) ? user.getUser_first_name()
						: fullUserInfo.getUser_first_name());
				ps.setString(4, StringUtils.isNotEmpty(user.getUser_last_name()) ? user.getUser_last_name()
						: fullUserInfo.getUser_last_name());
				ps.setString(5, StringUtils.isNotEmpty(user.getUser_email()) ? user.getUser_email()
						: fullUserInfo.getUser_email());
				ps.setInt(6, fullUserInfo.getUser_id());

				int updateResult = ps.executeUpdate();
				if (updateResult > 0) {
					return new Response(200, "User data updated.");
				} else {
					throw new SQLException("Failed in updating user data.");
				}
			} catch (SQLException e) {
				System.err.println("Error in updateUser().");
				e.printStackTrace();
			}
			// System.err.println("Error. updateUser() failed.");
		}
		return new Response(400, "Please specify username.");
	}

	@Override
	public Response deleteUser(String user_name) {

		if (user_name != null) {
			try (Connection conn = ConnectionUtil.getConnection()) {
				String SQL = "DELETE FROM messageboard.users WHERE user_name = ?";

				// Instantiate a PreparedStatement to fill in the variables (?)
				PreparedStatement ps = conn.prepareStatement(SQL, ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);

				ps.setString(1, user_name);

				int deleteResult = ps.executeUpdate();
				if (deleteResult > 0) {
					return new Response(200, "Deleted user.");
				} else {
					throw new SQLException("Could not delete user.");
				}

			} catch (SQLException e) {
				System.err.println("Error in deleteUser().");
				e.printStackTrace();
			}

			return new Response(400, "Could not delete user.");
		}
		return new Response(400, "Please specify username");
	}

	@Override
	public int getUserId(String user_auth_token) {
		if (user_auth_token != null) {
			try (Connection conn = ConnectionUtil.getConnection()) {
				String SQL = "SELECT user_id FROM messageboard.users WHERE user_auth_token = ?";
				PreparedStatement ps = conn.prepareStatement(SQL);
				ps.setString(1, user_auth_token);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					return rs.getInt("user_id");
				} else {
					throw new SQLException("Could not get user ID");
				}
			} catch (SQLException e) {
				System.err.println("Error in getUserId().");
				e.printStackTrace();
			}
		}
		return 0;
	}

}
