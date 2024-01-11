package com.vini.game;

import com.vini.server.socket.ActionTypeEnum;
import java.util.Arrays;

public class GameManager {
  public Game game;

  public GameManager(Game game) { this.game = game; }

  public boolean action(String message) {
    String[] commands = message.split(";");
    String action = commands[0];
    String[] params = Arrays.copyOfRange(commands, 1, commands.length);

    switch (ActionTypeEnum.findAction(action)) {
    case MOVE:
      return this.actionMove(params);

    default:
      return false;
    }
  }

  public boolean actionMove(String[] params) {
    int[] sourcePosition = new int[2];
    int[] targetPosition = new int[2];

    try {
      sourcePosition[0] = Integer.parseInt(params[0]);
      sourcePosition[1] = Integer.parseInt(params[1]);
      targetPosition[2] = Integer.parseInt(params[2]);
      targetPosition[3] = Integer.parseInt(params[3]);
    } catch (NumberFormatException err) {
      err.printStackTrace();
      return false;
    }

    return this.game.move(sourcePosition, targetPosition);
  }
}
