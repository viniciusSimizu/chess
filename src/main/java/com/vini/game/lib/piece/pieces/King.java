package com.vini.game.lib.piece.pieces;

import com.vini.game.enums.PieceEnum;
import com.vini.game.interfaces.IPiece;
import com.vini.game.piece.Piece;
import com.vini.game.structs.Position;

public class King extends Piece {

    private static final int[][] directions = {
        {-1, -1}, {0, -1}, {1, -1}, {-1, 0},
        {1, 0}, {-1, 1}, {0, 1}, {1, 1}
    };

    @Override
    public void updateMoves() {
        var localPosition = new Position(null, null);

        for (int[] direction : directions) {
            localPosition.update(this.position);
            localPosition.x += direction[0];
            localPosition.y += direction[1];

            if (!board.isInsideTable(localPosition)) {
                continue;
            }

            IPiece target = board.findPiece(localPosition);
            if (this.isAlly(target)) {
                continue;
            }

            var localPositionIndex = localPosition.getIndex(this.board.getWidth());
            this.moves.set(localPositionIndex, true);
        }
    }

    @Override
    public PieceEnum getType() {
        return PieceEnum.KING;
    }
}
