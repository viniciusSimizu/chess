package com.vini.app.pieces;

import java.util.ArrayList;
import java.util.List;

import com.vini.app.fen.FenPieceEnum;
import com.vini.app.types.ColorEnum;

public abstract class Piece {
	public ColorEnum color;
	public int[] position = new int[2];
	public List<List<Boolean>> moves;
	public FenPieceEnum fen;

	Piece(ColorEnum color, int[] position, FenPieceEnum fen) {
		this.color = color;
		this.position = position;
		this.fen = fen;
	}

	public void move(List<List<Piece>> board, int[] position) {
		board.get(position[1]).set(position[0], this);
		board.get(this.position[1]).set(this.position[0], null);
	}

	public Boolean canMove(List<List<Piece>> board, int[] position) {
		return this.moves.get(position[1]).get(position[0]);
	}

	protected void structMoves(Piece piece, List<List<Piece>> board) {
		piece.moves = new ArrayList<>();
		for (int i = 0; i < board.size(); i++) {
			ArrayList<Boolean> row = new ArrayList<>();
			for (int j = 0; j < board.get(i).size(); j++) {
				row.add(false);
			}
			piece.moves.add(row);
		}
	}

	protected Boolean insideBoard(List<List<Piece>> board, int[] position) {
		if
		(
		 position[1] >= 0 
		 && position[1] < board.size() 
		 && position[0] >= 0 
		 && position[0] < board.get(position[1]).size()
		 ) {
			return true;
		}
		return false;
	}

	protected Boolean isFriend(Piece origin, Piece target) {
		return origin.color == target.color;
	}
}
