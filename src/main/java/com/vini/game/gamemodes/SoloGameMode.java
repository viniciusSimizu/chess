package com.vini.game.gamemodes;

import com.vini.game.board.Board;
import com.vini.game.enums.ColorEnum;
import com.vini.game.fen.Fen;
import com.vini.game.interfaces.IGameMode;
import com.vini.game.lib.PlayerData;
import com.vini.game.lib.Position;
import com.vini.game.piece.IPiece;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SoloGameMode implements IGameMode {

    private UUID id;
    private Board board;
    private List<PlayerData> players;
    private ColorEnum currColor;

    private static final List<ColorEnum> colorOrder =
            new ArrayList<>() {
                {
                    add(ColorEnum.WHITE);
                    add(ColorEnum.BLACK);
                }
            };

    public SoloGameMode(List<PlayerData> players) {
        this.id = UUID.randomUUID();
        this.board = Fen.build(Fen.defaultNotation);
        this.players = players;
        this.currColor = ColorEnum.WHITE;
    }

    @Override
    public boolean hasPermissionToMove(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hasPermissionToMove'");
    }

    @Override
    public boolean tryMove(UUID userId, Position from, Position to) {
        if (!this.hasPermissionToMove(userId)) {
            return false;
        }

        IPiece piece = this.board.findPiece(from);
        if (piece == null) {
            return false;
        }

        boolean hasMoved = this.board.trySetPiece(to, piece);
        if (!hasMoved) {
            return false;
        }
        this.board.trySetPiece(from, null);

        this.board.updateState();
        switch (this.board.getState()) {
            case WONNED:
                this.won(userId);
                break;

            case DRAWNED:
                this.draw();
                break;

            default:
                this.toggleColor();
                break;
        }

        return true;
    }

    @Override
    public void toggleColor() {
        int colorIdx = colorOrder.indexOf(this.currColor);
        int nextIdx = (colorIdx + 1) % colorOrder.size();
        this.currColor = colorOrder.get(nextIdx);
    }

    @Override
    public void won(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'won'");
    }

    @Override
    public void draw() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }
}
