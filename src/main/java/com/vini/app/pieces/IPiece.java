package com.vini.app.pieces;

import java.util.List;

import com.vini.app.types.ColorEnum;

public interface IPiece {
	public IPiece updateMoves(List<List<IPiece>> board);
	public void structMoves(List<List<IPiece>> board);
	public void move(List<List<IPiece>> board, int[] position);
	public boolean canMove(List<List<IPiece>> board, int[] position);

	public ColorEnum color();
	public IPiece setColor(ColorEnum color);
	
	public int[] position();
	public IPiece setPosition(int[] position);

	public List<List<Boolean>> moves();

	public String fen();
	public IPiece setFen(String fen);

	public boolean movesUpdated();
	public IPiece setMovesUpdated(boolean state);
}

