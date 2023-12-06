package com.vini.chess.game.piece.abstracts;

import com.vini.chess.game.board.Board;
import com.vini.chess.game.enums.PieceEnum;
import com.vini.chess.game.piece.Piece;

public abstract class Knight extends Piece {
	protected Knight(Board board) {
		super(board);
	}

	@Override
	public PieceEnum fen() {
		return PieceEnum.KNIGHT;
	}
}


