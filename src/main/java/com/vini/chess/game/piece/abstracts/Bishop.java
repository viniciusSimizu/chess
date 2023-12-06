package com.vini.chess.game.piece.abstracts;

import com.vini.chess.game.board.Board;
import com.vini.chess.game.enums.PieceEnum;
import com.vini.chess.game.piece.Piece;

public abstract class Bishop extends Piece {
	protected Bishop(Board board) {
		super(board);
	}

	@Override
	public PieceEnum fen() {
		return PieceEnum.BISHOP;
	}
}
