package com.vini.app.fen;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.vini.app.pieces.Piece;
import com.vini.app.types.ColorEnum;

public class Fen {
	HashMap<String, Class<? extends Piece>> pieceMap;

	Fen(HashMap<String, Class<? extends Piece>> pieceMap) {
		this.pieceMap = pieceMap;
	}

	public List<List<Piece>> build(String fenNotation) {
		List<List<Piece>> board = new ArrayList<>();
		List<Piece> row = new ArrayList<>();

		for (int i = 0; i < fenNotation.length(); i++) {
			String command = this.fenCommand(fenNotation, i);

			if (command == "/") {
				board.add(row);
				row = new ArrayList<>();
				continue;
			}

			if (command.matches("[a-zA-Z]+")) {
				ColorEnum color = ColorEnum.BLACK;

				if (Character.isUpperCase(command.charAt(0))) {
					color = ColorEnum.WHITE;
				}

				try {
					Constructor<? extends Piece> constructor = this.pieceMap.get(command).getDeclaredConstructor();
					Piece piece = constructor.newInstance(color, new int[]{row.size(), board.size()}, command);
					row.add(piece);
				} catch (Exception e) {}

				continue;
			}

			if (command.matches("[0-9]+")) {
				for (int j = 0; j < Integer.parseInt(command); j++) {
					row.add(null);
				}
			}
		}

		board.add(row);
		return board;
	}

	private String fenCommand(String fenNotation, int i) {
		String command = "";

		while(true) {
			char chr = fenNotation.charAt(i);
			command.concat(Character.toString(chr));

			if (command.matches("\\d+")) {
				try {
					char nextChr = fenNotation.charAt(i+1);
					
					if (!Character.isDigit(nextChr)) {
						return command;
					}
					i++;
					continue;
				} catch (ArrayIndexOutOfBoundsException e) {
					return command;
				}
			}

			if (command == "/") {
				return command;
			}

			return command;
		}
	}
}

