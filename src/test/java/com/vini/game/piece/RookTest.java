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
import com.vini.game.piece.pieces.Rook;

public class RookTest {
	private Rook piece;
	private BoardBuilder builder;

	@BeforeEach
	public void setup() {
		this.builder = new BoardBuilder();
		this.piece = new Rook(this.builder.result());
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
			add(Arrays.asList(false, false, true, false, false));
			add(Arrays.asList(false, false, true, false, false));
			add(Arrays.asList(true, true, false, true, true));
			add(Arrays.asList(false, false, true, false, false));
			add(Arrays.asList(false, false, true, false, false));
		}};

		assertTrue(pieceMoves.equals(expected));
	}

	@Test
	public void updateMovesWithAlly() {
		Board board = this.builder
			.buildEmptySquare().buildRow()
			.buildPiece(PieceEnum.PAWN, ColorEnum.BLACK).buildRow()
			.buildEmptySquare().buildRow()
			.result();

		int[] position = {0, 2};
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
	public void updateMovesWithEnemy() {
		Board board = this.builder
			.buildEmptySquare().buildRow()
			.buildPiece(PieceEnum.PAWN, ColorEnum.WHITE).buildRow()
			.buildEmptySquare().buildRow()
			.result();

		int[] position = {0, 2};
		this.piece.setPosition(position);
		this.piece.structureMoves();

		board.table().get(position[1]).set(position[0], this.piece);

		this.piece.updateMoves();
		List<List<Boolean>> pieceMoves = this.piece.moves();

		List<List<Boolean>> expected = new ArrayList<List<Boolean>>() {{
			add(Arrays.asList(false));
			add(Arrays.asList(true));
			add(Arrays.asList(false));
		}};

		assertTrue(pieceMoves.equals(expected));
	}
}


