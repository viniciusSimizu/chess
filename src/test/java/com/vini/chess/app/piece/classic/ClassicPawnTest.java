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

public class ClassicPawnTest {
	private IPieceFactory factory = ClassicPieceFactory.getInstance();
	private IPiece piece;
	private BoardBuilder builder;

	@BeforeEach
	public void setup() {
		this.builder = new BoardBuilder(this.factory);
		this.piece = new PieceDecorator(new ClassicPawn(this.builder.result()));
		this.piece.setColor(ColorEnum.BLACK);
	}

	@Test
	public void firstMoves() {
		Board board = this.builder
			.buildEmptySquare().buildEmptySquare().buildEmptySquare().buildRow()
			.buildEmptySquare().buildEmptySquare().buildEmptySquare().buildRow()
			.buildEmptySquare().buildEmptySquare().buildEmptySquare().buildRow()
			.result();

		int[] position = {1, 0};
		this.piece.setPosition(position);
		this.piece.structureMoves();

		board.table().get(position[1]).set(position[0], this.piece);

		this.piece.updateMoves();
		List<List<Boolean>> pieceMoves = this.piece.moves();

		List<List<Boolean>> expected = new ArrayList<List<Boolean>>() {{
			add(Arrays.asList(false, false, false));
			add(Arrays.asList(false, true, false));
			add(Arrays.asList(false, true, false));
		}};

		assertTrue(pieceMoves.equals(expected));
	}

	@Test
	public void nonFirstMoves() {
		Board board = this.builder
			.buildEmptySquare().buildRow()
			.buildEmptySquare().buildRow()
			.buildEmptySquare().buildRow()
			.buildEmptySquare().buildRow()
			.result();

		int[] position = {0, 0};
		this.piece.setPosition(position);
		this.piece.structureMoves();

		board.table().get(position[1]).set(position[0], this.piece);

		this.piece.updateMoves();
		List<List<Boolean>> pieceMoves = this.piece.moves();

		List<List<Boolean>> expected = new ArrayList<List<Boolean>>() {{
			add(Arrays.asList(false));
			add(Arrays.asList(true));
			add(Arrays.asList(true));
			add(Arrays.asList(false));
		}};

		assertTrue(pieceMoves.equals(expected));

		int[] nextPosition = {0, 1};
		this.piece.move(nextPosition);

		this.piece.updateMoves();
		pieceMoves = this.piece.moves();

		expected = new ArrayList<List<Boolean>>() {{
			add(Arrays.asList(false));
			add(Arrays.asList(false));
			add(Arrays.asList(true));
			add(Arrays.asList(false));
		}};

		assertTrue(pieceMoves.equals(expected));
	}

	@Test
	public void piercingAttack() {
		Board board = this.builder
			.buildEmptySquare().buildEmptySquare().buildEmptySquare().buildRow()
			.buildPiece(PieceEnum.PAWN, ColorEnum.BLACK).buildEmptySquare().buildPiece(PieceEnum.PAWN, ColorEnum.WHITE).buildRow()
			.buildEmptySquare().buildEmptySquare().buildEmptySquare().buildRow()
			.result();

		int[] position = {1, 0};
		this.piece.setPosition(position);
		this.piece.structureMoves();

		board.table().get(position[1]).set(position[0], this.piece);

		this.piece.updateMoves();
		List<List<Boolean>> pieceMoves = this.piece.moves();

		List<List<Boolean>> expected = new ArrayList<List<Boolean>>() {{
			add(Arrays.asList(false, false, false));
			add(Arrays.asList(false, true, true));
			add(Arrays.asList(false, true, false));
		}};

		assertTrue(pieceMoves.equals(expected));
	}

	@Test
	public void pieceBlock() {
		Board board = this.builder
			.buildEmptySquare().buildRow()
			.buildPiece(PieceEnum.PAWN, ColorEnum.WHITE).buildRow()
			.buildEmptySquare().buildRow()
			.result();

		int[] position = {0, 0};
		this.piece.setPosition(position);
		this.piece.structureMoves();

		board.table().get(position[1]).set(position[0], this.piece);

		this.piece.updateMoves();
		List<List<Boolean>> pieceMoves = this.piece.moves();

		List<List<Boolean>> expected = new ArrayList<List<Boolean>>() {{
			add(Arrays.asList(false));
			add(Arrays.asList(false));
			add(Arrays.asList(false));
		}};

		assertTrue(pieceMoves.equals(expected));
	}

	@Test
	public void whiteMoves() {
		Board board = this.builder
			.buildEmptySquare().buildRow()
			.buildEmptySquare().buildRow()
			.buildEmptySquare().buildRow()
			.buildEmptySquare().buildRow()
			.buildEmptySquare().buildRow()
			.result();

		int[] position = {0, 2};
		this.piece.setPosition(position);
		this.piece.structureMoves();

		board.table().get(position[1]).set(position[0], this.piece);

		this.piece.setColor(ColorEnum.WHITE);
		this.piece.updateMoves();
		List<List<Boolean>> pieceMoves = this.piece.moves();

		List<List<Boolean>> expected = new ArrayList<List<Boolean>>() {{
			add(Arrays.asList(true));
			add(Arrays.asList(true));
			add(Arrays.asList(false));
			add(Arrays.asList(false));
			add(Arrays.asList(false));
		}};

		assertTrue(pieceMoves.equals(expected));
	}
}
