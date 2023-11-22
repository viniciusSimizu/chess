package com.vini.app.board.iterator;

import com.vini.app.board.Board;
import com.vini.app.piece.Piece;

public class BoardIteratorOverPiece implements BoardIterator {
	Board board;
	int rowIdx = 0;
	int colIdx = 0;

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

			boolean isPiece = this.board.table().get(rowIdx).get(colIdx) instanceof Piece;

			if (isPiece) {
				return true;
			}

			colIdx++;
		}
	}

	@Override
	public Piece next() {
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

			Piece square = this.board.table().get(this.rowIdx).get(this.colIdx);

			if (square instanceof Piece) {
				return square;
			}

			this.colIdx++;
		}
	}
}
