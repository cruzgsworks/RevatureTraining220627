package com.revature.messageboard.routes;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.delete;
import static io.javalin.apibuilder.ApiBuilder.put;

import com.google.gson.Gson;
import com.revature.messageboard.controller.AuthController;
import com.revature.messageboard.controller.BoardController;
import com.revature.messageboard.controller.UsersController;
import com.revature.messageboard.models.Response;
import com.revature.messageboard.models.Roles;

import io.javalin.Javalin;

public class Routing {

	private Javalin app;

	public void initRoutes() {

		// Instance of Javalin
		this.app = Javalin.create(config -> {
			config.enableCorsForAllOrigins();

			config.accessManager((handler, ctx, routeRoles) -> {
				Roles role = new AuthController().checkAuthToken(ctx);
				System.out.println("Given role = " + role.name());
				if (routeRoles.contains(role)) {
					handler.handle(ctx);
				} else {
					if (role.equals(Roles.EXPIRED)) {
						Response resp = new Response(401, "Expired Auth Token. Please Login.");
						ctx.status(resp.getStatus()).contentType("application/json").result(new Gson().toJson(resp));
					} else {
						Response resp = new Response(401, "Unauthorized");
						ctx.status(resp.getStatus()).contentType("application/json").result(new Gson().toJson(resp));
					}
				}
			});

		}).start(8080);

		this.app.routes(() -> {
			path("/api/superadmin", () -> {
				// For initial setup on demo. This should not exist
				post(new UsersController().createSuperAdmin, Roles.ANYONE);
			});

			path("/api/users", () -> {
				// Anybody can register
				post(new UsersController().createUser, Roles.ANYONE);
				// Only Super Admin, and Current User can only read user info
				get(new UsersController().viewUser, new Roles[] { Roles.SUPER_ADMIN, Roles.CURRENT_USER });
				// Only Super Admin, and Current User can update user
				put(new UsersController().updateUser, new Roles[] { Roles.SUPER_ADMIN, Roles.CURRENT_USER });
				// Only Super Admin, and Current User can delete user
				delete(new UsersController().deleteUser, new Roles[] { Roles.SUPER_ADMIN, Roles.CURRENT_USER });

			});
			path("/api/board", () -> {
				// Users can create their own boards
				post(new BoardController().createBoard, Roles.CURRENT_USER);
				// View owned boards
				get(new BoardController().viewMyBoards, new Roles[] { Roles.SUPER_ADMIN, Roles.CURRENT_USER });
			});
			path("/api/board", () -> {
				// View all owned board
			});
			path("/api/login", () -> {
				// Anybody can login and get auth token cookie if successful
				post(new AuthController().login, Roles.ANYONE);
			});
		});

	}

}
