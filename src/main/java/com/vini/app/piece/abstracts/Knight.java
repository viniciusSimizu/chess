package com.vini.app.piece.abstracts;

import com.vini.app.board.Board;
import com.vini.app.piece.Piece;
import com.vini.app.types.PieceEnum;

public abstract class Knight extends Piece {
	protected Knight(Board board) {
		super(board);
	}

	@Override
	public PieceEnum fen() {
		return PieceEnum.KNIGHT;
	}
}


