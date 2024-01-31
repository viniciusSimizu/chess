package com.vini.game.board;

import com.vini.game.enums.BoardStateEnum;
import com.vini.game.lib.Position;
import com.vini.game.piece.IPiece;

import java.util.Iterator;
import java.util.List;

public class Board {
    private final int width, height;
    private final List<List<IPiece>> table;
    private BoardStateEnum state;

    public Board(List<List<IPiece>> table) {
        this.table = table;
        this.state = BoardStateEnum.ON_GOING;
        this.height = table.size();
        this.width = table.get(0).size();
    }

    public IPiece findPiece(Position position) {
        if (this.isInsideTable(position)) {
            return this.table.get(position.y).get(position.x);
        }
        return null;
    }

    public boolean trySetPiece(Position position, IPiece piece) {
        if (!this.isInsideTable(position)) {
            return false;
        }

        this.table.get(position.y).set(position.x, piece);

        return true;
    }

    public BoardStateEnum getState() {
        return this.state;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
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

    public void updateState() {
        this.updatePieceMovements();
    }

    private void updatePieceMovements() {
        BoardIteratorOverPiece iterator = this.iterator();
        while (iterator.hasNext()) {
            IPiece piece = iterator.next();
            piece.updateMoves();
        }
    }

    public BoardIteratorOverPiece iterator() {
        return new BoardIteratorOverPiece(this.table);
    }

    class BoardIteratorOverPiece implements Iterator<IPiece> {
        private List<List<IPiece>> table;
        private int rowIdx = 0, colIdx = 0;

        public BoardIteratorOverPiece(List<List<IPiece>> table) {
            this.table = table;
        }

        @Override
        public boolean hasNext() {
            int rowIdx = this.rowIdx;
            int colIdx = this.colIdx;

            while (true) {
                boolean rowInsideTable = rowIdx < this.table.size();
                if (!rowInsideTable) {
                    return false;
                }

                boolean colInsideRow = colIdx < this.table.get(rowIdx).size();
                if (!colInsideRow) {
                    rowIdx++;
                    colIdx = 0;
                    continue;
                }

                boolean isPiece = this.table.get(rowIdx).get(colIdx) instanceof IPiece;

                if (isPiece) {
                    return true;
                }

                colIdx++;
            }
        }

        @Override
        public IPiece next() {
            if (!this.hasNext()) {
                return null;
            }

            while (true) {
                boolean colInsideRow = this.colIdx < this.table.get(this.rowIdx).size();
                if (!colInsideRow) {
                    this.rowIdx++;
                    this.colIdx = 0;
                    continue;
                }

                IPiece square = this.table.get(this.rowIdx).get(this.colIdx);
                this.colIdx++;

                if (square instanceof IPiece) {
                    return square;
                }
            }
        }
    }
}
