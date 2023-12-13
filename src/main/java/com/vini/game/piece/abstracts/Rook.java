package com.vini.game.piece.abstracts;

import com.vini.game.board.Board;
import com.vini.game.enums.PieceEnum;
import com.vini.game.piece.Piece;

public abstract class Rook extends Piece {
	protected Rook(Board board) {
		super(board);
	}

	protected boolean isFirstMove = true;

	public boolean isFirstMove() {
		return this.isFirstMove;
	}

	@Override
	public PieceEnum fen() {
		return PieceEnum.ROOK;
	}
}

