package com.vini.game.lib;

import java.util.ArrayList;
import java.util.List;

public class CommandQueue {
	private List<List<Command>> commands = new ArrayList<>();

	public void put(Command command, int round, int skipRounds) {
		this.createRoundsGap(round + skipRounds);
		this.commands.get(round + skipRounds).add(command);
	}

	public List<Command> get(int round) {
		this.createRoundsGap(round);
		return this.commands.get(round);
	}

	private void createRoundsGap(int round) {
		for (int i = this.commands.size(); i <= round; i++) {
			this.commands.add(new ArrayList<Command>());
		}
	}
}
