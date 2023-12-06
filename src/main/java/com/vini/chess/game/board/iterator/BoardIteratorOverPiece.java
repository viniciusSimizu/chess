package com.vini.chess.game.board.iterator;

import java.util.Iterator;

import com.vini.chess.game.board.Board;
import com.vini.chess.game.piece.IPiece;

public class BoardIteratorOverPiece implements Iterator<IPiece> {
	private Board board;
	private int rowIdx = 0;
	private int colIdx = 0;

	public BoardIteratorOverPiece(Board board) {
		this.board = board;
	}

	@Override
	public boolean hasNext() {
		int rowIdx = this.rowIdx;
		int colIdx = this.colIdx;

		while (true) {
			if (rowIdx >= this.board.table().size()) {
				return false;
			}

			boolean colInsideRow = colIdx < this.board.table().get(rowIdx).size();

			if (!colInsideRow) {
				rowIdx++;
				colIdx = 0;
				continue;
			}

			boolean isPiece = this.board.table().get(rowIdx).get(colIdx) instanceof IPiece;

			if (isPiece) {
				return true;
			}

			colIdx++;
		}
	}

	@Override
	public IPiece next() {
		if (!this.hasNext()) {
			return null;
		}

		while (true) {
			boolean colInsideRow = this.colIdx < this.board.table().get(this.rowIdx).size();

			if (!colInsideRow) {
				this.rowIdx++;
				this.colIdx = 0;
				continue;
			}

			IPiece square = this.board.table().get(this.rowIdx).get(this.colIdx);
			this.colIdx++;

			if (square instanceof IPiece) {
				return square;
			}
		}
	}
}
