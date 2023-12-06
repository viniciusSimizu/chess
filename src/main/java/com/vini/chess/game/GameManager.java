package com.vini.chess.game;

import java.net.Socket;
import java.util.Arrays;

import com.vini.chess.server.ActionTypeEnum;

public class GameManager {
	private Game game;
	private Socket socket;

	public GameManager(Game game, Socket socket) {
		this.game = game;
		this.socket = socket;
	}

	public boolean action(String message) {
		String[] commands = message.split(";");
		String action = commands[0];
		String[] params = Arrays.copyOfRange(commands, 1, commands.length);

		switch (ActionTypeEnum.findAction(action)) {
			case MOVE:
				return this.actionMove(params);

			default:
				return false;
		}
	}
	
	public boolean actionMove(String[] params) {
		int[] sourcePosition = new int[2];
		int[] targetPosition = new int[2];

		try {
			sourcePosition[0] = Integer.parseInt(params[0]);
			sourcePosition[1] = Integer.parseInt(params[1]);
			targetPosition[2] = Integer.parseInt(params[2]);
			targetPosition[3] = Integer.parseInt(params[3]);
		} catch (NumberFormatException err) {
			err.printStackTrace();
			return false;
		}

		return this.game.move(sourcePosition, targetPosition);
	}
}
