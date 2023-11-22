package com.vini.app.piece;

import java.util.List;

import com.vini.app.board.Board;
import com.vini.app.types.ColorEnum;

public interface Piece {
	public Piece updateMoves(Board board);

	public ColorEnum color();
	public Piece setColor(ColorEnum color);
	
	public int[] position();
	public Piece setPosition(int[] position);

	public List<List<Boolean>> moves();
	public Piece setMoves(List<List<Boolean>> moves);

	public boolean hasMovesUpdated();
	public Piece setHasMovesUpdated(boolean state);
}

