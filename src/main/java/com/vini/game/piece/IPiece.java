package com.vini.game.piece;

import java.util.List;

import com.vini.game.Game;
import com.vini.game.enums.ColorEnum;
import com.vini.game.enums.PieceEnum;
import com.vini.game.piece.commands.PieceMoveCommand;

public interface IPiece {
	public void resetMoves();
	public IPiece structureMoves();
	public IPiece updateMoves();

	public IPiece setGame(Game game);

	public PieceMoveCommand move(int[] position);
	public boolean canMove(int[] position);

	public ColorEnum color();
	public IPiece setColor(ColorEnum color);
	
	public int[] position();
	public IPiece setPosition(int[] position);

	public List<List<Boolean>> moves();
	public IPiece setMoves(List<List<Boolean>> moves);

	public PieceEnum fen();
}

