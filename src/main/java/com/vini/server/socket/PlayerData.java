package com.vini.server.socket;

import com.vini.game.enums.ColorEnum;
import com.vini.game.server.GameServer;
import java.net.Socket;
import java.util.UUID;

public class PlayerData {

  public final UUID id = UUID.randomUUID();
  public final Socket connection;
  public GameServer gameServer;
  public ColorEnum color;

  public PlayerData(Socket connection) { this.connection = connection; }
}
