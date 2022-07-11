package com.revature.messageboard.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.messageboard.models.Board;
import com.revature.messageboard.models.BoardMemberAccess;
import com.revature.messageboard.models.Response;
import com.revature.messageboard.utils.ConnectionUtil;

import io.javalin.http.Context;

public class BoardDAO implements BoardDAOInterface {

	@Override
	public Response createBoard(Board board, Context ctx) {

		// at the top of every DAO method, a connection must be opened
		try (Connection conn = ConnectionUtil.getConnection()) {
			String SQL = "SELECT COUNT(*) ct FROM messageboard.board WHERE board_name = ?";

			// Instantiate a PreparedStatement to fill in the variables (?)
			PreparedStatement ps = conn.prepareStatement(SQL);

			ps.setString(1, board.getBoard_name());

			ResultSet checkBoard = ps.executeQuery();

			if (checkBoard.next()) {
				if (checkBoard.getInt("ct") < 1) {
					SQL = "WITH Y AS (\r\n" + "  INSERT INTO messageboard.board(board_name)\r\n" + "  VALUES (?)\r\n"
							+ "  RETURNING board_id\r\n" + "), X AS (\r\n"
							+ "  INSERT INTO messageboard.members(board_id, user_id)\r\n" + "  SELECT board_id, ?\r\n"
							+ "  FROM Y\r\n" + "  RETURNING member_id\r\n" + ")\r\n"
							+ "INSERT INTO messageboard.member_access(is_admin, is_moderator, is_member, member_id)\r\n"
							+ "SELECT TRUE, FALSE, FALSE, member_id\r\n" + "FROM X;";

					ps = conn.prepareStatement(SQL);

					ps.setString(1, board.getBoard_name());
					ps.setInt(2, new UsersDAO().getUserId(ctx.cookie("user_auth_token")));

					int insertedRows = ps.executeUpdate();
					if (insertedRows > 0) {
						return new Response(200, "Created a new board and assigned you as the owner/admin.");
					}

				}

			} else {
				throw new SQLException("board_name already exists.");
			}

		} catch (SQLException e) {
			System.err.println("Error. registerNewUser() failed.");
			e.printStackTrace();
		}
		return new Response(400, "Failed. Board was not added");
	}

	@Override
	public ArrayList<BoardMemberAccess> viewMyBoards(Context ctx) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String SQL = "SELECT b.board_id, m.user_id, b.board_name, ma.is_admin, ma.is_moderator, ma.is_member\r\n"
					+ "FROM messageboard.board b\r\n" + "INNER JOIN messageboard.members m ON\r\n"
					+ "b.board_id = m.board_id\r\n" + "INNER JOIN messageboard.member_access ma ON\r\n"
					+ "m.member_id = ma.member_id\r\n" + "WHERE m.user_id = ?";

			// Instantiate a PreparedStatement to fill in the variables (?)
			PreparedStatement ps = conn.prepareStatement(SQL);

			ps.setInt(1, new UsersDAO().getUserId(ctx.cookie("user_auth_token")));

			ResultSet rs = ps.executeQuery();

			ArrayList<BoardMemberAccess> myBoards = new ArrayList<BoardMemberAccess>();
			while (rs.next()) {
				BoardMemberAccess board = new BoardMemberAccess(rs.getString("board_name"), rs.getInt("board_id"),
						rs.getInt("user_id"), rs.getBoolean("is_admin"), rs.getBoolean("is_moderator"),
						rs.getBoolean("is_member"));
				System.out.println(board.toString());
				myBoards.add(board);
			}
			return myBoards;
		} catch (SQLException e) {
			System.err.println("viewMyBoards() failed");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Response updateBoard(Board board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response deleteBoard(String user_name) {
		// TODO Auto-generated method stub
		return null;
	}

}
