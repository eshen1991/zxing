package com.google.zxing.common;

/**
 * Created by eshen on 5/13/16.
 */
public class BitXY {

    int x, y;  //x is column, y is row

    int originalOffset;

    public BitXY(int x, int y, int originalOffset) {
        this.x = x;
        this.y = y;
        this.originalOffset = originalOffset;
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

    public int getOriginalOffset() {
        return originalOffset;
    }

    public void setOriginalOffset(int originalOffset) {
        this.originalOffset = originalOffset;
    }

    @Override
    public String toString() {
        return "[" + x + "," + y + "," + originalOffset + "]";
    }
}
