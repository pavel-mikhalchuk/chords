package com.prettybit.chords.entity;

/**
 * @author Pavel Mikhalchuk
 */
public class Caret {

    private int size;
    private int start = 0;
    private int x = 0;
    private int y = 0;

    private Caret parent;

    public Caret(int size) {
        this(0, 0, size, null);
    }

    public Caret(int x, int y, int size, Caret parent) {
        this.start = this.x = x;
        this.y = y;
        this.size = size;
        this.parent = parent;
    }

    public int x() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int y() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void set(int x, int y) {
        setX(x);
        setY(y);
    }

    public int size() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int width() {
        return x - start;
    }

    public Caret split(int rowSize) {
        return new Caret(x, y, rowSize, this);
    }

    public void move(int distance) {
        setX(x + distance);
    }

    public void newLine() {
        setY(y + size);
        setX(start);
    }

}