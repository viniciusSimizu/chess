package com.vini.game.board;

import com.vini.game.board.iterators.BoardIteratorOverPiece;
import com.vini.game.interfaces.IPiece;
import com.vini.game.lib.Position;
import com.vini.socket.lib.TableRepresentation;

import java.util.List;

public class Board {

    private List<IPiece> pieces;
    private Integer width, height;
    private int round = 0;

    public IPiece findPiece(Position position) {
        if (this.isInsideTable(position)) {
            return this.pieces.get(position.getIndex(this.width));
        }
        return null;
    }

    public void setSquarePiece(Position position, IPiece value) {
        if (!this.isInsideTable(position)) {
            return;
        }
        this.pieces.set(position.getIndex(this.width), value);
    }

    public Integer getHeight() {
        return this.height;
    }

    public Integer getWidth() {
        return this.width;
    }

    public boolean isInsideTable(Position position) {
        if (position.x == null || position.y == null) {
            return false;
        }

        if (position.x < 0 || position.x >= this.width) {
            return false;
        }

        if (position.y < 0 || position.y >= this.height) {
            return false;
        }

        return true;
    }

    public void updatePieceMovements() {
        BoardIteratorOverPiece iterator = this.iteratorOverPiece();
        while (iterator.hasNext()) {
            IPiece piece = iterator.next();
            piece.resetMoves();
            piece.updateMoves();
        }
    }

    public BoardIteratorOverPiece iteratorOverPiece() {
        return new BoardIteratorOverPiece(this.pieces);
    }

    public int getRound() {
        return this.round;
    }

    public void newRound() {
        this.round++;
    }

    public void setPieces(List<IPiece> pieces, int height, int width) {
        this.pieces = pieces;
        this.height = height;
        this.width = width;

        BoardIteratorOverPiece iterator = this.iteratorOverPiece();
        while (iterator.hasNext()) {
            IPiece piece = iterator.next();
            piece.setBoard(this);
        }
    }

    public TableRepresentation export() {
        return new TableRepresentation(this);
    }
}
