package com.vini.socket.lib;

import com.vini.game.board.Board;
import com.vini.game.interfaces.IPiece;
import com.vini.game.lib.Position;

import java.util.ArrayList;
import java.util.List;

public class TableRepresentation {

    private Board board;
    public List<Row> rows;

    public TableRepresentation(Board board) {
        this.rows = new ArrayList<>(board.getHeight());
        this.board = board;

        for (int row = 0; row < board.getHeight(); row++) {
            var position = new Position(null, row);
            this.rows.add(new Row(position));
        }
    }

    class Row {

        public List<Square> squares;

        public Row(Position position) {
            this.squares = new ArrayList<>(board.getWidth());

            for (int column = 0; column < board.getWidth(); column++) {
                position.x = column;
                this.squares.add(new Square(position));
            }
        }
    }

    class Square {

        public boolean isPiece;
        public String classes;
        public List<Move> moves = new ArrayList<>();

        public Square(Position position) {
            IPiece piece = board.findPiece(position);

            if (piece == null) {
                this.isPiece = false;
                return;
            }

            this.isPiece = true;
            this.classes = piece.getIdentifiers();
            var moveTable = piece.getMoves();
            String identifier = piece.getIdentifiers();
            if (identifier.contains("knight") && identifier.contains("black")) {
                this.viewMoves(piece);
            }
            for (int i = 0; i < moveTable.size(); i++) {
                if (!moveTable.get(i)) {
                    continue;
                }

                int x = i % board.getWidth();
                int y = Math.floorDiv(i, board.getWidth());
                var movePosition = new Position(x, y);
                this.moves.add(new Move(piece, movePosition));
            }
        }

        private void viewMoves(IPiece piece) {
            var message = new StringBuilder();
            var position = piece.getPosition();
            var identifier = piece.getIdentifiers();
            message.append(
                    "piece: %s | x: %d | y: %d\n".formatted(identifier, position.x, position.y));
            message.append("CAN MOVE TO:\n");

            var moves = piece.getMoves();
            for (int i = 0; i < moves.size(); i++) {
                if (!moves.get(i)) {
                    continue;
                }

                var x = i % board.getWidth();
                var y = Math.floorDiv(i, board.getWidth());
                message.append("x: %d | y: %d\n".formatted(x, y));
            }
            System.out.println(message);
        }
    }

    class Move {
        public int offsetX, offsetY;

        public Move(IPiece piece, Position movePosition) {
            this.offsetX = movePosition.x - piece.getPosition().x;
            this.offsetY = movePosition.y - piece.getPosition().y;
        }
    }
}
