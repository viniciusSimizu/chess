package com.vini.game.piece.pieces;

import com.vini.game.enums.PieceEnum;
import com.vini.game.interfaces.IPiece;
import com.vini.game.lib.Position;
import com.vini.game.piece.Piece;
import com.vini.game.piece.PieceHelper;

import java.util.List;

public class Knight extends Piece {

    private final int[][] directions = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    private final int forwardStepWeight = 2;
    private final int sideStepWeight = 1;

    @Override
    public IPiece updateMoves() {
        Position position = new Position(null, null);
        for (int[] direction : this.directions) {
            for (int sideStepDirection : List.of(-1, 1)) {
                position.x = this.getPosition().x.intValue();
                position.y = this.getPosition().y.intValue();

                position.x += this.forwardStepWeight * direction[0];
                position.y += this.forwardStepWeight * direction[1];

                position.x += Math.abs(direction[1]) * sideStepDirection * sideStepWeight;
                position.y += Math.abs(direction[0]) * sideStepDirection * sideStepWeight;

                if (!board.isInsideTable(position)) {
                    continue;
                }

                IPiece target = board.findPiece(position);

                if (PieceHelper.isAlly(this, target)) {
                    break;
                }

                this.moves.set(this.board.getPositionIndex(position), true);

                if (PieceHelper.isEnemy(this, target)) {
                    break;
                }
            }
        }

        return this;
    }

    @Override
    public String getIdentifier() {
        return String.join(" ", PieceEnum.KNIGHT.toString(), this.getColor().toString());
    }
}
