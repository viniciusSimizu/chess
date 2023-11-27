package com.vini.chess.app.piece.classic;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.vini.chess.app.board.Board;
import com.vini.chess.app.board.builder.BoardBuilder;
import com.vini.chess.app.piece.IPiece;
import com.vini.chess.app.piece.IPieceFactory;
import com.vini.chess.app.piece.PieceDecorator;
import com.vini.chess.app.types.ColorEnum;
import com.vini.chess.app.types.PieceEnum;

public class ClassicKnightTest {
	private IPieceFactory factory = ClassicPieceFactory.getInstance();
	private IPiece piece;
	private BoardBuilder builder;

	@BeforeEach
	public void setup() {
		this.builder = new BoardBuilder(this.factory);
		this.piece = new PieceDecorator(new ClassicKnight(this.builder.result()));
		this.piece.setColor(ColorEnum.BLACK);
	}

	@Test
	public void updateMoves() {
		Board board = this.builder
			.buildEmptySquare().buildEmptySquare().buildEmptySquare().buildEmptySquare().buildEmptySquare().buildRow()
			.buildEmptySquare().buildEmptySquare().buildEmptySquare().buildEmptySquare().buildEmptySquare().buildRow()
			.buildEmptySquare().buildEmptySquare().buildEmptySquare().buildEmptySquare().buildEmptySquare().buildRow()
			.buildEmptySquare().buildEmptySquare().buildEmptySquare().buildEmptySquare().buildEmptySquare().buildRow()
			.buildEmptySquare().buildEmptySquare().buildEmptySquare().buildEmptySquare().buildEmptySquare().buildRow()
			.result();

		int[] position = {2, 2};
		this.piece.setPosition(position);
		this.piece.structureMoves();

		board.table().get(position[1]).set(position[0], this.piece);

		this.piece.updateMoves();
		List<List<Boolean>> pieceMoves = this.piece.moves();

		List<List<Boolean>> expected = new ArrayList<List<Boolean>>() {{
			add(Arrays.asList(false, true, false, true, false));
			add(Arrays.asList(true, false, false, false, true));
			add(Arrays.asList(false, false, false, false, false));
			add(Arrays.asList(true, false, false, false, true));
			add(Arrays.asList(false, true, false, true, false));
		}};

		assertTrue(pieceMoves.equals(expected));
	}

	@Test
	public void updateMovesWithAlly() {
		Board board = this.builder
			.buildEmptySquare().buildPiece(PieceEnum.PAWN, ColorEnum.BLACK).buildRow()
			.buildEmptySquare().buildEmptySquare().buildRow()
			.buildEmptySquare().buildEmptySquare().buildRow()
			.result();

		int[] position = {0, 2};
		this.piece.setPosition(position);
		this.piece.structureMoves();

		board.table().get(position[1]).set(position[0], this.piece);

		this.piece.updateMoves();
		List<List<Boolean>> pieceMoves = this.piece.moves();

		List<List<Boolean>> expected = new ArrayList<List<Boolean>>() {{
			add(Arrays.asList(false, false));
			add(Arrays.asList(false, false));
			add(Arrays.asList(false, false));
		}};

		assertTrue(pieceMoves.equals(expected));
	}

	@Test
	public void updateMovesWithEnemy() {
		Board board = this.builder
			.buildEmptySquare().buildPiece(PieceEnum.PAWN, ColorEnum.WHITE).buildRow()
			.buildEmptySquare().buildEmptySquare().buildRow()
			.buildEmptySquare().buildEmptySquare().buildRow()
			.result();

		int[] position = {0, 2};
		this.piece.setPosition(position);
		this.piece.structureMoves();

		board.table().get(position[1]).set(position[0], this.piece);

		this.piece.updateMoves();
		List<List<Boolean>> pieceMoves = this.piece.moves();

		List<List<Boolean>> expected = new ArrayList<List<Boolean>>() {{
			add(Arrays.asList(false, true));
			add(Arrays.asList(false, false));
			add(Arrays.asList(false, false));
		}};

		assertTrue(pieceMoves.equals(expected));
	}
}
