package com.vini.game.lib;

public class Position {

    public Integer x, y;

    public Position(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public int getIndex(int width) {
        return this.y * width + this.x;
    }

    public static Position fromIndex(int index, int width) {
        int x = index % width;
        int y = Math.floorDiv(index, width);
        var position = new Position(x, y);
        return position;
    }
}
