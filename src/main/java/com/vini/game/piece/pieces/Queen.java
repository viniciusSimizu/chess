package com.vini.game.piece.pieces;

import com.vini.game.enums.PieceEnum;
import com.vini.game.interfaces.IPiece;
import com.vini.game.lib.Position;
import com.vini.game.piece.Piece;

public class Queen extends Piece {

    private final int[][] directions = {
        {-1, -1}, {0, -1}, {1, -1}, {-1, 0},
        {1, 0}, {-1, 1}, {0, 1}, {1, 1}
    };

    @Override
    public IPiece updateMoves() {
        var localPosition = new Position(null, null);

        for (int[] direction : this.directions) {
            localPosition.x = this.position.x;
            localPosition.y = this.position.y;

            while (true) {
                localPosition.x += direction[0];
                localPosition.y += direction[1];

                if (!board.isInsideTable(localPosition)) {
                    break;
                }
                ;

                IPiece target = board.findPiece(localPosition);

                if (this.isAlly(target)) {
                    break;
                }

                this.moves.set(localPosition.getIndex(this.board.getWidth()), true);

                if (this.isEnemy(target)) {
                    break;
                }
            }
        }

        return this;
    }

    @Override
    public PieceEnum getType() {
        return PieceEnum.QUEEN;
    }
}
