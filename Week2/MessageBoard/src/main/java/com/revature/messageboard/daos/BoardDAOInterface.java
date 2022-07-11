package com.revature.messageboard.daos;

import java.util.ArrayList;

import com.revature.messageboard.models.Board;
import com.revature.messageboard.models.BoardMemberAccess;
import com.revature.messageboard.models.Response;

import io.javalin.http.Context;

public interface BoardDAOInterface {
	Response createBoard(Board board, Context ctx);

	ArrayList<BoardMemberAccess> viewMyBoards(Context ctx);

	Response updateBoard(Board board);

	Response deleteBoard(String user_name);
}
