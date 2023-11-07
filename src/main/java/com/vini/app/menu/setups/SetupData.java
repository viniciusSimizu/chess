package com.vini.app.menu.setups;

import com.vini.app.types.ColorEnum;

public class SetupData {
	String fen;
	ColorEnum turn;
	
	SetupData(String fen, ColorEnum turn) {
		this.fen = fen;
		this.turn = turn;
	}
}

