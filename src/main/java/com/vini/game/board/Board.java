package com.vini.game.board;

import com.vini.game.board.iterators.BoardIteratorOverPiece;
import com.vini.game.interfaces.IPiece;
import com.vini.game.lib.Position;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<IPiece> pieces;
    private Integer width, height;
    private int round = 0;

    public IPiece findPiece(Position position) {
        if (this.isInsideTable(position)) {
            return this.pieces.get(this.width * position.y + position.x);
        }
        return null;
    }

    public boolean tryMovePiece(Position from, Position to) {
        if (!this.isInsideTable(to)) {
            return false;
        }

        IPiece piece = this.findPiece(from);
        if (piece == null) {
            return false;
        }

        return piece.tryMove(to);
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

        if (position.x < this.width && position.y < this.height) {
            return true;
        }

        return false;
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

    public List<List<String>> getRepresentation() {
        var representation = new ArrayList<List<String>>(this.height);
        for (int i = 0; i < this.height; i++) {

            var row = new ArrayList<String>(this.width);
            representation.add(row);
            for (int j = 0; j < this.width; j++) {

                var piece = this.pieces.get(this.width * i + j);
                if (piece == null) {
                    row.add(null);
                } else {
                    row.add(piece.getIdentifier());
                }
            }
        }
        return representation;
    }
}
