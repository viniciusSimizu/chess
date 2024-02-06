package com.vini.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.vini.game.board.BoardBuilder;
import com.vini.game.enums.ColorEnum;
import com.vini.game.fen.Fen;
import com.vini.game.lib.Position;
import com.vini.game.piece.pieces.Bishop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BishopTest {

    private Bishop bishop;
    private final Position position = new Position(null, null);
    private final BoardBuilder builder = new BoardBuilder();

    @BeforeEach
    public void setup() {
        this.bishop = new Bishop();
        this.position.x = null;
        this.position.y = null;
        this.builder.reset();
    }

    @Test
    public void CanMoveOnEverySquare_Test() {
        var board = Fen.build("5/5/5/5/5");
        this.position.x = 2;
        this.position.y = 2;
        this.bishop.setPosition(this.position);

        board.setSquarePiece(position, this.bishop);
        this.bishop.setBoard(board);
        board.updatePieceMovements();

        var actual = this.bishop.getMoves();
        var expected = new ArrayList<Boolean>();
        expected.addAll(List.of(true, false, false, false, true));
        expected.addAll(List.of(false, true, false, true, false));
        expected.addAll(List.of(false, false, false, false, false));
        expected.addAll(List.of(false, true, false, true, false));
        expected.addAll(List.of(true, false, false, false, true));

        assertEquals(expected, actual);
    }

    @Test
    public void AllyBlockMovement_Test() {
        var board = Fen.build("p4/3p1/5");
        this.position.x = 2;
        this.position.y = 2;
        this.bishop.setColor(ColorEnum.BLACK);
        this.bishop.setPosition(this.position);

        board.setSquarePiece(position, this.bishop);
        this.bishop.setBoard(board);
        board.updatePieceMovements();

        var actual = this.bishop.getMoves();
        var expected = new ArrayList<Boolean>();
        expected.addAll(List.of(false, false, false, false, false));
        expected.addAll(List.of(false, true, false, false, false));
        expected.addAll(List.of(false, false, false, false, false));

        assertEquals(expected, actual);
    }

    @Test
    public void EnemyMovementCaptures_Test() {
        var board = Fen.build("p4/3p1/5");
        this.position.x = 2;
        this.position.y = 2;
        this.bishop.setColor(ColorEnum.WHITE);
        this.bishop.setPosition(this.position);

        board.setSquarePiece(position, this.bishop);
        this.bishop.setBoard(board);
        board.updatePieceMovements();

        var actual = this.bishop.getMoves();
        var expected = new ArrayList<Boolean>();
        expected.addAll(List.of(true, false, false, false, false));
        expected.addAll(List.of(false, true, false, true, false));
        expected.addAll(List.of(false, false, false, false, false));

        assertEquals(expected, actual);
    }
}
