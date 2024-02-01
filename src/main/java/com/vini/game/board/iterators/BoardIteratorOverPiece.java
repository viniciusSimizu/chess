package com.vini.game.board.iterators;

import com.vini.game.piece.IPiece;

import java.util.Iterator;
import java.util.List;

public class BoardIteratorOverPiece implements Iterator<IPiece> {
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
