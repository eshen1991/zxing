package com.google.zxing.common;

/**
 * Created by eshen on 5/22/16.
 */
public class CoordinateXY {
    int x, y;

    public CoordinateXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
