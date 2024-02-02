package com.vini.game.piece.pieces;

import com.vini.game.enums.PieceEnum;
import com.vini.game.interfaces.IPiece;
import com.vini.game.lib.Position;
import com.vini.game.piece.Piece;
import com.vini.game.piece.PieceHelper;

public class Bishop extends Piece {

    private final int[][] directions = {{1, -1}, {1, 1}, {-1, 1}, {-1, -1}};

    @Override
    public IPiece updateMoves() {
        Position position = new Position(null, null);
        for (int[] direction : this.directions) {
            position.x = this.getPosition().x.intValue();
            position.y = this.getPosition().y.intValue();

            while (true) {
                position.x += direction[0];
                position.y += direction[1];

                if (!this.board.isInsideTable(position)) {
                    break;
                }
                ;

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
        return String.join(" ", PieceEnum.BISHOP.toString(), this.getColor().toString());
    }
}
