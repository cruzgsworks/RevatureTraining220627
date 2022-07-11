package com.revature.messageboard.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.commons.lang3.RandomStringUtils;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import com.google.gson.Gson;
import com.revature.messageboard.daos.UsersDAO;
import com.revature.messageboard.models.Response;
import com.revature.messageboard.models.Roles;
import com.revature.messageboard.models.Users;
import com.revature.messageboard.utils.ConnectionUtil;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class AuthController {
	private String internalSeed;

	public AuthController() {
		super();
		this.internalSeed = "REVATURE2022";
	}

	public String doEncrypt(String subject) {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(this.internalSeed);
		return encryptor.encrypt(subject);
	}

	public boolean comparePasswords(String input, String storedPass) {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(this.internalSeed);
		// System.out.println(encryptor.decrypt(storedPass) + " == " + input);
		if (encryptor.decrypt(storedPass).equals(input)) {
			return true;
		}
		return false;
	}

	public String createAuthToken() {
		String generatedString = RandomStringUtils.randomAlphanumeric(32);
		return generatedString;
	}

	public Response doLogin(Users user, Context ctx) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String SQL = "SELECT user_id, user_pass FROM messageboard.users WHERE user_name = ?";

			// Instantiate a PreparedStatement to fill in the variables (?)
			PreparedStatement ps = conn.prepareStatement(SQL);

			ps.setString(1, user.getUser_name());

			ResultSet checkUser = ps.executeQuery();

			if (checkUser.next()) {
				boolean checkPassword = new AuthController().comparePasswords(user.getUser_pass(),
						checkUser.getString("user_pass"));
				if (checkPassword) {
					String token = new AuthController().createAuthToken();
					SQL = "UPDATE messageboard.users SET user_auth_token = ?, user_auth_expiration = ? WHERE user_id = ?";
					ps = conn.prepareStatement(SQL);

					// Store new token and expiration timestamp to user's record
					ps.setString(1, token);
					long duration = (24 * 60 * 60 * 1000);
					ps.setTimestamp(2, new Timestamp((System.currentTimeMillis() + duration)));
					ps.setInt(3, checkUser.getInt("user_id"));
					int updateResult = ps.executeUpdate();
					if (updateResult > 0) {

						// Store token as cookie. Max-Age is 24 hrs.
						ctx.cookie("user_auth_token", token, (24 * 60 * 60));
						return new Response(200, "Login successful");
					}
				}
			}
			return new Response(400, "Wrong login credentials");

		} catch (SQLException e) {
			System.err.println("Error in doLogin().");
			e.printStackTrace();
		}
		return new Response(400, "Could not login");
	}

	public Roles checkAuthToken(Context ctx) {
		String authToken = ctx.cookie("user_auth_token");
		System.out.println("authToken = " + authToken);

		if (authToken != null) {
			try (Connection conn = ConnectionUtil.getConnection()) {

				// Find user based on given user_auth_token cookie
				String SQL = "SELECT * FROM messageboard.users WHERE user_auth_token = ?";

				PreparedStatement ps = conn.prepareStatement(SQL, ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);

				ps.setString(1, authToken);

				ResultSet rs = ps.executeQuery();
				Users user;

				String ctxPath = ctx.path();
				System.out.println("ctxPath = " + ctxPath);
				int size = 0;
				if (rs != null) {
					rs.last();
					size = rs.getRow();
					if (size > 0) {
						user = new Users(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("user_pass"),
								rs.getString("user_first_name"), rs.getString("user_last_name"),
								rs.getString("user_email"), rs.getString("user_auth_token"),
								rs.getTimestamp("user_auth_expiration"), rs.getBoolean("is_superadmin"));

						// check access depending on context path
						switch (ctxPath) {
						case "/api/board":

							// Check auth token expiration
							if (user.getUser_auth_expiration() != null) {
								System.out.println("Check expiration " + user.getUser_auth_expiration().toString()
										+ " VS " + new Timestamp(System.currentTimeMillis()));
								if (user.getUser_auth_expiration().before(new Timestamp(System.currentTimeMillis()))) {
									return Roles.EXPIRED;
								}
							} else {
								return Roles.EXPIRED;
							}

							// switch cases on request method
							switch (ctx.method().toLowerCase()) {
							case "post":
							case "get":
								return Roles.CURRENT_USER;
							default:
								break;
							}

							break;
						case "/api/users":

							// Check auth token expiration
							if (user.getUser_auth_expiration() != null) {
								System.out.println("Check expiration " + user.getUser_auth_expiration().toString()
										+ " VS " + new Timestamp(System.currentTimeMillis()));
								if (user.getUser_auth_expiration().before(new Timestamp(System.currentTimeMillis()))) {
									return Roles.EXPIRED;
								}
							} else {
								return Roles.EXPIRED;
							}

							// switch cases on request method
							switch (ctx.method().toLowerCase()) {
							case "get":
							case "put":
							case "delete":

								// Only current User should have access to their own info in this path.
								Users viewUser = new UsersDAO().viewUserByUserName(ctx.queryParam("user_name"));
								if (viewUser != null) {
									if (viewUser.getUser_id() == user.getUser_id()) {
										System.out.println("Current User");
										return Roles.CURRENT_USER;
									}
								}
								break;
							case "post":
								// anybody can register
								return Roles.ANYONE;

							default:
								break;
							}

							// immediately return superadmin
							if (user.isIs_superadmin()) {
								return Roles.SUPER_ADMIN;
							}

						default:
							break;
						}
					}
				}
			} catch (SQLException e) {
				System.err.println("Error. checkAuthToken() failed.");
				e.printStackTrace();
			}
		}

		// TODO Auto-generated method stub
		return Roles.ANYONE;
	}

	public Handler login = (ctx) -> {
		Gson gson = new Gson();

		AuthController auth = new AuthController();
		Users requestBody = gson.fromJson(ctx.body(), Users.class);
		System.out.println(requestBody.getUser_name());

		Response doLogin = auth.doLogin(requestBody, ctx);
		String output = gson.toJson(doLogin);

		ctx.contentType("application/json");
		ctx.status(doLogin.getStatus());
		ctx.result(output);
	};

}
