package com.vini.game.piece.pieces;

import com.vini.game.board.Board;
import com.vini.game.enums.ColorEnum;
import com.vini.game.enums.PieceEnum;
import com.vini.game.lib.Position;
import com.vini.game.piece.IPiece;
import com.vini.game.piece.Piece;
import com.vini.game.piece.PieceHelper;

public class Pawn extends Piece {

    private final int[][] forkDirections = {{-1, 1}, {1, 1}};
    private final int[][] enPassantDirections = {{-1, 0}, {1, 0}};
    private int directionWeight = 0;

    private Integer movedTwoRound = -1;
    private boolean isFirstMoveFlag = true;

    public Pawn(Board board, ColorEnum color) {
        super(board, color);

        if (color == ColorEnum.WHITE) {
            this.directionWeight = -1;
        } else {
            this.directionWeight = 1;
        }
    }

    @Override
    public IPiece updateMoves() {
        this.resetMoves();
        Position position = new Position(null, null);
        int maxDistance = this.isFirstMoveFlag ? 2 : 1;

        for (int range = 1; range <= maxDistance; range++) {
            position.x = this.position().x.intValue();
            position.y = this.position().y.intValue();
            position.y += range * this.directionWeight;

            if (!board.isInsideTable(position)) {
                break;
            }
            ;

            IPiece target = board.findPiece(position);

            if (target != null) {
                break;
            }

            this.moves().get(position.y).set(position.x, true);
        }

        for (int[] forkDirection : this.forkDirections) {
            position.x = this.position().x.intValue();
            position.y = this.position().y.intValue();
            position.x += forkDirection[0];
            position.y += forkDirection[1] * this.directionWeight;

            if (!this.board.isInsideTable(position)) {
                continue;
            }

            IPiece target = board.findPiece(position);

            if (PieceHelper.isEnemy(this, target)) {
                this.moves().get(position.y).set(position.x, true);
            }
        }

        for (int[] enPassantDirection : this.enPassantDirections) {
            position.x = this.position().x.intValue();
            position.y = this.position().y.intValue();
            position.x += enPassantDirection[0];

            if (!this.board.isInsideTable(position)) {
                continue;
            }

            IPiece target = board.findPiece(position);

            if (!PieceHelper.isEnemy(this, target) || !(target instanceof Pawn)) {
                continue;
            }

            Pawn pawnTarget = (Pawn) target;

            boolean hasMovedTwo = pawnTarget.movedTwoRound != -1;
            int roundSinceMovedTwo = this.board.getRound() - pawnTarget.movedTwoRound;
            if (!hasMovedTwo || roundSinceMovedTwo != 1) {
                continue;
            }

            int backupY = position.y.intValue();
            position.y += this.directionWeight;

            this.moves().get(position.y).set(position.x, true);
            position.y = backupY;
        }

        return this;
    }

    @Override
    public boolean tryMove(Position to) {
        boolean tryingToMoveTwo = Math.abs(this.position.y - to.y) == 2;
        boolean successMove = super.tryMove(to);

        if (successMove) {
            this.isFirstMoveFlag = false;
            if (tryingToMoveTwo) {
                this.movedTwoRound = this.board.getRound();
            }
        }

        return successMove;
    }

    @Override
    public String getIdentifier() {
        return String.join(" ", PieceEnum.PAWN.toString(), this.color().toString());
    }
}
