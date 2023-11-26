package com.vini.app.piece.abstracts;

import com.vini.app.board.Board;
import com.vini.app.piece.Piece;
import com.vini.app.types.PieceEnum;

public abstract class King extends Piece {
	protected King(Board board) {
		super(board);
	}

	@Override
	public PieceEnum fen() {
		return PieceEnum.KING;
	}
}

