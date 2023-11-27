package com.vini.chess.app.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.vini.chess.app.board.Board;
import com.vini.chess.app.board.iterator.BoardIteratorOverPiece;
import com.vini.chess.app.piece.IPiece;
import com.vini.chess.app.piece.commands.PieceMoveCommand;
import com.vini.chess.app.translate.PieceEnumIconTranslate;
import com.vini.chess.app.types.ColorEnum;

public class Game {
	private Board board;
	private ColorEnum turn;

	private final List<PieceMoveCommand> moveHistory = new ArrayList<>();
	private final Scanner sc = new Scanner(System.in);

	public Game(Board board, ColorEnum turn) {
		this.board = board;
		this.turn = turn;
	}
	
	public void play() {
		String command;
		String[] splitted = new String[2];

		while (true) {
			System.out.println(String.format("%s's turn", this.turn.toString()));

			this.printBoard();

			System.out.print("Move piece: ");
			command = this.sc.nextLine();
			splitted = command.split(" ");

			int[] position = Arrays.stream(splitted)
				.mapToInt(s -> Integer.parseInt(s))
				.toArray();

			if (!this.board.isInsideTable(position)) {
				System.out.println("Position not found");
				continue;
			};

			IPiece piece = this.board.findPiece(position);

			if (piece == null) {
				System.out.println("No piece selected");
				continue;
			}

			if (piece.color() != this.turn) {
				System.out.println(String.format("Cannot move piece in %s's turn", this.turn.toString()));
				continue;
			}

			piece.updateMoves();
			this.printBoard(piece);

			System.out.print("To: ");
			command = this.sc.nextLine();
			splitted = command.split(" ");

			position = Arrays.stream(splitted)
				.mapToInt(s -> Integer.parseInt(s))
				.toArray();

			if (piece.canMove(position)) {
				PieceMoveCommand moveCommand = piece.move(position);

				this.moveHistory.add(moveCommand);
				this.toggleTurn();
				this.resetPieceMoves();
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

	private void resetPieceMoves() {
		BoardIteratorOverPiece iterator = new BoardIteratorOverPiece(this.board);

		while (iterator.hasNext()) {
			iterator.next().resetMoves();
		}
	}

	private void printBoard(IPiece piece) {
		int hi = 0;

		for (int row = 0; row < piece.moves().size(); row++) {
			System.out.print(row);

			if (piece.moves().get(row).size() > hi) {
				hi = piece.moves().get(row).size();
			}

			for (int col = 0; col < piece.moves().get(row).size(); col++) {
				boolean canMove = piece.canMove(new int[]{col, row});

				if (canMove) {
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

		for (int i = 0; i < this.board.table().size(); i++) {
			System.out.print(i);

			if (this.board.table().get(i).size() > hi) {
				hi = this.board.table().get(i).size();
			}

			for (int j = 0; j < this.board.table().get(i).size(); j++) {
				IPiece square = this.board.table().get(i).get(j);

				if (square == null) {
					System.out.print(' ');
					continue;
				}

				Integer code = PieceEnumIconTranslate.book
					.get(square.fen());

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
