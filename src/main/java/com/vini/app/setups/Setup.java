package com.vini.app.setups;

public abstract class Setup implements ISetup {
	private final String description;

	Setup(String description) {
		this.description = description;
	}

	public String description() {
		return this.description;
	}
}

