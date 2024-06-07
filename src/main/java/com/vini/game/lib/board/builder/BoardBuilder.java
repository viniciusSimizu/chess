package com.vini.game.board;

import com.vini.game.enums.ColorEnum;
import com.vini.game.enums.PieceEnum;
import com.vini.game.interfaces.IBoard;
import com.vini.game.interfaces.IPiece;
import com.vini.game.piece.PieceFactory;
import com.vini.game.structs.Position;

import java.util.ArrayList;
import java.util.List;

public class BoardBuilder {

    private Integer width;
    private int columnIdx, rowIdx;
    private IBoard board;
    private List<IPiece> pieces;
    private PieceFactory factory = PieceFactory.getInstance();

    public BoardBuilder() {
        this.reset();
    }

    public BoardBuilder reset() {
        this.board = new Board();
        this.pieces = new ArrayList<>();
        this.columnIdx = this.rowIdx = 0;
        this.width = null;
        return this;
    }

    public BoardBuilder buildRow() throws IllegalArgumentException {
        if (this.width == null) {
            this.width = columnIdx;
        }

        if (this.columnIdx < this.width) {
            throw new IllegalArgumentException("Row with less squares than others");
        }

        this.columnIdx = 0;
        this.rowIdx++;
        return this;
    }

    public BoardBuilder buildEmptySquare() throws IllegalArgumentException {
        if (!this.canInsert()) {
            throw new IllegalArgumentException("Row with more squares than others");
        }
        this.pieces.add(null);
        this.columnIdx++;
        return this;
    }

    public BoardBuilder buildPiece(PieceEnum piece, ColorEnum color)
            throws IllegalArgumentException {
        if (!this.canInsert()) {
            throw new IllegalArgumentException("Row with more squares than others");
        }

        IPiece buildingPiece;
        switch (piece) {
            case PAWN:
                buildingPiece = this.factory.makePawn(color);
                break;
            case ROOK:
                buildingPiece = this.factory.makeRook(color);
                break;
            case KNIGHT:
                buildingPiece = this.factory.makeKnight(color);
                break;
            case BISHOP:
                buildingPiece = this.factory.makeBishop(color);
                break;
            case QUEEN:
                buildingPiece = this.factory.makeQueen(color);
                break;
            case KING:
                buildingPiece = this.factory.makeKing(color);
                break;
            default:
                return this;
        }

        var position = new Position(this.columnIdx, this.rowIdx);
        buildingPiece.setPosition(position);
        this.pieces.add(buildingPiece);
        this.columnIdx++;
        return this;
    }

    public IBoard getResult() throws IllegalArgumentException {
        if (this.columnIdx != 0 && this.canInsert()) {
            throw new IllegalArgumentException("Unbalanced board");
        }
        this.board.setPieces(this.pieces, this.rowIdx, this.width);

        IBoard board = this.board;
        this.reset();
        return board;
    }

    private boolean canInsert() {
        if (this.width == null) {
            return true;
        }
        if (this.columnIdx < this.width) {
            return true;
        }
        return false;
    }
}
