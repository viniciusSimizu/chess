package com.vini.game.server;

public class UserPackage {
  public int withoutSignalCount = 0;
  public GameServer gameServer;

  public UserPackage(GameServer gameServer) { this.gameServer = gameServer; }
}
