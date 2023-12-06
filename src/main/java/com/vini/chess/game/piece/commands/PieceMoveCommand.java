package com.vini.chess.game.piece.commands;

import com.vini.chess.game.board.Board;
import com.vini.chess.game.lib.Command;
import com.vini.chess.game.piece.IPiece;

public class PieceMoveCommand implements Command {
	private int[] sourcePosition;
	private int[] targetPosition;
	private Board board;

	public PieceMoveCommand(int[] sourcePosition, int[] targetPosition, Board board) {
		this.sourcePosition = sourcePosition;
		this.targetPosition = targetPosition;
		this.board = board;
	}

	@Override
	public void execute() {
		IPiece piece = this.board.findPiece(sourcePosition);

		this.board.table().get(targetPosition[1]).set(targetPosition[0], piece);
		this.board.table().get(sourcePosition[1]).set(sourcePosition[0], null);

		piece.setPosition(targetPosition);
	}
}