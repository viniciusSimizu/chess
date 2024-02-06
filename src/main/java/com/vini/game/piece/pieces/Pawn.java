package com.vini.game.piece.pieces;

import com.vini.game.enums.ColorEnum;
import com.vini.game.enums.PieceEnum;
import com.vini.game.interfaces.IPiece;
import com.vini.game.lib.Position;
import com.vini.game.piece.Piece;

public class Pawn extends Piece {

    private final int[][] forkDirections = {{-1, 1}, {1, 1}};
    private int directionWeight = 0;

    private int movedTwoRound = -1;
    private boolean isFirstMoveFlag = true;

    @Override
    public void setColor(ColorEnum color) {
        super.setColor(color);
        if (color == ColorEnum.WHITE) {
            this.directionWeight = -1;
        } else {
            this.directionWeight = 1;
        }
    }

    @Override
    public IPiece updateMoves() {
        this.resetMoves();
        int maxDistance = this.isFirstMoveFlag ? 2 : 1;
        var localPosition = new Position(null, null);

        for (int range = 1; range <= maxDistance; range++) {
            localPosition.x = this.position.x;
            localPosition.y = this.position.y;
            localPosition.y += range * this.directionWeight;

            if (!board.isInsideTable(localPosition)) {
                break;
            }

            IPiece target = board.findPiece(localPosition);
            if (target != null) {
                break;
            }

            this.moves.set(localPosition.getIndex(this.board.getWidth()), true);
        }

        for (int[] forkDirection : this.forkDirections) {
            localPosition.x = this.position.x;
            localPosition.y = this.position.y;
            localPosition.x += forkDirection[0];
            localPosition.y += forkDirection[1] * this.directionWeight;

            if (!this.board.isInsideTable(localPosition)) {
                continue;
            }

            IPiece target = board.findPiece(localPosition);
            if (this.isEnemy(target)) {
                this.moves.set(localPosition.getIndex(this.board.getWidth()), true);
            }
        }

        for (int[] enPassantDirection : this.forkDirections) {
            localPosition.x = this.position.x;
            localPosition.y = this.position.y;
            localPosition.x += enPassantDirection[0];

            if (!this.board.isInsideTable(localPosition)) {
                continue;
            }

            IPiece target = board.findPiece(localPosition);
            if (!this.isEnemy(target) || !(target instanceof Pawn)) {
                continue;
            }

            Pawn pawnTarget = (Pawn) target;
            boolean hasMovedTwo = pawnTarget.movedTwoRound != -1;
            int roundSinceMovedTwo = this.board.getRound() - pawnTarget.movedTwoRound;

            if (!hasMovedTwo || roundSinceMovedTwo != 1) {
                continue;
            }

            int backupY = localPosition.y;
            localPosition.y += enPassantDirection[1] * this.directionWeight;
            this.moves.set(localPosition.getIndex(this.board.getWidth()), true);
            localPosition.y = backupY;
        }

        return this;
    }

    @Override
    public boolean tryMove(Position to) {
        boolean tryingToMoveTwo = Math.abs(this.position.y - to.y) == 2;
        boolean successMove = super.tryMove(to);

        if (!successMove) {
            return false;
        }

        this.isFirstMoveFlag = false;
        if (tryingToMoveTwo) {
            this.movedTwoRound = this.board.getRound();
        }

        return true;
    }

    @Override
    public PieceEnum getType() {
        return PieceEnum.PAWN;
    }

    public void setMovedTwoRound(int movedTwoRound) {
        this.movedTwoRound = movedTwoRound;
    }

    public void setIsFirstMoveFlag(boolean isFirstMoveFlag) {
        this.isFirstMoveFlag = isFirstMoveFlag;
    }
}
