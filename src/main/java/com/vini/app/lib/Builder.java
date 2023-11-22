package com.vini.app.lib;

public interface Builder<T> {
	public T result();
	public Builder<T> reset();
}
