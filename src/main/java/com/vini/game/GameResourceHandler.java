package com.vini.game;

import java.util.ArrayList;
import java.util.List;

import com.vini.game.lib.Command;
import com.vini.game.lib.CommandQueue;
import com.vini.game.piece.commands.PieceMoveCommand;

public class GameResourceHandler {
	private final List<PieceMoveCommand> moveHistory = new ArrayList<>();
	private final CommandQueue executionQueue = new CommandQueue();

	public GameResourceHandler() {}

	public void addToMoveHistory(PieceMoveCommand moveCommand) {
		this.moveHistory.add(moveCommand);
	}

	public void runRoundExecutions(int round) {
		for (Command command : this.executionQueue.get(round)) {
			command.execute();
		}
	}

	public void addToExecutionQueue(Command command, int round, int skipTurns) {
		this.executionQueue.put(command, round, skipTurns);
	}
}
