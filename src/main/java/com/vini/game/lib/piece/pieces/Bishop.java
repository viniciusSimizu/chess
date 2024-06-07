package com.vini.game.piece.pieces;

import com.vini.game.enums.PieceEnum;
import com.vini.game.interfaces.IPiece;
import com.vini.game.piece.Piece;
import com.vini.game.structs.Position;

public class Bishop extends Piece {

    private static final int[][] directions = {{1, -1}, {1, 1}, {-1, 1}, {-1, -1}};

    @Override
    public void updateMoves() {
        var localPosition = new Position(null, null);

        for (int[] direction : directions) {
            localPosition.update(this.position);

            while (true) {
                localPosition.x += direction[0];
                localPosition.y += direction[1];

                if (!this.board.isInsideTable(localPosition)) {
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
    }

    @Override
    public PieceEnum getType() {
        return PieceEnum.BISHOP;
    }
}
