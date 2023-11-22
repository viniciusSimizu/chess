package com.vini.app.types;

public enum ColorEnum {
	BLACK,
	WHITE;

	@Override
	public String toString() {
		switch (this) {
			case WHITE:
				return "White";
			case BLACK:
				return "Black";
			default:
				return null;
		}
	}
}

