package com.vini.game;

import com.vini.game.enums.ColorEnum;
import com.vini.game.enums.GameStateEnum;
import com.vini.game.interfaces.IBoard;
import com.vini.game.interfaces.IGame;
import com.vini.game.structs.Position;
import com.vini.socket.structs.TableRepresentation;

import java.util.List;

public class SoloGame implements IGame {

    private static final List<ColorEnum> COLOR_ORDER = List.of(ColorEnum.WHITE, ColorEnum.BLACK);

    private IBoard board;
    private ColorEnum currColor = ColorEnum.WHITE;
    private GameStateEnum state = GameStateEnum.PLAYING;
    private int round = 0;

    public SoloGame(IBoard board) {
        this.board = board;
        board.setGame(this);
    }

    @Override
    public boolean tryMove(Position from, Position to) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nextTurn() {
        this.round++;
        this.toggleColor();
    }

    @Override
    public ColorEnum getCurrColor() {
        return this.currColor;
    }

    @Override
    public GameStateEnum getState() {
        return this.state;
    }

    @Override
    public TableRepresentation export() {
        return new TableRepresentation(this.board);
    }

    @Override
    public int getRound() {
        return this.round;
    }

    private void toggleColor() {
        int colorIdx = COLOR_ORDER.indexOf(this.currColor);
        int nextIdx = (colorIdx + 1) % COLOR_ORDER.size();
        this.currColor = COLOR_ORDER.get(nextIdx);
    }
}
