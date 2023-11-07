package com.vini.app.pieces;

import java.util.List;

public class Knight extends Piece implements IPiece {
	final int[][] directions = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
	final int forwardStepWight = 2;
	final int sideStepWeight = 1;

	@Override
	public Piece updateMoves(List<List<Piece>> board) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'updateMoves'");
	}
}

