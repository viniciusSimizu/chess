package com.vini.gamemodes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.vini.game.enums.ColorEnum;
import com.vini.game.gamemodes.SoloGameMode;
import com.vini.game.lib.Position;
import com.vini.game.piece.pieces.Knight;
import com.vini.game.piece.pieces.Pawn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SoloGameModeTest {

    private SoloGameMode game;

    @BeforeEach
    public void setup() {
        this.game = new SoloGameMode();
    }

    @Test
    public void ToggleColorCycle_Test() {
        ColorEnum currColor = this.game.getCurrColor();
        assertEquals(ColorEnum.WHITE, currColor);

        this.game.toggleColor();
        currColor = this.game.getCurrColor();
        assertEquals(ColorEnum.BLACK, currColor);

        this.game.toggleColor();
        currColor = this.game.getCurrColor();
        assertEquals(ColorEnum.WHITE, currColor);
    }

    @Test
    public void MoveTurn_Test() {
        var from = new Position(1, 0);
        var to = new Position(2, 2);

        var square = this.game.getBoard().findPiece(from);
        assertEquals(true, square instanceof Knight);
        assertEquals(ColorEnum.BLACK, square.getColor());

        square = this.game.getBoard().findPiece(to);
        assertEquals(null, square);

        boolean hasMoved = this.game.tryMove(from, to);
        assertEquals(false, hasMoved);
        assertEquals(ColorEnum.WHITE, this.game.getCurrColor());

        from.x = 0;
        from.y = 6;
        to.x = 0;
        to.y = 4;

        square = this.game.getBoard().findPiece(from);
        assertEquals(true, square instanceof Pawn);
        assertEquals(ColorEnum.WHITE, square.getColor());

        square = this.game.getBoard().findPiece(to);
        assertEquals(null, square);

        hasMoved = this.game.tryMove(from, to);
        assertEquals(true, hasMoved);
        assertEquals(ColorEnum.BLACK, this.game.getCurrColor());

        square = this.game.getBoard().findPiece(to);
        assertEquals(true, square instanceof Pawn);
        assertEquals(ColorEnum.WHITE, square.getColor());
        assertEquals(to.x, square.getPosition().x);
        assertEquals(to.y, square.getPosition().y);

        square = this.game.getBoard().findPiece(from);
        assertEquals(null, square);
    }
}
