package com.vini.chess.app.piece;

import java.util.List;

import com.vini.chess.app.piece.commands.PieceMoveCommand;
import com.vini.chess.app.types.ColorEnum;
import com.vini.chess.app.types.PieceEnum;

public interface IPiece {
	public IPiece structureMoves();
	public IPiece resetMoves();
	public IPiece updateMoves();

	public PieceMoveCommand move(int[] position);
	public boolean canMove(int[] position);

	public ColorEnum color();
	public IPiece setColor(ColorEnum color);
	
	public int[] position();
	public IPiece setPosition(int[] position);

	public List<List<Boolean>> moves();
	public IPiece setMoves(List<List<Boolean>> moves);

	public boolean hasMovesUpdated();
	public IPiece setHasMovesUpdated(boolean state);

	public PieceEnum fen();
}

