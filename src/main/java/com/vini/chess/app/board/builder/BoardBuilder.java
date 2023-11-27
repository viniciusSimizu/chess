package com.vini.chess.app.board.builder;

import java.util.ArrayList;
import java.util.List;

import com.vini.chess.app.board.Board;
import com.vini.chess.app.piece.IPiece;
import com.vini.chess.app.piece.IPieceFactory;
import com.vini.chess.app.types.ColorEnum;
import com.vini.chess.app.types.PieceEnum;

public class BoardBuilder implements IBoardBuilder {
	private Board result;

	private List<List<IPiece>> table = new ArrayList<>();
	private List<IPiece> row = new ArrayList<>();

	private IPieceFactory factory;

	private int rowIdx = 0;
	private int colIdx = 0;

	public BoardBuilder(IPieceFactory factory) {
		this.result = new Board(this.table);
		this.factory = factory;
	}

	@Override
	public IBoardBuilder buildRow() {
		this.table.add(this.row);
		this.row = new ArrayList<>();

		this.rowIdx++;
		this.colIdx = 0;
		return this;
	}

	@Override
	public IBoardBuilder buildEmptySquare() {
		this.row.add(null);
		this.colIdx++;
		return this;
	}

	@Override
	public IBoardBuilder buildPiece(PieceEnum piece, ColorEnum color) {
		IPiece buildingPiece;
		switch (piece) {
			case PAWN:
				buildingPiece = this.factory.makePawn(this.result());	
				break;
			case ROOK:
				buildingPiece = this.factory.makeRook(this.result());
				break;
			case KNIGHT:
				buildingPiece = this.factory.makeKnight(this.result());
				break;
			case BISHOP:
				buildingPiece = this.factory.makeBishop(this.result());
				break;
			case QUEEN:
				buildingPiece = this.factory.makeQueen(this.result());
				break;
			case KING:
				buildingPiece = this.factory.makeKing(this.result());
				break;
			default:
				return this;
		}

		buildingPiece.setPosition(new int[]{this.colIdx, this.rowIdx});
		buildingPiece.setColor(color);

		this.row.add(buildingPiece);
		this.colIdx++;
		return this;
	}

	@Override
	public Board result() {
		return this.result;
	}

	@Override
	public IBoardBuilder setFactory(IPieceFactory factory) {
		this.factory = factory;
		return this;
	}
}
