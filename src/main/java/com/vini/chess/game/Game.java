package com.vini.chess.game;

import com.vini.chess.game.board.Board;
import com.vini.chess.game.board.iterator.BoardIteratorOverPiece;
import com.vini.chess.game.enums.ColorEnum;
import com.vini.chess.game.lib.Command;
import com.vini.chess.game.piece.IPiece;
import com.vini.chess.game.piece.commands.PieceMoveCommand;

public class Game {
	private ColorEnum turn;
	private int round = 0;

	private final Board board;
	private final GameResources resources = new GameResources();

	public Game(Board board, ColorEnum turn) {
		this.board = board;
		this.turn = turn;
	}

	public boolean move(int[] sourcePosition, int[] targetPosition) {
		IPiece piece = this.board.findPiece(sourcePosition);

		if (piece == null) {
			return false;
		}

		if (!piece.canMove(targetPosition)) {
			return false;
		}

		PieceMoveCommand moveCommand = piece.move(targetPosition);
		this.resources.addToMoveHistory(moveCommand);

		this.resources.runRoundExecutions(this.round);

		this.round++;

		return true;
	}

	public void toggleTurn() {
		if (this.turn == ColorEnum.BLACK) {
			this.turn = ColorEnum.WHITE;
		} else {
			this.turn = ColorEnum.BLACK;
		}
	}

	public void refreshPieceMoves() {
		BoardIteratorOverPiece iterator = new BoardIteratorOverPiece(this.board);

		while (iterator.hasNext()) {
			IPiece piece = iterator.next();
			piece.resetMoves();
			piece.updateMoves();
		}
	}

	public void scheduleToExecute(Command command, int skipTurns) {
		this.resources.addToExecutionQueue(command, this.round, skipTurns);
	}

	public void attachGameOverPieces() {
		BoardIteratorOverPiece iterator = new BoardIteratorOverPiece(this.board);

		while (iterator.hasNext()) {
			IPiece piece = iterator.next();
			piece.setGame(this);
		}
	}
}