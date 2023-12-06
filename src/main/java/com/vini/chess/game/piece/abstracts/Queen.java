package com.vini.chess.game.piece.abstracts;

import com.vini.chess.game.board.Board;
import com.vini.chess.game.enums.PieceEnum;
import com.vini.chess.game.piece.Piece;

public abstract class Queen extends Piece {
	protected Queen(Board board) {
		super(board);
	}

	@Override
	public PieceEnum fen() {
		return PieceEnum.QUEEN;
	}
}


