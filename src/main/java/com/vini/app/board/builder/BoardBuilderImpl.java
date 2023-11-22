package com.vini.app.board.builder;

import java.util.ArrayList;
import java.util.List;

import com.vini.app.board.Board;
import com.vini.app.lib.Builder;
import com.vini.app.piece.Piece;
import com.vini.app.piece.PieceFactory;
import com.vini.app.types.ColorEnum;
import com.vini.app.types.PieceEnum;

public class BoardBuilderImpl implements BoardBuilder {
	private Board result = new Board();
	private PieceFactory factory;

	private List<List<Piece>> board = new ArrayList<>();
	private List<Piece> row = new ArrayList<>();

	public BoardBuilderImpl(PieceFactory factory) {
		this.factory = factory;
	}

	@Override
	public BoardBuilder buildRow() {
		this.board.add(this.row);
		this.result.setTable(this.board);
		this.row = new ArrayList<>();
		return this;
	}

	@Override
	public BoardBuilder buildEmptySquare() {
		this.row.add(null);
		return this;
	}

	@Override
	public BoardBuilder buildPiece(PieceEnum piece, ColorEnum color) {
		Piece buildingPiece;
		switch (piece) {
			case PAWN:
				buildingPiece = this.factory.makePawn();	
				break;
			case ROOK:
				buildingPiece = this.factory.makeRook();	
				break;
			case KNIGHT:
				buildingPiece = this.factory.makeKnight();	
				break;
			case BISHOP:
				buildingPiece = this.factory.makeBishop();	
				break;
			case QUEEN:
				buildingPiece = this.factory.makeQueen();	
				break;
			case KING:
				buildingPiece = this.factory.makeKing();	
				break;
			default:
				return this;
		}
		buildingPiece.setColor(color);
		this.row.add(buildingPiece);
		return this;
	}

	@Override
	public Board result() {
		return this.result;
	}

	@Override
	public Builder<Board> reset() {
		this.result = new Board();
		return this;
	}

	@Override
	public BoardBuilder setFactory(PieceFactory factory) {
		this.factory = factory;
		return this;
	}
}
