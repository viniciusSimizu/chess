package com.vini.game.board;

import com.vini.game.enums.ColorEnum;
import com.vini.game.enums.PieceEnum;
import com.vini.game.piece.IPiece;
import com.vini.game.piece.PieceFactory;

import java.util.ArrayList;
import java.util.List;

public class BoardBuilder {

    private int rowIdx = 0, colIdx = 0;

    private Board board = new Board();
    private PieceFactory factory = PieceFactory.getInstance();
    private List<List<IPiece>> table = new ArrayList<>();
    private List<IPiece> row = new ArrayList<>();

    public BoardBuilder buildRow() {
        this.table.add(this.row);
        this.row = new ArrayList<>();

        this.rowIdx++;
        this.colIdx = 0;
        return this;
    }

    public BoardBuilder buildEmptySquare() {
        this.row.add(null);
        this.colIdx++;
        return this;
    }

    public BoardBuilder buildPiece(PieceEnum piece, ColorEnum color) {
        IPiece buildingPiece;
        switch (piece) {
            case PAWN:
                buildingPiece = this.factory.makePawn(this.board, color);
                break;
            case ROOK:
                buildingPiece = this.factory.makeRook(this.board, color);
                break;
            case KNIGHT:
                buildingPiece = this.factory.makeKnight(this.board, color);
                break;
            case BISHOP:
                buildingPiece = this.factory.makeBishop(this.board, color);
                break;
            case QUEEN:
                buildingPiece = this.factory.makeQueen(this.board, color);
                break;
            case KING:
                buildingPiece = this.factory.makeKing(this.board, color);
                break;
            default:
                return this;
        }

        buildingPiece.position().x = this.colIdx;
        buildingPiece.position().y = this.rowIdx;

        this.row.add(buildingPiece);
        this.colIdx++;
        return this;
    }

    public Board getResult() {
        this.board.setTable(this.table);
        return this.board;
    }
}
