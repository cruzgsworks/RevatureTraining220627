package com.revature.messageboard.controller;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.revature.messageboard.daos.BoardDAO;
import com.revature.messageboard.models.Board;
import com.revature.messageboard.models.BoardMemberAccess;
import com.revature.messageboard.models.Response;

import io.javalin.http.Handler;

public class BoardController {

	public Handler createBoard = (ctx) -> {
		Gson gson = new Gson();

		BoardDAO boardDAO = new BoardDAO();
		// Convert JSON data to Board Object
		Board requestBody = gson.fromJson(ctx.body(), Board.class);

		System.out.println(requestBody.toString());

		Response createBoard = boardDAO.createBoard(requestBody, ctx);
		String output = gson.toJson(createBoard);

		ctx.contentType("application/json");
		ctx.status(createBoard.getStatus());
		ctx.result(output);
	};

	public Handler viewMyBoards = (ctx) -> {
		Gson gson = new Gson();

		BoardDAO boardDAO = new BoardDAO();
		ArrayList<BoardMemberAccess> myBoards = boardDAO.viewMyBoards(ctx);

		String output = gson.toJson(myBoards);

		if (myBoards.size() > 0) {
			ctx.contentType("application/json").status(200).result(output);
		} else {
			ctx.contentType("application/json").status(400)
					.result(gson.toJson(new Response(400, "You have no boards")));
		}

	};

}
