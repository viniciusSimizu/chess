package com.vini.game.server;

import com.vini.game.Game;
import com.vini.game.GameManager;
import com.vini.game.enums.ColorEnum;
import com.vini.game.fen.Fen;
import com.vini.server.socket.PlayerData;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.UUID;

public class GameServer extends Thread {
  public final UUID id = UUID.randomUUID();
  private final GameManager gameManager;
  private final List<ColorEnum> availableColors;
  private final EnumMap<ColorEnum, PlayerData> players =
      new EnumMap<>(ColorEnum.class);

  public GameServer() {

    String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
    ColorEnum turn = ColorEnum.WHITE;
    this.availableColors = Arrays.asList(ColorEnum.WHITE, ColorEnum.BLACK);
    Game game = new Game(Fen.build(fen), turn, this.players);
    game.attachGameOverPieces();

    this.gameManager = new GameManager(game);
  }

  public void attachPlayer(PlayerData playerData) {
    if (this.availableColors.size() == 0) {
      return;
    }

    int choosedIndex = (int)Math.random() * this.availableColors.size();
    ColorEnum choosedColor = this.availableColors.remove(choosedIndex);
    playerData.color = choosedColor;
    playerData.gameServer = this;
    this.players.put(choosedColor, playerData);
  }

  @Override
  public void run() {}
}
