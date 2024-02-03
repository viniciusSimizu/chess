package com.vini.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.vini.game.board.BoardBuilder;
import com.vini.game.enums.ColorEnum;
import com.vini.game.fen.Fen;
import com.vini.game.lib.Position;
import com.vini.game.piece.pieces.Pawn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PawnTest {

    private Pawn pawn;
    private Position position = new Position(null, null);
    private BoardBuilder builder = new BoardBuilder();

    @BeforeEach
    public void setup() {
        this.pawn = new Pawn();
        this.position.x = null;
        this.position.y = null;
        this.builder.reset();
    }

    @Test
    public void CanMoveOneSquare_Test() {
        var board = Fen.build("1/1/1/1");
        this.position.x = 0;
        this.position.y = 3;
        this.pawn.setColor(ColorEnum.WHITE);
        this.pawn.setIsFirstMoveFlag(false);
        this.pawn.setPosition(this.position);

        board.setSquarePiece(position, this.pawn);
        this.pawn.setBoard(board);
        board.updatePieceMovements();

        var actual = this.pawn.getMoves();
        var expected = new ArrayList<Boolean>();
        expected.addAll(List.of(false));
        expected.addAll(List.of(false));
        expected.addAll(List.of(true));
        expected.addAll(List.of(false));

        assertEquals(actual.size(), expected.size());

        for (int i = 0; i < actual.size(); i++) {
            assertEquals(actual.get(i), expected.get(i));
        }
    }

    @Test
    public void CanMoveTwoSquare_Test() {
        var board = Fen.build("1/1/1/1");
        this.position.x = 0;
        this.position.y = 3;
        this.pawn.setColor(ColorEnum.WHITE);
        this.pawn.setPosition(this.position);

        board.setSquarePiece(position, this.pawn);
        this.pawn.setBoard(board);
        board.updatePieceMovements();

        var actual = this.pawn.getMoves();
        var expected = new ArrayList<Boolean>();
        expected.addAll(List.of(false));
        expected.addAll(List.of(true));
        expected.addAll(List.of(true));
        expected.addAll(List.of(false));

        assertEquals(actual.size(), expected.size());

        for (int i = 0; i < actual.size(); i++) {
            assertEquals(actual.get(i), expected.get(i));
        }
    }

    @Test
    public void BlackPawnDirection_Test() {
        var board = Fen.build("1/1/1");
        this.position.x = 0;
        this.position.y = 1;
        this.pawn.setIsFirstMoveFlag(false);
        this.pawn.setColor(ColorEnum.BLACK);
        this.pawn.setPosition(this.position);

        board.setSquarePiece(position, this.pawn);
        this.pawn.setBoard(board);
        board.updatePieceMovements();

        var actual = this.pawn.getMoves();
        var expected = new ArrayList<Boolean>();
        expected.addAll(List.of(false));
        expected.addAll(List.of(false));
        expected.addAll(List.of(true));

        assertEquals(actual.size(), expected.size());

        for (int i = 0; i < actual.size(); i++) {
            assertEquals(actual.get(i), expected.get(i));
        }
    }

    @Test
    public void WhitePawnDirection_Test() {
        var board = Fen.build("1/1/1");
        this.position.x = 0;
        this.position.y = 1;
        this.pawn.setIsFirstMoveFlag(false);
        this.pawn.setColor(ColorEnum.WHITE);
        this.pawn.setPosition(this.position);

        board.setSquarePiece(position, this.pawn);
        this.pawn.setBoard(board);
        board.updatePieceMovements();

        var actual = this.pawn.getMoves();
        var expected = new ArrayList<Boolean>();
        expected.addAll(List.of(true));
        expected.addAll(List.of(false));
        expected.addAll(List.of(false));

        assertEquals(actual.size(), expected.size());

        for (int i = 0; i < actual.size(); i++) {
            assertEquals(actual.get(i), expected.get(i));
        }
    }
}
