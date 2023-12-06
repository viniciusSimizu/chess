package com.vini.chess.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServerConfig {
	@Value("${spring.socket.port}")
	private int PORT;

	public int port() {
		return this.PORT;
	}
}
