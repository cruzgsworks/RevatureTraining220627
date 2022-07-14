package com.revature.controller;

import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.revature.models.LoginDTO;
import com.revature.services.AuthService;

import io.javalin.http.Handler;

public class AuthController {
	AuthService as = new AuthService();

	public static HttpSession ses;

	public Handler loginHandler = (ctx) -> {

		String body = ctx.body();

		Gson gson = new Gson();

		LoginDTO loginDTO = gson.fromJson(body, LoginDTO.class);

		String loginUsername = as.login(loginDTO.getUsername(), loginDTO.getPassword());

		if (loginUsername != null) {
			ses = ctx.req.getSession(); // this is how we create new sessions

			ctx.result("welcome " + loginUsername);
			ctx.status(202); // 202 stands for "accepted"
		} else {
			ctx.status(401); //401 stands for "unauthorized"
		}

	};
}
