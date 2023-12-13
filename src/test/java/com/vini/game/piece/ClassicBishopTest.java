package com.vini.game.piece;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.vini.game.board.Board;
import com.vini.game.board.BoardBuilder;
import com.vini.game.enums.ColorEnum;
import com.vini.game.enums.PieceEnum;
import com.vini.game.piece.classic.ClassicBishop;

public class ClassicBishopTest {
	private ClassicBishop piece;
	private BoardBuilder builder;

	@BeforeEach
	public void setup() {
		this.builder = new BoardBuilder();
		this.piece = new ClassicBishop(this.builder.result());
		this.piece.setColor(ColorEnum.BLACK);
	}

	@Test
	public void updateMoves() {
		Board board = this.builder
			.buildEmptySquare().buildEmptySquare().buildEmptySquare().buildRow()
			.buildEmptySquare().buildEmptySquare().buildEmptySquare().buildRow()
			.buildEmptySquare().buildEmptySquare().buildEmptySquare().buildRow()
			.result();

		int[] position = {1, 1};
		this.piece.setPosition(position);
		this.piece.structureMoves();

		board.table().get(position[1]).set(position[0], this.piece);

		this.piece.updateMoves();
		List<List<Boolean>> pieceMoves = this.piece.moves();

		List<List<Boolean>> expected = new ArrayList<List<Boolean>>() {{
			add(Arrays.asList(true, false, true));
			add(Arrays.asList(false, false, false));
			add(Arrays.asList(true, false, true));
		}};

		assertTrue(pieceMoves.equals(expected));
	}

	@Test
	public void updateMovesWithAlly() {
		Board board = this.builder
			.buildEmptySquare().buildPiece(PieceEnum.PAWN, ColorEnum.BLACK).buildRow()
			.buildEmptySquare().buildEmptySquare().buildRow()
			.result();

		int[] position = {0, 1};
		this.piece.setPosition(position);
		this.piece.structureMoves();

		board.table().get(position[1]).set(position[0], this.piece);

		this.piece.updateMoves();
		List<List<Boolean>> pieceMoves = this.piece.moves();

		List<List<Boolean>> expected = new ArrayList<List<Boolean>>() {{
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
			.result();

		int[] position = {0, 1};
		this.piece.setPosition(position);
		this.piece.structureMoves();

		board.table().get(position[1]).set(position[0], this.piece);

		this.piece.updateMoves();
		List<List<Boolean>> pieceMoves = this.piece.moves();

		List<List<Boolean>> expected = new ArrayList<List<Boolean>>() {{
			add(Arrays.asList(false, true));
			add(Arrays.asList(false, false));
		}};

		assertTrue(pieceMoves.equals(expected));
	}
}
