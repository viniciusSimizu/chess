package com.vini.game.lib.board.iterators;

import com.vini.game.interfaces.IBoard;
import com.vini.game.interfaces.IPiece;
import com.vini.game.structs.Position;

import java.util.Iterator;

public class BoardIteratorOverPiece implements Iterator<IPiece> {

    private int currIndex = 0;
    private IBoard board;

    public BoardIteratorOverPiece(IBoard board) {
        this.board = board;
    }

    @Override
    public boolean hasNext() {
        int localIndex = this.currIndex;
        var size = this.board.getWidth() * this.board.getHeight();
        var position = new Position(null, null);

        while (localIndex < size) {
            position.updateFromIndex(localIndex, this.board.getWidth());
            var square = this.board.findSquare(position);
            if (square.getOccupiedBy() != null) {
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

        IPiece piece;
        var position = new Position(null, null);
        var size = this.board.getWidth() * this.board.getHeight();

        while (this.currIndex < size) {
            position.updateFromIndex(this.currIndex, this.board.getWidth());
            var square = this.board.findSquare(position);
            piece = square.getOccupiedBy();
            this.currIndex++;

            if (piece != null) {
                return piece;
            }
        }
        return null;
    }
}
