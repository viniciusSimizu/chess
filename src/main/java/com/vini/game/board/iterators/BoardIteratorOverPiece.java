package com.vini.game.board.iterators;

import com.vini.game.interfaces.IPiece;

import java.util.Iterator;
import java.util.List;

public class BoardIteratorOverPiece implements Iterator<IPiece> {

    private int currIndex = 0;
    private List<IPiece> pieces;

    public BoardIteratorOverPiece(List<IPiece> pieces) {
        this.pieces = pieces;
    }

    @Override
    public boolean hasNext() {
        int localIndex = this.currIndex;

        while (localIndex < this.pieces.size()) {
            if (this.pieces.get(localIndex) != null) {
                return true;
            }
            localIndex++;
        }
        return false;
    }

    @Override
    public IPiece next() {
        if (!this.hasNext()) {
            return null;
        }

        IPiece piece = null;
        while (this.currIndex < this.pieces.size()) {

            piece = this.pieces.get(this.currIndex);
            this.currIndex++;

            if (piece != null) {
                return piece;
            }
        }
        return null;
    }
}
