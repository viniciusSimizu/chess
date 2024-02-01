package com.vini.game.piece.pieces;

import com.vini.game.board.Board;
import com.vini.game.enums.ColorEnum;
import com.vini.game.enums.PieceEnum;
import com.vini.game.lib.Position;
import com.vini.game.piece.IPiece;
import com.vini.game.piece.Piece;
import com.vini.game.piece.PieceHelper;

public class Queen extends Piece {

    private final int[][] directions = {
        {-1, -1}, {0, -1}, {1, -1}, {-1, 0},
        {1, 0}, {-1, 1}, {0, 1}, {1, 1}
    };

    public Queen(Board board, ColorEnum color) {
        super(board, color);
    }

    @Override
    public IPiece updateMoves() {
        Position position = new Position(null, null);
        for (int[] direction : this.directions) {
            position.x = this.position().x.intValue();
            position.y = this.position().y.intValue();

            while (true) {
                position.x += direction[0];
                position.y += direction[1];

                if (!board.isInsideTable(position)) {
                    break;
                }
                ;

                IPiece target = board.findPiece(position);

                if (PieceHelper.isAlly(this, target)) {
                    break;
                }

                this.moves().get(position.y).set(position.x, true);

                if (PieceHelper.isEnemy(this, target)) {
                    break;
                }
            }
        }

        return this;
    }

    @Override
    public String getIdentifier() {
        return String.join(" ", PieceEnum.QUEEN.toString(), this.color().toString());
    }
}
