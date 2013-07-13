package com.prettybit.chords.entity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * @author Pavel Mikhalchuk
 */
public class Column extends Item {

    private Items items = new Items();

    private Paint backGround = new Paint();

    public Column() {
        backGround.setStyle(Paint.Style.STROKE);
        backGround.setColor(Color.CYAN);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public void onMeasure(int size) {
        for (Item item : items) {
            item.measure(size / items.size());
            setWidth(Math.max(width(), item.width()));
            heightPlus(item.height());
        }
    }

    @Override
    public void draw(Canvas canvas, Caret caret) {
        drawBackGround(canvas, caret);
        drawLines(canvas, caret.split(height() / items.size()));
        caret.move(width());
    }

    private void drawBackGround(Canvas canvas, Caret caret) {
        canvas.drawRect(caret.x(), caret.y(), caret.x() + width(), caret.y() + height(), backGround);
    }

    private void drawLines(Canvas canvas, Caret caret) {
        items.drawln(canvas, caret);
    }

}