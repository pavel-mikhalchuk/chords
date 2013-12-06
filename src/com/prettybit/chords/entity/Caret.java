package com.prettybit.chords.entity;

/**
 * @author Pavel Mikhalchuk
 */
public class Caret {

    private float size;
    private float start = 0;
    private float x = 0;
    private float y = 0;

    private Caret parent;

    public Caret() {
        this(0);
    }

    public Caret(float size) {
        this(0, 0, size, null);
    }

    public Caret(float x, float y, float size, Caret parent) {
        this.start = this.x = x;
        this.y = y;
        this.size = size;
        this.parent = parent;
    }

    public float x() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float y() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void set(float x, float y) {
        setX(x);
        setY(y);
    }

    public float size() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public float width() {
        return x - start;
    }

    public Caret split(float rowSize) {
        return new Caret(x, y, rowSize, this);
    }

    public void move(float distance) {
        setX(x + distance);
    }

    public void newLine() {
        setY(y + size);
        setX(start);
    }

}