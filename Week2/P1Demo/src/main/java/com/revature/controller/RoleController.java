package com.revature.controller;

import com.google.gson.Gson;
import com.revature.daos.RoleDAO;
import com.revature.models.Response;
import com.revature.models.Role;

import io.javalin.http.Handler;

public class RoleController {
	public Handler updateRoleSalary = (ctx) -> {

		String body = ctx.body();

		Gson gson = new Gson();

		Role role = gson.fromJson(body, Role.class);

		RoleDAO roleDAO = new RoleDAO();

		boolean updateRoleSalary = roleDAO.updateRoleSalary(ctx.pathParam("title"), role.getRole_salary());

		Response response;

		if (updateRoleSalary) {
			response = new Response(202, "Succesfully update role salary");
			ctx.contentType("application/json").status(response.getStatus()).result(gson.toJson(response));
		} else {
			response = new Response(406, "Failed to update role salary");
			ctx.contentType("application/json").status(response.getStatus()).result(gson.toJson(response));
		}

	};
}
