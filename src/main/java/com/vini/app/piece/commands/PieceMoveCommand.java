package com.vini.app.piece.commands;

import com.vini.app.board.Board;
import com.vini.app.lib.Command;
import com.vini.app.piece.Piece;

public class PieceMoveCommand implements Command {
	public Piece source;
	public Piece target;
	public int[] sourcePosition;
	public int[] targetPosition;
	public Board board;

	public PieceMoveCommand(int[] sourcePosition, int[] targetPosition, Board board) {
		this.source = board.table().get(sourcePosition[1]).get(sourcePosition[0]);
		this.target = board.table().get(targetPosition[1]).get(targetPosition[0]);
		this.sourcePosition = sourcePosition;
		this.targetPosition = targetPosition;
		this.board = board;
	}

	@Override
	public void execute() {
		this.board.table().get(targetPosition[1]).set(targetPosition[0], this.source);
		this.board.table().get(sourcePosition[1]).set(sourcePosition[0], null);
	}
}
