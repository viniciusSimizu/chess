package com.vini.chess.app.piece.abstracts;

import com.vini.chess.app.board.Board;
import com.vini.chess.app.piece.Piece;
import com.vini.chess.app.types.PieceEnum;

public abstract class Queen extends Piece {
	protected Queen(Board board) {
		super(board);
	}

	@Override
	public PieceEnum fen() {
		return PieceEnum.QUEEN;
	}
}


