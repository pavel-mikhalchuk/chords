package com.prettybit.chords.entity;

import android.graphics.Canvas;

import java.util.LinkedList;

/**
 * @author Pavel Mikhalchuk
 */
public class Items extends LinkedList<Item> {

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

    public void measure(int size) {
        for (Item item : this) {
            item.measure(size);
        }
    }

}