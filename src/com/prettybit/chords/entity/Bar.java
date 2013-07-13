package com.prettybit.chords.entity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * @author Pavel Mikhalchuk
 */
public class Bar extends Item {

    private Paint brush = new Paint();
    private Paint backGround = new Paint();

    public Bar() {
        brush.setStyle(Paint.Style.FILL);
        brush.setColor(Color.BLACK);
        backGround.setStyle(Paint.Style.STROKE);
        backGround.setColor(Color.BLACK);
    }

    @Override
    public void onMeasure(int size) {
        setWidth(size * 3 / 40);
        setHeight(size);
    }

    @Override
    public void draw(Canvas canvas, Caret caret) {
        canvas.drawRect(caret.x(), caret.y(), caret.x() + width(), caret.y() + height(), backGround);

        canvas.drawRect(caret.x(), caret.y(), caret.x() + width(), caret.y() + height(), brush);
        caret.move(width());
    }

}