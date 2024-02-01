package com.vini.game.piece.pieces;

import com.vini.game.board.Board;
import com.vini.game.enums.ColorEnum;
import com.vini.game.enums.PieceEnum;
import com.vini.game.lib.Position;
import com.vini.game.piece.IPiece;
import com.vini.game.piece.Piece;
import com.vini.game.piece.PieceHelper;

public class King extends Piece {

    private final int[][] directions = {
        {-1, -1}, {0, -1}, {1, -1}, {-1, 0},
        {1, 0}, {-1, 1}, {0, 1}, {1, 1}
    };
    private boolean isFirstMove = true;

    public King(Board board, ColorEnum color) {
        super(board, color);
    }

    @Override
    public IPiece updateMoves() {
        Position position = new Position(null, null);
        for (int[] direction : this.directions) {
            position.x = this.position().x.intValue() + direction[0];
            position.y = this.position().y.intValue() + direction[1];

            if (!board.isInsideTable(position)) {
                continue;
            }
            ;

            IPiece target = board.findPiece(position);

            if (PieceHelper.isAlly(this, target)) {
                continue;
            }

            this.moves().get(position.y).set(position.x, true);
        }

        return this;
    }

    public boolean isFirstMove() {
        return this.isFirstMove;
    }

    @Override
    public String getIdentifier() {
        return String.join(" ", PieceEnum.KING.toString(), this.color().toString());
    }
}
