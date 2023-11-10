package com.vini.app.boardManager;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import com.vini.app.pieces.IPiece;
import com.vini.app.translate.ColorTranslate;
import com.vini.app.translate.PieceDisplayTranslate;
import com.vini.app.types.ColorEnum;

public class BoardManager {
	private List<List<IPiece>> board;
	private ColorEnum turn;

	private Scanner sc = new Scanner(System.in);
	
	public BoardManager setBoard(List<List<IPiece>> board) {
		this.board = board;
		return this;
	}
	public BoardManager setTurn(ColorEnum turn) {
		this.turn = turn;
		return this;
	}

	public void play() {
		String command;
		String[] splitted = new String[2];
		while (true) {
			System.out.println(String.format("%s turn", ColorTranslate.book.get(this.turn)));

			this.printBoard();

			System.out.print("Move piece: ");
			command = this.sc.nextLine();
			splitted = command.split(" ");

			int[] position = {
				Integer.parseInt(splitted[0]),
				Integer.parseInt(splitted[1])
			};

			IPiece piece = this.board.get(position[1]).get(position[0]);

			if (piece.color() != this.turn) {
				continue;
			}

			this.printBoard(piece);

			System.out.print("To: ");
			command = this.sc.nextLine();
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

	private void printBoard(IPiece piece) {
		int hi = 0;
		for (int i = 0; i < piece.moves().size(); i++) {
			System.out.print(i);
			if (this.board.get(i).size() > hi) {
				hi = this.board.get(i).size();
			}
			for (int j = 0; i < piece.moves().get(i).size(); j++) {
				Boolean square = piece.moves().get(i).get(j);
				if (square) {
					System.out.print(Character.toChars(0x2588));
				} else {
					System.out.print(' ');
				}
			}
			System.out.println();
		}
		System.out.print(" ");
		for (int i = 0; i < hi; i++) {
			System.out.print(i);
		}
		System.out.println();

		this.printBoard();
	}

	private void printBoard() {
		int hi = 0;
		for (int i = 0; i < this.board.size(); i++) {
			System.out.print(i);
			if (this.board.get(i).size() > hi) {
				hi = this.board.get(i).size();
			}
			for (int j = 0; j < this.board.get(i).size(); j++) {
				IPiece square = this.board.get(i).get(j);

				if (Objects.isNull(square)) {
					System.out.print(' ');
					continue;
				}

				Integer code = PieceDisplayTranslate.book
					.get(square.fen().toLowerCase());

				if (square.color() == ColorEnum.WHITE) {
					code += 0x6;
				}

				System.out.print(Character.toChars(code));
			}
			System.out.println();
		}
		System.out.print(" ");
		for (int i = 0; i < hi; i++) {
			System.out.print(i);
		}
		System.out.println();
	}
}
