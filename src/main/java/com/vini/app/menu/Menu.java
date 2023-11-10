package com.vini.app.menu;

import java.util.List;
import java.util.Scanner;

import com.vini.app.pieces.IPiece;
import com.vini.app.setups.Setup;
import com.vini.app.setups.SetupClassic;
import com.vini.app.setups.SetupData;
import com.vini.app.boardManager.BoardManager;
import com.vini.app.fen.Fen;

public class Menu {
	private Setup[] setups = {
		new SetupClassic()
	};
	private Scanner sc = new Scanner(System.in);

	public void start() {
		for (int i = 0; i < this.setups.length; i++) {
			System.out.println(String.format("%d - %s", i+1, setups[i].description()));
		}

		System.out.print("Choose one setup: ");
		int choosed = this.sc.nextInt() - 1;

		SetupData setup = this.setups[choosed].config();
		List<List<IPiece>> board = new Fen(setup.pieceMap).build(setup.fen);

		new BoardManager()
			.setBoard(board)
			.setTurn(setup.turn)
			.play();
	}
}

