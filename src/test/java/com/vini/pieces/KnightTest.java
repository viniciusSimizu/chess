package com.vini.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.vini.game.board.BoardBuilder;
import com.vini.game.enums.ColorEnum;
import com.vini.game.fen.Fen;
import com.vini.game.lib.Position;
import com.vini.game.piece.pieces.Knight;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class KnightTest {

    private Knight knight;
    private final Position position = new Position(null, null);
    private final BoardBuilder builder = new BoardBuilder();

    @BeforeEach
    public void setup() {
        this.knight = new Knight();
        this.position.x = null;
        this.position.y = null;
        this.builder.reset();
    }

    @Test
    public void CanMoveOnEverySquare_Test() {
        var board = Fen.build("5/5/5/5/5");
        this.position.x = 2;
        this.position.y = 2;
        this.knight.setPosition(this.position);

        board.setSquareValue(position, this.knight);
        this.knight.setBoard(board);
        board.updatePieceMovements();

        var actual = this.knight.exportMoves();
        var expected = new ArrayList<Boolean>();
        expected.addAll(List.of(false, true, false, true, false));
        expected.addAll(List.of(true, false, false, false, true));
        expected.addAll(List.of(false, false, false, false, false));
        expected.addAll(List.of(true, false, false, false, true));
        expected.addAll(List.of(false, true, false, true, false));

        assertEquals(expected, actual);
    }

    @Test
    public void AllyBlockMovement_Test() {
        var board = Fen.build("3/2p/1p1");
        this.position.x = 0;
        this.position.y = 0;
        this.knight.setColor(ColorEnum.BLACK);
        this.knight.setPosition(this.position);

        board.setSquareValue(position, this.knight);
        this.knight.setBoard(board);
        board.updatePieceMovements();

        var actual = this.knight.exportMoves();
        var expected = new ArrayList<Boolean>();
        expected.addAll(List.of(false, false, false));
        expected.addAll(List.of(false, false, false));
        expected.addAll(List.of(false, false, false));

        assertEquals(expected, actual);
    }

    @Test
    public void EnemyMovementCaptures_Test() {
        var board = Fen.build("3/2p/1p1");
        this.position.x = 0;
        this.position.y = 0;
        this.knight.setColor(ColorEnum.WHITE);
        this.knight.setPosition(this.position);

        board.setSquareValue(position, this.knight);
        this.knight.setBoard(board);
        board.updatePieceMovements();

        var actual = this.knight.exportMoves();
        var expected = new ArrayList<Boolean>();
        expected.addAll(List.of(false, false, false));
        expected.addAll(List.of(false, false, true));
        expected.addAll(List.of(false, true, false));

        assertEquals(expected, actual);
    }
}
