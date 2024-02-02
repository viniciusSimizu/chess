package com.vini.game.board;

import com.vini.game.board.iterators.BoardIteratorOverPiece;
import com.vini.game.interfaces.IPiece;
import com.vini.game.lib.Position;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<List<IPiece>> table;
    private Integer width, height;
    private int round = 0;

    public IPiece findPiece(Position position) {
        if (this.isInsideTable(position)) {
            return this.table.get(position.y).get(position.x);
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
        if (position.y < 0 || position.y >= this.table.size()) {
            return false;
        }

        if (position.x >= 0 && position.x < this.table.get(position.y).size()) {
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
        return new BoardIteratorOverPiece(this.table);
    }

    public int getRound() {
        return this.round;
    }

    public void newRound() {
        this.round++;
    }

    public void setTable(List<List<IPiece>> table) {
        this.table = table;
        this.height = table.size();
        this.width = table.get(0).size();

        BoardIteratorOverPiece iterator = this.iteratorOverPiece();
        while (iterator.hasNext()) {
            IPiece piece = iterator.next();
            piece.setBoard(this);
        }
    }

    public List<String> getRepresentation() {
        List<String> squareIdentifiers = new ArrayList<>(this.getHeight() * this.getWidth());
        for (List<IPiece> row : this.table) {
            for (IPiece square : row) {
                if (square == null) {
                    squareIdentifiers.add(null);
                } else {
                    squareIdentifiers.add(square.getIdentifier());
                }
            }
        }
        return squareIdentifiers;
    }
}
