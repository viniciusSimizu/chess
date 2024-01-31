package com.vini.socket.lib;

import com.vini.game.Game;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class GameStorage {

    private Map<UUID, Game> mapping = new ConcurrentHashMap<>();

		public Game findByUser(UUID id) {
			return this.mapping.get(id);
		}
}
