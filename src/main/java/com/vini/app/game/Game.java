package com.vini.app.game;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import com.vini.app.board.Board;
import com.vini.app.board.BoardHelper;
import com.vini.app.piece.Piece;
import com.vini.app.translate.PieceEnumIconTranslate;
import com.vini.app.types.ColorEnum;

public class Game {
	private ColorEnum turn;
	private Board board;

	private Scanner sc = new Scanner(System.in);

	public Game(Board board, ColorEnum turn) {
		this.board = board;
		this.turn = turn;
	}
	
	public void play() {
		String command;
		String[] splitted = new String[2];

		while (true) {
			System.out.println(String.format("%s turn", this.turn.toString()));

			this.printBoard();

			System.out.print("Move piece: ");
			command = this.sc.nextLine();
			splitted = command.split(" ");

			int[] position = {
				Integer.parseInt(splitted[1]),
				Integer.parseInt(splitted[0])
			};

			if (!BoardHelper.isPositionInsideBoard(position, this.board)) {
				System.out.println("Position not found");
				continue;
			};

			Piece piece = this.board.piece(position);

			if (piece == null) {
				System.out.println("No piece selected");
				continue;
			}

			if (piece.color() != this.turn) {
				System.out.println(String.format("Cannot move piece in %s turn", this.turn.toString()));
				continue;
			}

			piece.updateMoves(board);
			System.out.println(piece.moves());
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

				for (Piece square : this.boardIterator) {
				}
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

	private void printBoard(Piece piece) {
		int hi = 0;
		for (int i = 0; i < piece.moves().size(); i++) {
			System.out.print(i);
			if (piece.moves().get(i).size() > hi) {
				hi = piece.moves().get(i).size();
			}
			for (int j = 0; j < piece.moves().get(i).size(); j++) {
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
				Piece square = this.board.get(i).get(j);

				if (Objects.isNull(square)) {
					System.out.print(' ');
					continue;
				}

				Integer code = PieceEnumIconTranslate.book
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
