package com.prettybit.chords.entity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * @author Pavel Mikhalchuk
 */
public class Span extends Item {

    private Items items = new Items();

    private Paint backGround = new Paint();

    public Span() {
        backGround.setStyle(Paint.Style.STROKE);
        backGround.setColor(Color.BLUE);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public void onMeasure(float size) {
        for (Item item : items) {
            item.measure(size);
            widthPlus(item.width());
            setHeight(height() != 0 ? Math.min(item.height(), height()) : item.height());
        }

        items.measure(height());
    }

    @Override
    public void draw(Canvas canvas, Caret caret) {
        canvas.drawRect(caret.x(), caret.y(), caret.x() + width(), caret.y() + height(), backGround);

        items.draw(canvas, caret);
    }

}