package com.vini.chess.app.piece.abstracts;

import com.vini.chess.app.board.Board;
import com.vini.chess.app.piece.IPiece;
import com.vini.chess.app.piece.Piece;
import com.vini.chess.app.piece.commands.PieceMoveCommand;
import com.vini.chess.app.types.ColorEnum;
import com.vini.chess.app.types.PieceEnum;

public abstract class Pawn extends Piece {
	protected Pawn(Board board) {
		super(board);
	}

	protected boolean isFirstMove = true;
	protected int directionWeight = 1;

	@Override
	public PieceMoveCommand move(int[] position) {
		PieceMoveCommand command = super.move(position);
		
		if (command != null) {
			this.isFirstMove = false;
		}

		return command;
	}

	@Override
	public IPiece setColor(ColorEnum color) {
		super.setColor(color);

		if (color == ColorEnum.WHITE) {
			this.directionWeight = -1;
		}

		return this;
	}

	@Override
	public PieceEnum fen() {
		return PieceEnum.PAWN;
	}
}

