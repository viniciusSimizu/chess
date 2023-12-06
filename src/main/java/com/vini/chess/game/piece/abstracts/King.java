package com.vini.chess.game.piece.abstracts;

import com.vini.chess.game.board.Board;
import com.vini.chess.game.enums.PieceEnum;
import com.vini.chess.game.piece.Piece;

public abstract class King extends Piece {
	protected King(Board board) {
		super(board);
	}

	protected boolean isFirstMove = true;

	public boolean isFirstMove() {
		return this.isFirstMove;
	}

	@Override
	public PieceEnum fen() {
		return PieceEnum.KING;
	}
}

