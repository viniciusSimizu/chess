package com.vini.pieces;

import static org.junit.jupiter.api.Assertions.*;

import com.vini.game.board.BoardBuilder;
import com.vini.game.enums.ColorEnum;
import com.vini.game.fen.Fen;
import com.vini.game.interfaces.IPiece;
import com.vini.game.lib.Position;
import com.vini.game.piece.pieces.Pawn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PawnTest {

    private Pawn pawn;
    private final Position position = new Position(null, null);
    private final BoardBuilder builder = new BoardBuilder();

    @BeforeEach
    public void setup() {
        this.pawn = new Pawn();
        this.position.x = null;
        this.position.y = null;
        this.builder.reset();
    }

    @Test
    public void MoveOneSquare_Test() {
        var board = Fen.build("1/1/1/1");
        this.position.x = 0;
        this.position.y = 3;
        this.pawn.setColor(ColorEnum.WHITE);
        this.pawn.setIsFirstMoveFlag(false);
        this.pawn.setPosition(this.position);

        board.setSquareValue(this.position, this.pawn);
        this.pawn.setBoard(board);
        board.updatePieceMovements();

        var actual = this.pawn.exportMoves();
        var expected = new ArrayList<Boolean>();
        expected.addAll(List.of(false));
        expected.addAll(List.of(false));
        expected.addAll(List.of(true));
        expected.addAll(List.of(false));

        assertEquals(expected, actual);
    }

    @Test
    public void MoveTwoSquares_Test() {
        var board = Fen.build("1/1/1/1");
        this.position.x = 0;
        this.position.y = 3;
        this.pawn.setColor(ColorEnum.WHITE);
        this.pawn.setIsFirstMoveFlag(true);
        this.pawn.setPosition(this.position);

        board.setSquareValue(this.position, this.pawn);
        this.pawn.setBoard(board);
        board.updatePieceMovements();

        var actual = this.pawn.exportMoves();
        var expected = new ArrayList<Boolean>();
        expected.addAll(List.of(false));
        expected.addAll(List.of(true));
        expected.addAll(List.of(true));
        expected.addAll(List.of(false));

        assertEquals(expected, actual);
    }

    @Test
    public void Direction_Black_Test() {
        var board = Fen.build("1/1/1");
        this.position.x = 0;
        this.position.y = 1;
        this.pawn.setIsFirstMoveFlag(false);
        this.pawn.setColor(ColorEnum.BLACK);
        this.pawn.setPosition(this.position);

        board.setSquareValue(this.position, this.pawn);
        this.pawn.setBoard(board);
        board.updatePieceMovements();

        var actual = this.pawn.exportMoves();
        var expected = new ArrayList<Boolean>();
        expected.addAll(List.of(false));
        expected.addAll(List.of(false));
        expected.addAll(List.of(true));

        assertEquals(expected, actual);
    }

    @Test
    public void Direction_White_Test() {
        var board = Fen.build("1/1/1");
        this.position.x = 0;
        this.position.y = 1;
        this.pawn.setIsFirstMoveFlag(false);
        this.pawn.setColor(ColorEnum.WHITE);
        this.pawn.setPosition(this.position);

        board.setSquareValue(this.position, this.pawn);
        this.pawn.setBoard(board);
        board.updatePieceMovements();

        var actual = this.pawn.exportMoves();
        var expected = new ArrayList<Boolean>();
        expected.addAll(List.of(true));
        expected.addAll(List.of(false));
        expected.addAll(List.of(false));

        assertEquals(expected, actual);
    }

    @Test
    public void AllyBlockMovement_FirstMovement_Test() {
        var board = Fen.build("3/1p1/3");
        this.position.x = 1;
        this.position.y = 0;
        this.pawn.setColor(ColorEnum.BLACK);
        this.pawn.setIsFirstMoveFlag(true);
        this.pawn.setPosition(this.position);

        board.setSquareValue(this.position, this.pawn);
        this.pawn.setBoard(board);
        board.updatePieceMovements();

        var actual = this.pawn.exportMoves();
        var expected = new ArrayList<Boolean>();
        expected.addAll(List.of(false, false, false));
        expected.addAll(List.of(false, false, false));
        expected.addAll(List.of(false, false, false));

        assertEquals(expected, actual);
    }

    @Test
    public void EnemyMovementCaptures_Black_Test() {
        var board = Fen.build("3P1/1PP2/5");
        this.position.x = 2;
        this.position.y = 0;
        this.pawn.setColor(ColorEnum.BLACK);
        this.pawn.setIsFirstMoveFlag(true);
        this.pawn.setPosition(this.position);

        var squarePosition = new Position(3, 0);
        IPiece square = board.findPiece(squarePosition);
        assertInstanceOf(Pawn.class, square);
        assertEquals(ColorEnum.WHITE, square.getColor());

        var targetPawn = (Pawn) square;
        targetPawn.setIsFirstMoveFlag(false);
        targetPawn.setMovedTwoRound(board.getRound());
        board.newRound();

        board.setSquareValue(this.position, this.pawn);
        this.pawn.setBoard(board);
        board.updatePieceMovements();

        var actual = this.pawn.exportMoves();
        var expected = new ArrayList<Boolean>();
        expected.addAll(List.of(false, false, false, false, false));
        expected.addAll(List.of(false, true, false, true, false));
        expected.addAll(List.of(false, false, false, false, false));

        assertEquals(expected, actual);
    }

    @Test
    public void EnemyMovementCaptures_White_Test() {
        var board = Fen.build("5/1pp2/3p1");
        this.position.x = 2;
        this.position.y = 2;
        this.pawn.setColor(ColorEnum.WHITE);
        this.pawn.setIsFirstMoveFlag(true);
        this.pawn.setPosition(this.position);
        board.setSquareValue(this.position, this.pawn);

        var squarePosition = new Position(3, 2);
        IPiece square = board.findPiece(squarePosition);
        assertInstanceOf(Pawn.class, square);
        assertEquals(ColorEnum.BLACK, square.getColor());

        var targetPawn = (Pawn) square;
        targetPawn.setIsFirstMoveFlag(false);
        targetPawn.setMovedTwoRound(board.getRound());
        board.newRound();

        this.pawn.setBoard(board);
        board.updatePieceMovements();

        var actual = this.pawn.exportMoves();
        var expected = new ArrayList<Boolean>();
        expected.addAll(List.of(false, false, false, false, false));
        expected.addAll(List.of(false, true, false, true, false));
        expected.addAll(List.of(false, false, false, false, false));

        assertEquals(expected, actual);
    }
}
