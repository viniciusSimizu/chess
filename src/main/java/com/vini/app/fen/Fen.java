package com.vini.app.fen;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.vini.app.pieces.IPiece;
import com.vini.app.pieces.PieceDecorator;
import com.vini.app.types.ColorEnum;

public class Fen {
	Map<String, Class<? extends IPiece>> pieceMap;

	public Fen(Map<String, Class<? extends IPiece>> pieceMap) {
		this.pieceMap = pieceMap;
	}

	public List<List<IPiece>> build(String fenNotation) {
		List<List<IPiece>> board = new ArrayList<>();
		List<IPiece> row = new ArrayList<>();

		for (int i = 0; i < fenNotation.length(); i++) {
			String command = this.fenCommand(fenNotation, i);

			if (command.equals("/")) {
				board.add(row);
				row = new ArrayList<>();
				continue;
			}

			if (command.matches("[0-9]+")) {
				for (int j = 0; j < Integer.parseInt(command); j++) {
					row.add(null);
				}
			}

			ColorEnum color = ColorEnum.BLACK;

			if (Character.isUpperCase(command.charAt(0))) {
				color = ColorEnum.WHITE;
			}

			try {
				IPiece piece = this.pieceMap
					.get(command.toLowerCase())
					.getConstructor()
					.newInstance()
					.setColor(color)
					.setPosition(new int[]{row.size(), board.size()})
					.setFen(command);

				row.add(new PieceDecorator(piece));
			} catch (Exception e) {}
		}

		board.add(row);
		return board;
	}

	private String fenCommand(String fenNotation, int i) {
		String command = "";

		while(true) {
			char chr = fenNotation.charAt(i);
			command = command.concat(Character.toString(chr));

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

