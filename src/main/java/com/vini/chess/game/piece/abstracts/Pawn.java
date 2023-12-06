package com.vini.chess.game.piece.abstracts;

import com.vini.chess.game.board.Board;
import com.vini.chess.game.enums.ColorEnum;
import com.vini.chess.game.enums.PieceEnum;
import com.vini.chess.game.lib.Command;
import com.vini.chess.game.piece.IPiece;
import com.vini.chess.game.piece.Piece;
import com.vini.chess.game.piece.commands.PieceMoveCommand;

public abstract class Pawn extends Piece {

	class UndoHasMovedTwoCommand implements Command {
		Pawn pawn;

		public UndoHasMovedTwoCommand(Pawn pawn) {
			this.pawn = pawn;
		}

		@Override
		public void execute() {
			this.pawn.hasMovedTwo = false;
		}
	}

	protected Pawn(Board board) {
		super(board);
	}

	public boolean hasMovedTwo = false;

	protected boolean isFirstMove = true;
	protected int directionWeight = 1;

	@Override
	public PieceMoveCommand move(int[] position) {
		int heightDiff = Math.abs(position[1] - this.position()[1]);
		if (this.isFirstMove && heightDiff == 2) {
			this.hasMovedTwo = true;

			if (this.game != null) {
				this.game.scheduleToExecute(new UndoHasMovedTwoCommand(this), 1);
			}
		}

		PieceMoveCommand command = super.move(position);
		this.isFirstMove = false;

		return command;
	}

	@Override
	public IPiece setColor(ColorEnum color) {
		super.setColor(color);

		if (color == ColorEnum.WHITE) {
			this.directionWeight = -1;
		}

		return this;
	}

	@Override
	public PieceEnum fen() {
		return PieceEnum.PAWN;
	}
}

