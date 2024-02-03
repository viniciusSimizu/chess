package com.vini.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.vini.game.board.BoardBuilder;
import com.vini.game.fen.Fen;
import com.vini.game.lib.Position;
import com.vini.game.piece.pieces.Queen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class QueenTest {

    private Queen queen;
    private Position position = new Position(null, null);
    private BoardBuilder builder = new BoardBuilder();

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

        assertEquals(actual.size(), expected.size());

        for (int i = 0; i < actual.size(); i++) {
            assertEquals(actual.get(i), expected.get(i));
        }
    }
}
