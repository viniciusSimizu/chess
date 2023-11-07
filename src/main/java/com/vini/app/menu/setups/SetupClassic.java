package com.vini.app.menu.setups;

import com.vini.app.types.ColorEnum;

public class SetupClassic extends Setup {
	public String description = "The classic experience of chess";

	@Override
	SetupData getConfig() {
		return new SetupData("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", ColorEnum.WHITE);
	}
}
