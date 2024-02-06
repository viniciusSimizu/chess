package com.vini.game.piece.pieces;

import com.vini.game.enums.PieceEnum;
import com.vini.game.interfaces.IPiece;
import com.vini.game.lib.Position;
import com.vini.game.piece.Piece;

public class King extends Piece {

    private final int[][] directions = {
        {-1, -1}, {0, -1}, {1, -1}, {-1, 0},
        {1, 0}, {-1, 1}, {0, 1}, {1, 1}
    };
    private boolean isFirstMove = true;

    @Override
    public IPiece updateMoves() {
        var localPosition = new Position(null, null);

        for (int[] direction : this.directions) {
            localPosition.x = this.position.x + direction[0];
            localPosition.y = this.position.y + direction[1];

            if (!board.isInsideTable(localPosition)) {
                continue;
            }
            ;

            IPiece target = board.findPiece(localPosition);

            if (this.isAlly(target)) {
                continue;
            }

            this.moves.set(localPosition.getIndex(this.board.getWidth()), true);
        }

        return this;
    }

    public boolean isFirstMove() {
        return this.isFirstMove;
    }

    @Override
    public PieceEnum getType() {
        return PieceEnum.KING;
    }
}
