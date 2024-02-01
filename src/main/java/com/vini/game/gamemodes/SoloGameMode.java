package com.vini.game.gamemodes;

import com.vini.game.board.Board;
import com.vini.game.enums.ColorEnum;
import com.vini.game.fen.Fen;
import com.vini.game.interfaces.IGameMode;
import com.vini.game.lib.Position;
import com.vini.game.piece.IPiece;

import org.java_websocket.WebSocket;

import java.util.ArrayList;
import java.util.List;

public class SoloGameMode implements IGameMode {

    private Board board;
    private WebSocket player;
    private ColorEnum currColor = ColorEnum.WHITE;

    private static final List<ColorEnum> COLOR_ORDER =
            new ArrayList<>() {
                {
                    add(ColorEnum.WHITE);
                    add(ColorEnum.BLACK);
                }
            };

    public SoloGameMode(WebSocket socket) {
        this.player = socket;
        this.board = Fen.build(Fen.defaultNotation);
    }

    @Override
    public void move(Position from, Position to) {
        IPiece piece = this.board.findPiece(from);
        if (piece == null) {
            return;
        }

        if (piece.color() != this.currColor) {
            return;
        }

        boolean hasMoved = this.board.tryMovePiece(from, to);
        if (!hasMoved) {
            return;
        }

        this.board.newRound();
        this.toggleColor();
        this.board.updatePieceMovements();
        this.player.send("RELOAD");
    }

    public void toggleColor() {
        int colorIdx = COLOR_ORDER.indexOf(this.currColor);
        int nextIdx = (colorIdx + 1) % COLOR_ORDER.size();
        this.currColor = COLOR_ORDER.get(nextIdx);
    }

    @Override
    public List<List<String>> export() {
        return this.board.tableIdentifiers();
    }
}
