package com.vini.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.vini.game.board.BoardBuilder;
import com.vini.game.enums.ColorEnum;
import com.vini.game.fen.Fen;
import com.vini.game.lib.Position;
import com.vini.game.piece.pieces.King;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class KingTest {

    private King king;
    private final Position position = new Position(null, null);
    private final BoardBuilder builder = new BoardBuilder();

    @BeforeEach
    public void setup() {
        this.king = new King();
        this.position.x = null;
        this.position.y = null;
        this.builder.reset();
    }

    @Test
    public void CanMoveOnEverySquare_Test() {
        var board = Fen.build("5/5/5/5/5");
        this.position.x = 2;
        this.position.y = 2;
        this.king.setPosition(this.position);

        board.setSquarePiece(position, this.king);
        this.king.setBoard(board);
        board.updatePieceMovements();

        var actual = this.king.getMoves();
        var expected = new ArrayList<Boolean>();
        expected.addAll(List.of(false, false, false, false, false));
        expected.addAll(List.of(false, true, true, true, false));
        expected.addAll(List.of(false, true, false, true, false));
        expected.addAll(List.of(false, true, true, true, false));
        expected.addAll(List.of(false, false, false, false, false));

        assertEquals(expected, actual);
    }

    @Test
    public void AllyBlockMovement_Test() {
        var board = Fen.build("5/2pp1/1p1p1/1ppp1/5");
        this.position.x = 2;
        this.position.y = 2;
        this.king.setColor(ColorEnum.BLACK);
        this.king.setPosition(this.position);

        board.setSquarePiece(position, this.king);
        this.king.setBoard(board);
        board.updatePieceMovements();

        var actual = this.king.getMoves();
        var expected = new ArrayList<Boolean>();
        expected.addAll(List.of(false, false, false, false, false));
        expected.addAll(List.of(false, true, false, false, false));
        expected.addAll(List.of(false, false, false, false, false));
        expected.addAll(List.of(false, false, false, false, false));
        expected.addAll(List.of(false, false, false, false, false));

        assertEquals(expected, actual);
    }

    @Test
    public void EnemyMovementCaptures_Test() {
        var board = Fen.build("5/2pp1/1p1p1/1ppp1/5");
        this.position.x = 2;
        this.position.y = 2;
        this.king.setColor(ColorEnum.WHITE);
        this.king.setPosition(this.position);

        board.setSquarePiece(position, this.king);
        this.king.setBoard(board);
        board.updatePieceMovements();

        var actual = this.king.getMoves();
        var expected = new ArrayList<Boolean>();
        expected.addAll(List.of(false, false, false, false, false));
        expected.addAll(List.of(false, true, true, true, false));
        expected.addAll(List.of(false, true, false, true, false));
        expected.addAll(List.of(false, true, true, true, false));
        expected.addAll(List.of(false, false, false, false, false));

        assertEquals(expected, actual);
    }
}
