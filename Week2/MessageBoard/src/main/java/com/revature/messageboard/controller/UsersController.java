package com.revature.messageboard.controller;

import com.google.gson.Gson;
import com.revature.messageboard.daos.UsersDAO;
import com.revature.messageboard.models.Response;
import com.revature.messageboard.models.Users;

import io.javalin.http.Handler;

public class UsersController {

	public Handler createSuperAdmin = (ctx) -> {
		Gson gson = new Gson();

		UsersDAO userDAO = new UsersDAO();
		Users user = gson.fromJson(ctx.body(), Users.class);

		// System.out.println(requestBody.toString());

		user.setUser_pass(new AuthController().doEncrypt(user.getUser_pass()));
		user.setIs_superadmin(true);

		Response createUser = userDAO.createSuperAdmin(user);
		String output = gson.toJson(createUser);

		ctx.contentType("application/json").status(createUser.getStatus()).result(output);

	};

	public Handler createUser = (ctx) -> {
		Gson gson = new Gson();

		UsersDAO userDAO = new UsersDAO();
		Users user = gson.fromJson(ctx.body(), Users.class);

		// System.out.println(requestBody.toString());

		user.setUser_pass(new AuthController().doEncrypt(user.getUser_pass()));
		user.setIs_superadmin(true);

		Response createUser = userDAO.registerNewUser(user);
		String output = gson.toJson(createUser);

		ctx.contentType("application/json").status(createUser.getStatus()).result(output);

	};

	public Handler viewUser = (ctx) -> {
		Gson gson = new Gson();

		UsersDAO userDAO = new UsersDAO();
		Users user = userDAO.viewUserByUserName(ctx.queryParam("user_name"));

		if (user != null) {
			String output = gson.toJson(user);
			ctx.contentType("application/json");
			ctx.status(200);
			ctx.result(output);
		} else {
			Response response = new Response(404, "User not found");
			String output = gson.toJson(response);
			ctx.contentType("application/json");
			ctx.status(response.getStatus());
			ctx.result(output);
		}
	};

	public Handler updateUser = (ctx) -> {
		Gson gson = new Gson();

		UsersDAO userDAO = new UsersDAO();
		Users requestBody = gson.fromJson(ctx.body(), Users.class);

		System.out.println(requestBody.toString());

		Response updateUser = userDAO.updateUser(requestBody);
		String output = gson.toJson(updateUser);

		ctx.contentType("application/json");
		ctx.status(updateUser.getStatus());
		ctx.result(output);
	};

	public Handler deleteUser = (ctx) -> {
		Gson gson = new Gson();

		UsersDAO userDAO = new UsersDAO();

		Response deleteUser = userDAO.deleteUser(ctx.queryParam("user_name"));
		String output = gson.toJson(deleteUser);

		ctx.contentType("application/json");
		ctx.status(deleteUser.getStatus());
		ctx.result(output);
	};

}
