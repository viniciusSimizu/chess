package com.vini.game.piece.abstracts;

import com.vini.game.board.Board;
import com.vini.game.enums.PieceEnum;
import com.vini.game.piece.Piece;

public abstract class Bishop extends Piece {
	protected Bishop(Board board) {
		super(board);
	}

	@Override
	public PieceEnum fen() {
		return PieceEnum.BISHOP;
	}
}
