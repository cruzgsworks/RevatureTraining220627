package com.revature.messageboard.models;

import java.sql.Timestamp;

public class Messages {
	private int message_id;
	private Timestamp message_timestamp;
	private int board_id;
	private int user_id;

	public int getMessage_id() {
		return message_id;
	}

	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}

	public Timestamp getMessage_timestamp() {
		return message_timestamp;
	}

	public void setMessage_timestamp(Timestamp message_timestamp) {
		this.message_timestamp = message_timestamp;
	}

	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

}
