package com.vini.game.gamemodes;

import com.vini.game.board.Board;
import com.vini.game.enums.ColorEnum;
import com.vini.game.fen.Fen;
import com.vini.game.interfaces.IGameMode;
import com.vini.game.interfaces.IPiece;
import com.vini.game.lib.Position;
import com.vini.socket.lib.TableRepresentation;

import java.util.List;

public class SoloGameMode implements IGameMode {

    private static final List<ColorEnum> COLOR_ORDER = List.of(ColorEnum.WHITE, ColorEnum.BLACK);

    private Board board = Fen.build(Fen.defaultNotation);
    private ColorEnum currColor = ColorEnum.WHITE;

    @Override
    public void move(Position from, Position to) {
        IPiece piece = this.board.findPiece(from);
        if (piece == null) {
            return;
        }

        if (piece.getColor() != this.currColor) {
            return;
        }

        boolean hasMoved = this.board.tryMovePiece(from, to);
        if (!hasMoved) {
            return;
        }

        this.board.newRound();
        this.toggleColor();
        this.board.updatePieceMovements();
    }

    public void toggleColor() {
        int colorIdx = COLOR_ORDER.indexOf(this.currColor);
        int nextIdx = (colorIdx + 1) % COLOR_ORDER.size();
        this.currColor = COLOR_ORDER.get(nextIdx);
    }

    @Override
    public TableRepresentation export() {
			return new TableRepresentation(this.board);
    }
}
