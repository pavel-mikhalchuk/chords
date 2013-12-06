package com.prettybit.chords.entity;

import android.graphics.Canvas;

/**
 * @author Pavel Mikhalchuk
 */
public class SongPart extends Item {

    private Items items = new Items();

    @Override
    public void onMeasure(float size) {
        items.measure(size);
        setHeight(items.height());
        setWidth(items.width());
    }

    @Override
    public void draw(Canvas canvas, Caret caret) {
        items.draw(canvas, caret);
    }

    public void addItem(Item item) {
        items.add(item);
    }

}