package com.vini.app.menu;

import java.util.List;
import java.util.Scanner;

import com.vini.app.menu.setups.Setup;
import com.vini.app.menu.setups.SetupClassic;
import com.vini.app.menu.setups.SetupData;
import com.vini.app.pieces.Piece;
import com.vini.app.board_manager.BoardManager;
import com.vini.app.fen.Fen;

public class Menu {
	private final Setup[] setups = {
		new SetupClassic()
	};
	private final Scanner sc = new Scanner(System.in);

	public void start() {
		for (int i = 0; i < this.setups.length; i++) {
			System.out.println(String.format("%d - %s", i+1, this.setups[i].description));
		}

		System.out.print("Choose one setup: ");
		int choosed = sc.nextInt() - 1;

		SetupData setup = this.setups[choosed].getConfig();
		List<List<Piece>> board = new Fen(setup.pieceMap).build(setup.fen);

		new BoardManager();
	}
}

