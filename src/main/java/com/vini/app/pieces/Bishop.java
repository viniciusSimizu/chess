package com.vini.app.pieces;

import java.util.List;

public class Bishop extends Piece implements IPiece {
	final int[][] directions = {{1, -1}, {1, 1}, {-1, 1}, {-1, -1}};

	@Override
	public Piece updateMoves(List<List<Piece>> board) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'updateMoves'");
	}
	
}
