package com.vini.app.boardManager;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.vini.app.pieces.IPiece;

public class BoardManagerObserver {
	private List<IPiece> subscribed = new ArrayList<>();

	public BoardManagerObserver structMoves(List<List<IPiece>> board) {
		for (IPiece piece : this.subscribed) {
			piece.structMoves(board);
		}
		return this;
	}

	public BoardManagerObserver clearMoves() {
		for (IPiece piece : this.subscribed) {
			piece.setMovesUpdated(false);
		}
		return this;
	}

	public BoardManagerObserver notify(Function<IPiece, ?> function) {
		for (IPiece piece : this.subscribed) {
			function.apply(piece);
		}
		return this;
	}

	public BoardManagerObserver subscribe(IPiece piece) {
		this.subscribed.add(piece);
		return this;
	}

	public BoardManagerObserver unsubscribe(IPiece piece) {
		for (int i = 0; i < this.subscribed.size(); i++) {
			if (this.subscribed.get(i) == piece) {
				this.subscribed.remove(i);
				break;
			}
		}
		return this;
	}

	public BoardManagerObserver clear() {
		this.subscribed.clear();
		return this;
	}
}
