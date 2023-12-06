package com.vini.chess;

public enum EnvEnum {
	PORT("PORT");

	public final String property;

	private EnvEnum(String property) {
		this.property = property;
	}
}
