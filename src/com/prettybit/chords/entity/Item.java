package com.prettybit.chords.entity;

import android.graphics.Canvas;

/**
 * @author Pavel Mikhalchuk
 */
public abstract class Item {

    private float width;
    private float height;

    public void drawln(Canvas canvas, Caret caret) {
        draw(canvas, caret);
        caret.newLine();
    }

    public float width() {
        return width;
    }

    protected void setWidth(float width) {
        this.width = width;
    }

    protected void widthPlus(float width) {
        this.width += width;
    }

    public float height() {
        return height;
    }

    protected void setHeight(float height) {
        this.height = height;
    }

    protected void heightPlus(float height) {
        this.height += height;
    }

    protected void measure(float size) {
        width = height = 0;
        onMeasure(size);
    }

    public abstract void onMeasure(float size);

    public abstract void draw(Canvas canvas, Caret caret);

}