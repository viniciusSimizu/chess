package com.vini.app.menu;

import java.util.List;
import java.util.Scanner;

import com.vini.app.board.Board;
import com.vini.app.fen.Fen;
import com.vini.app.game.Game;
import com.vini.app.game.modes.Mode;
import com.vini.app.game.modes.ModeRegistry;
import com.vini.app.game.modes.setup.ISetup;

public class Menu {
	private static Scanner sc = new Scanner(System.in);
	private static List<Mode> modes = ModeRegistry.instance().registry;

	public static void start() {
		for (int i = 0; i < Menu.modes.size(); i++) {
			System.out.println(String.format("%d - %s", i+1, Menu.modes.get(i).description()));
		}

		Mode mode = Menu.modes.get(0);
		// System.out.print("Choose one setup: ");
		// int choosed = Menu.sc.nextInt() - 1;

		// Mode mode = Menu.modes.get(choosed);
		ISetup setup = mode.setup();

		Board board = Fen.build(mode.factory(), setup.fen());

		Game game = new Game(board, setup.turn());

		game.play();
	}
}

