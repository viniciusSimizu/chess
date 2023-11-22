package com.vini.app.menu;

import java.util.List;
import java.util.Scanner;

import com.vini.app.board.Board;
import com.vini.app.fen.FenImpl;
import com.vini.app.game.Game;
import com.vini.app.mode.ModeImpl;
import com.vini.app.mode.ModeRegistry;
import com.vini.app.mode.setup.Setup;

public class Menu {
	private static Menu instance;

	private Menu() {};

	public static Menu instance() {
		if (Menu.instance == null) {
			Menu.instance = new Menu();
		}
		return Menu.instance;
	}

	private Scanner sc = new Scanner(System.in);
	private List<ModeImpl> modes = ModeRegistry.instance().modes();

	public void start() {
		for (int i = 0; i < this.modes.size(); i++) {
			System.out.println(String.format("%d - %s", i+1, this.modes.get(i).description()));
		}

		System.out.print("Choose one setup: ");
		int choosed = this.sc.nextInt() - 1;

		ModeImpl mode = this.modes.get(choosed);
		Setup setup = mode.setup();

		FenImpl fenFactory = new FenImpl(mode.factory());

		Board board = fenFactory.build(setup.fen());

		Game game = new Game(board, setup.turn());

		game.play();
	}
}

