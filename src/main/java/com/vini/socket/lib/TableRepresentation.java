package com.vini.socket.lib;

import com.vini.game.board.Board;
import com.vini.game.interfaces.IPiece;
import com.vini.game.lib.Position;

import java.util.ArrayList;
import java.util.List;

public class TableRepresentation {

    public List<Row> rows;

    public TableRepresentation(Board board) {
        var position = new Position(null, null);
        this.rows = new ArrayList<>(board.getHeight());

        for (int y = 0; y < board.getHeight(); y++) {
            var row = new ArrayList<IPiece>(board.getWidth());

            for (int x = 0; x < board.getWidth(); x++) {
                position.y = y;
                position.x = x;
                row.add(board.findPiece(position));
            }
            this.rows.add(new Row(row));
        }
    }
}

class Row {

    public List<Square> squares;

    public Row(List<IPiece> row) {
        this.squares = row.stream().map(Square::new).toList();
    }
}

class Square {

    public boolean isPiece;
    public String classes;

    public Square(IPiece piece) {
        if (piece == null) {
            this.isPiece = false;
            this.classes = null;
            return;
        }

        this.isPiece = true;
        this.classes = piece.getIdentifier();
    }
}
