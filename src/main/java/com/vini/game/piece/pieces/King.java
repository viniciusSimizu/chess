package com.vini.game.piece.pieces;

import com.vini.game.enums.PieceEnum;
import com.vini.game.interfaces.IPiece;
import com.vini.game.lib.Position;
import com.vini.game.piece.Piece;
import com.vini.game.piece.PieceHelper;

public class King extends Piece {

    private final int[][] directions = {
        {-1, -1}, {0, -1}, {1, -1}, {-1, 0},
        {1, 0}, {-1, 1}, {0, 1}, {1, 1}
    };
    private boolean isFirstMove = true;

    @Override
    public IPiece updateMoves() {
        Position position = new Position(null, null);
        for (int[] direction : this.directions) {
            position.x = this.getPosition().x.intValue() + direction[0];
            position.y = this.getPosition().y.intValue() + direction[1];

            if (!board.isInsideTable(position)) {
                continue;
            }
            ;

            IPiece target = board.findPiece(position);

            if (PieceHelper.isAlly(this, target)) {
                continue;
            }

            this.getMoves().get(position.y).set(position.x, true);
        }

        return this;
    }

    public boolean isFirstMove() {
        return this.isFirstMove;
    }

    @Override
    public String getIdentifier() {
        return String.join(" ", PieceEnum.KING.toString(), this.getColor().toString());
    }
}
