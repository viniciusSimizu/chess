package com.vini.game.structs;

public class Position implements Cloneable {

    public Integer x, y;

    public Position() {
        this.x = this.y = null;
    }

    public Position(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public int getIndex(int width) {
        return this.y * width + this.x;
    }

    public void update(Position position) {
        this.x = position.x;
        this.y = position.y;
    }

    public void updateFromIndex(int index, int width) {
        this.x = index % width;
        this.y = Math.floorDiv(index, width);
    }

    public static Position fromIndex(int index, int width) {
        int x = index % width;
        int y = Math.floorDiv(index, width);
        var position = new Position(x, y);
        return position;
    }

    @Override
    public Position clone() {
        return new Position(this.x, this.y);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Position)) return false;

        var that = (Position) obj;
        if (this.x != that.x) return false;
        if (this.y != that.y) return false;

        return true;
    }
}
