package com.vini.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.vini.game.board.BoardBuilder;
import com.vini.game.enums.ColorEnum;
import com.vini.game.fen.Fen;
import com.vini.game.lib.Position;
import com.vini.game.piece.pieces.Queen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class QueenTest {

    private Queen queen;
    private final Position position = new Position(null, null);
    private final BoardBuilder builder = new BoardBuilder();

    @BeforeEach
    public void setup() {
        this.queen = new Queen();
        this.position.x = null;
        this.position.y = null;
        this.builder.reset();
    }

    @Test
    public void CanMoveOnEverySquare_Test() {
        var board = Fen.build("5/5/5/5/5");
        this.position.x = 2;
        this.position.y = 2;
        this.queen.setPosition(this.position);

        board.setSquarePiece(position, this.queen);
        this.queen.setBoard(board);
        board.updatePieceMovements();

        var actual = this.queen.getMoves();
        var expected = new ArrayList<Boolean>();
        expected.addAll(List.of(true, false, true, false, true));
        expected.addAll(List.of(false, true, true, true, false));
        expected.addAll(List.of(true, true, false, true, true));
        expected.addAll(List.of(false, true, true, true, false));
        expected.addAll(List.of(true, false, true, false, true));

        assertEquals(expected, actual);
    }

    @Test
    public void AllyBlockMovement_Test() {
        var board = Fen.build("1p1/3/2p");
        this.position.x = 0;
        this.position.y = 0;
        this.queen.setColor(ColorEnum.BLACK);
        this.queen.setPosition(this.position);

        board.setSquarePiece(position, this.queen);
        this.queen.setBoard(board);
        board.updatePieceMovements();

        var actual = this.queen.getMoves();
        var expected = new ArrayList<Boolean>();
        expected.addAll(List.of(false, false, false));
        expected.addAll(List.of(true, true, false));
        expected.addAll(List.of(true, false, false));

        assertEquals(expected, actual);
    }

    @Test
    public void EnemyMovementCaptures_Test() {
        var board = Fen.build("1p1/3/2p");
        this.position.x = 0;
        this.position.y = 0;
        this.queen.setColor(ColorEnum.WHITE);
        this.queen.setPosition(this.position);

        board.setSquarePiece(position, this.queen);
        this.queen.setBoard(board);
        board.updatePieceMovements();

        var actual = this.queen.getMoves();
        var expected = new ArrayList<Boolean>();
        expected.addAll(List.of(false, true, false));
        expected.addAll(List.of(true, true, false));
        expected.addAll(List.of(true, false, true));

        assertEquals(expected, actual);
    }
}
