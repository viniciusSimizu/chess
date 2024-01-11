package com.vini.server.database;

import com.vini.game.server.GameServer;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OpenGameServers {
  private static OpenGameServers INSTANCE;

  private Map<UUID, GameServer> servers = new HashMap<>();

  private OpenGameServers() {}

  public static OpenGameServers getInstance() {
    if (OpenGameServers.INSTANCE == null) {
      OpenGameServers.INSTANCE = new OpenGameServers();
    }
    return OpenGameServers.INSTANCE;
  }

  public GameServer findServer(UUID id) { return this.servers.get(id); }

  public void registerServer(GameServer server) {
    this.servers.put(server.id, server);
  }

  public void removeServer(UUID id) { this.servers.remove(id); }
}
