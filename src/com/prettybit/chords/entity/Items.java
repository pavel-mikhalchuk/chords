package com.prettybit.chords.entity;

import android.graphics.Canvas;

import java.util.LinkedList;

/**
 * @author Pavel Mikhalchuk
 */
public class Items extends LinkedList<Item> {

    private float width;
    private float height;

    public void draw(Canvas canvas, Caret caret) {
        for (Item item : this) {
            item.draw(canvas, caret);
        }
    }

    public void drawln(Canvas canvas, Caret caret) {
        for (Item item : this) {
            item.drawln(canvas, caret);
        }
    }

    public void measure(float size) {
        for (Item item : this) {
            item.measure(size);
            width += item.width();
        }
        height = size;
    }

    public float width() {
        return width;
    }

    public float height() {
        return height;
    }

}