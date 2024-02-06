package com.vini.game.piece.pieces;

import com.vini.game.enums.PieceEnum;
import com.vini.game.interfaces.IPiece;
import com.vini.game.lib.Position;
import com.vini.game.piece.Piece;

import java.util.List;

public class Knight extends Piece {

    private static final int[][] directions = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    private static final int forwardWeight = 2;

    @Override
    public IPiece updateMoves() {
        var localPosition = new Position(null, null);

        for (int[] direction : directions) {
            for (int sideDirection : List.of(-1, 1)) {

                localPosition.x = this.position.x;
                localPosition.y = this.position.y;

                localPosition.x += forwardWeight * direction[0];
                localPosition.y += forwardWeight * direction[1];

                localPosition.x += Math.abs(direction[1]) * sideDirection;
                localPosition.y += Math.abs(direction[0]) * sideDirection;

                if (!board.isInsideTable(localPosition)) {
                    continue;
                }

                IPiece target = board.findPiece(localPosition);
                if (this.isAlly(target)) {
                    continue;
                }

                this.moves.set(localPosition.getIndex(this.board.getWidth()), true);
            }
        }

        return this;
    }

    @Override
    public PieceEnum getType() {
        return PieceEnum.KNIGHT;
    }
}
