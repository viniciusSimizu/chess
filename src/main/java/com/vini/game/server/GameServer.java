package com.vini.game.server;

import com.vini.game.Game;
import com.vini.game.GameManager;
import com.vini.game.enums.ColorEnum;
import com.vini.game.fen.Fen;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class GameServer extends Thread {
  public final UUID id = UUID.randomUUID();
  private final GameManager gameManager;
  private final List<ColorEnum> availableColors;

  public GameServer() {
    String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
    ColorEnum turn = ColorEnum.WHITE;
    this.availableColors = Arrays.asList(ColorEnum.WHITE, ColorEnum.BLACK);
    Game game = new Game(Fen.build(fen), turn);
    game.attachGameOverPieces();

    this.gameManager = new GameManager(game);
  }

  public void attachPlayer() {
    throw new UnsupportedOperationException("Unimplemented method 'attachPlayer'");
  }

  @Override
  public void run() {}
}
