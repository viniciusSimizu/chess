package com.vini.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.vini.game.board.BoardBuilder;
import com.vini.game.enums.ColorEnum;
import com.vini.game.fen.Fen;
import com.vini.game.lib.Position;
import com.vini.game.piece.pieces.Rook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class RookTest {

    private Rook rook;
    private final Position position = new Position(null, null);
    private final BoardBuilder builder = new BoardBuilder();

    @BeforeEach
    public void setup() {
        this.rook = new Rook();
        this.position.x = null;
        this.position.y = null;
        this.builder.reset();
    }

    @Test
    public void Movement_Test() {
        var board = Fen.build("5/5/5/5/5");
        this.position.x = 2;
        this.position.y = 2;
        this.rook.setPosition(this.position);

        board.setSquareValue(position, this.rook);
        this.rook.setBoard(board);
        board.updatePieceMovements();

        var actual = this.rook.exportMoves();
        var expected = new ArrayList<Boolean>();
        expected.addAll(List.of(false, false, true, false, false));
        expected.addAll(List.of(false, false, true, false, false));
        expected.addAll(List.of(true, true, false, true, true));
        expected.addAll(List.of(false, false, true, false, false));
        expected.addAll(List.of(false, false, true, false, false));

        assertEquals(expected, actual);
    }

    @Test
    public void AllyBlockMovement_Test() {
        var board = Fen.build("2p/3/1p1");
        this.position.x = 2;
        this.position.y = 2;
        this.rook.setColor(ColorEnum.BLACK);
        this.rook.setPosition(this.position);

        board.setSquareValue(position, this.rook);
        this.rook.setBoard(board);
        board.updatePieceMovements();

        var actual = this.rook.exportMoves();
        var expected = new ArrayList<Boolean>();
        expected.addAll(List.of(false, false, false));
        expected.addAll(List.of(false, false, true));
        expected.addAll(List.of(false, false, false));

        assertEquals(expected, actual);
    }

    @Test
    public void EnemyMovementCaptures_Test() {
        var board = Fen.build("2p/3/1p1");
        this.position.x = 2;
        this.position.y = 2;
        this.rook.setColor(ColorEnum.WHITE);
        this.rook.setPosition(this.position);

        board.setSquareValue(position, this.rook);
        this.rook.setBoard(board);
        board.updatePieceMovements();

        var actual = this.rook.exportMoves();
        var expected = new ArrayList<Boolean>();
        expected.addAll(List.of(false, false, true));
        expected.addAll(List.of(false, false, true));
        expected.addAll(List.of(false, true, false));

        assertEquals(expected, actual);
    }
}
