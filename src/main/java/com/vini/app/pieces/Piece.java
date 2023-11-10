package com.vini.app.pieces;

import java.util.ArrayList;
import java.util.List;

import com.vini.app.types.ColorEnum;

public abstract class Piece implements IPiece {
	private ColorEnum color;
	private String fen;
	private int[] position = new int[2];
	private List<List<Boolean>> moves = new ArrayList<>();

	public void move(List<List<IPiece>> board, int[] position) {
		board.get(position[1]).set(position[0], this);
		board.get(this.position[1]).set(this.position[0], null);
	}

	public Boolean canMove(List<List<IPiece>> board, int[] position) {
		return this.moves.get(position[1]).get(position[0]);
	}

	protected void structMoves(Piece piece, List<List<IPiece>> board) {
		piece.moves = new ArrayList<>();
		for (int i = 0; i < board.size(); i++) {
			ArrayList<Boolean> row = new ArrayList<>();
			for (int j = 0; j < board.get(i).size(); j++) {
				row.add(false);
			}
			piece.moves.add(row);
		}
	}

	protected Boolean insideBoard(List<List<IPiece>> board, int[] position) {
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

	protected Boolean isFriend(IPiece origin, IPiece target) {
		return origin.color() == target.color();
	}

	public ColorEnum color() {
		return this.color;
	}
	public IPiece setColor(ColorEnum color) {
		this.color = color;
		return this;
	}

	public String fen() {
		return this.fen;
	}
	public IPiece setFen(String fen) {
		this.fen = fen;
		return this;
	}

	public int[] position() {
		return this.position;
	}
	public IPiece setPosition(int[] position) {
		this.position = position;
		return this;
	}

	public List<List<Boolean>> moves() {
		return this.moves;
	}
	public IPiece setMoves(List<List<Boolean>> moves) {
		this.moves = moves;
		return this;
	}
}
