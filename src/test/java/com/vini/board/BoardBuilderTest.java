package com.vini.board;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.vini.game.board.BoardBuilder;
import com.vini.game.enums.ColorEnum;
import com.vini.game.enums.PieceEnum;
import com.vini.game.interfaces.IPiece;
import com.vini.game.lib.Position;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardBuilderTest {

    private BoardBuilder builder;

    @BeforeEach
    public void setup() {
        this.builder = new BoardBuilder();
    }

    @Test
    public void DefaultGameGeneration_Test() {
        this.builder
                .buildPiece(PieceEnum.ROOK, ColorEnum.BLACK)
                .buildPiece(PieceEnum.KNIGHT, ColorEnum.BLACK)
                .buildPiece(PieceEnum.BISHOP, ColorEnum.BLACK)
                .buildPiece(PieceEnum.QUEEN, ColorEnum.BLACK)
                .buildPiece(PieceEnum.KING, ColorEnum.BLACK)
                .buildPiece(PieceEnum.BISHOP, ColorEnum.BLACK)
                .buildPiece(PieceEnum.KNIGHT, ColorEnum.BLACK)
                .buildPiece(PieceEnum.ROOK, ColorEnum.BLACK)
                .buildRow();
        this.builder
                .buildPiece(PieceEnum.PAWN, ColorEnum.BLACK)
                .buildPiece(PieceEnum.PAWN, ColorEnum.BLACK)
                .buildPiece(PieceEnum.PAWN, ColorEnum.BLACK)
                .buildPiece(PieceEnum.PAWN, ColorEnum.BLACK)
                .buildPiece(PieceEnum.PAWN, ColorEnum.BLACK)
                .buildPiece(PieceEnum.PAWN, ColorEnum.BLACK)
                .buildPiece(PieceEnum.PAWN, ColorEnum.BLACK)
                .buildPiece(PieceEnum.PAWN, ColorEnum.BLACK)
                .buildRow();
        this.builder
                .buildEmptySquare()
                .buildEmptySquare()
                .buildEmptySquare()
                .buildEmptySquare()
                .buildEmptySquare()
                .buildEmptySquare()
                .buildEmptySquare()
                .buildEmptySquare()
                .buildRow();
        this.builder
                .buildEmptySquare()
                .buildEmptySquare()
                .buildEmptySquare()
                .buildEmptySquare()
                .buildEmptySquare()
                .buildEmptySquare()
                .buildEmptySquare()
                .buildEmptySquare()
                .buildRow();
        this.builder
                .buildEmptySquare()
                .buildEmptySquare()
                .buildEmptySquare()
                .buildEmptySquare()
                .buildEmptySquare()
                .buildEmptySquare()
                .buildEmptySquare()
                .buildEmptySquare()
                .buildRow();
        this.builder
                .buildEmptySquare()
                .buildEmptySquare()
                .buildEmptySquare()
                .buildEmptySquare()
                .buildEmptySquare()
                .buildEmptySquare()
                .buildEmptySquare()
                .buildEmptySquare()
                .buildRow();
        this.builder
                .buildPiece(PieceEnum.PAWN, ColorEnum.WHITE)
                .buildPiece(PieceEnum.PAWN, ColorEnum.WHITE)
                .buildPiece(PieceEnum.PAWN, ColorEnum.WHITE)
                .buildPiece(PieceEnum.PAWN, ColorEnum.WHITE)
                .buildPiece(PieceEnum.PAWN, ColorEnum.WHITE)
                .buildPiece(PieceEnum.PAWN, ColorEnum.WHITE)
                .buildPiece(PieceEnum.PAWN, ColorEnum.WHITE)
                .buildPiece(PieceEnum.PAWN, ColorEnum.WHITE)
                .buildRow();
        this.builder
                .buildPiece(PieceEnum.ROOK, ColorEnum.WHITE)
                .buildPiece(PieceEnum.KNIGHT, ColorEnum.WHITE)
                .buildPiece(PieceEnum.BISHOP, ColorEnum.WHITE)
                .buildPiece(PieceEnum.QUEEN, ColorEnum.WHITE)
                .buildPiece(PieceEnum.KING, ColorEnum.WHITE)
                .buildPiece(PieceEnum.BISHOP, ColorEnum.WHITE)
                .buildPiece(PieceEnum.KNIGHT, ColorEnum.WHITE)
                .buildPiece(PieceEnum.ROOK, ColorEnum.WHITE)
                .buildRow();

        var board = this.builder.getResult();
        Position position = new Position(null, null);
        int height = 8, width = 8;

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                position.y = row;
                position.x = column;
                IPiece piece = board.findPiece(position);

                if (piece == null) {
                    continue;
                }

                var piecePosition = piece.getPosition();
                assertEquals(position.y, piecePosition.y);
                assertEquals(position.x, piecePosition.x);
            }
        }
    }
}
