package com.vini.app.board_manager;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import com.vini.app.pieces.Piece;
import com.vini.app.translate.ColorTranslate;
import com.vini.app.translate.PieceDisplayTranslate;
import com.vini.app.types.ColorEnum;

public class BoardManager {
	List<List<Piece>> board;
	ColorEnum turn;

	private final Scanner sc = new Scanner(System.in);
	private final PrintStream out = new PrintStream(System.out, true, "UTF8");

	BoardManager(List<List<Piece>> board, ColorEnum turn) throws UnsupportedEncodingException {
		this.board = board;
		this.turn = turn;
	}

	public void play() throws UnsupportedEncodingException {
		String command;
		String[] splitted = new String[2];
		while (true) {
			System.out.println(String.format("%s turn", ColorTranslate.book.get(this.turn)));

			this.printBoard();

			System.out.print("Move piece: ");
			command = this.sc.next();
			splitted = command.split(" ");

			int[] position = {
				Integer.parseInt(splitted[0]),
				Integer.parseInt(splitted[1])
			};

			Piece piece = this.board.get(position[1]).get(position[0]);

			if (piece.color != this.turn) {
				continue;
			}

			this.printBoard(piece);

			System.out.print("To: ");
			command = this.sc.next();
			splitted = command.split(" ");

			int[] targetPosition = {
				Integer.parseInt(splitted[0]),
				Integer.parseInt(splitted[1])
			};

			if (piece.canMove(this.board, targetPosition)) {
				piece.move(this.board, targetPosition);
				this.toggleTurn();
			} else {
				System.out.println("Invalid move, try again");
			}
		}
	}

	private void toggleTurn() {
		if (this.turn == ColorEnum.BLACK) {
			this.turn = ColorEnum.WHITE;
		} else {
			this.turn = ColorEnum.BLACK;
		}
	}

	private void printBoard(Piece piece) throws UnsupportedEncodingException {
		this.printBoard();

		for (List<Boolean> row : piece.moves) {
			System.out.println();
			for (Boolean square : row) {
				if (square) {
					this.out.print("\u2588");
				} else {
					System.out.print(' ');
				}
			}
		}
	}

	private void printBoard() throws UnsupportedEncodingException {
		for (List<Piece> row : this.board) {
			System.out.println();
			for (Piece square : row) {
				if (Objects.isNull(square)) {
					System.out.print(' ');
					continue;
				}

				this.out.print(PieceDisplayTranslate.book.get(square.fen));
			}
		}
	}
}
