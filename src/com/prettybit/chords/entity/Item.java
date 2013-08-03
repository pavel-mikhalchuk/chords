package com.prettybit.chords.entity;

import android.graphics.Canvas;

/**
 * @author Pavel Mikhalchuk
 */
public abstract class Item {

    private int width;
    private int height;

    private SongPart parent;

    protected void setPart(SongPart parent) {
        this.parent = parent;
    }

    public void drawln(Canvas canvas, Caret caret) {
        draw(canvas, caret); caret.newLine();
    }

    public int width() {
        return width;
    }

    protected void setWidth(int width) {
        this.width = width;
    }

    protected void widthPlus(int width) {
        this.width += width;
    }

    public int height() {
        return height;
    }

    protected void setHeight(int height) {
        this.height = height;
    }

    protected void heightPlus(int height) {
        this.height += height;
    }

    public int screenWidth() {
        return parent.screenWidth();
    }

    public int screenHeight() {
        return parent.screenHeight();
    }

    protected void measure(int size) {
        width = height = 0;
        onMeasure(size);
    }

    protected boolean contains(int x, int y) {
        return false;
    }

    protected void select() {}

    protected void deselect() {}

    public abstract void onMeasure(int size);

    public abstract void draw(Canvas canvas, Caret caret);

}