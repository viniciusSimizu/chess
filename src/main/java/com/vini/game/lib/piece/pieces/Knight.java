package com.vini.game.piece.pieces;

import com.vini.game.enums.PieceEnum;
import com.vini.game.interfaces.IPiece;
import com.vini.game.piece.Piece;
import com.vini.game.structs.Position;

import java.util.List;

public class Knight extends Piece {

    private static final int[][] directions = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    private static final int forwardWeight = 2;

    @Override
    public void updateMoves() {
        var localPosition = new Position(null, null);

        for (int[] direction : directions) {
            for (int sideDirection : List.of(-1, 1)) {
                localPosition.update(this.position);

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
    }

    @Override
    public PieceEnum getType() {
        return PieceEnum.KNIGHT;
    }
}
