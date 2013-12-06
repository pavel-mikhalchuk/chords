package com.prettybit.chords.entity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * @author Pavel Mikhalchuk
 */
public class Bar extends Item {

    private Paint brush = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint backGround = new Paint();

    public Bar() {
        brush.setStyle(Paint.Style.FILL);
        brush.setColor(Color.BLACK);
        backGround.setStyle(Paint.Style.STROKE);
        backGround.setColor(Color.BLACK);
    }

    @Override
    public void onMeasure(float size) {
        setWidth(7);
        setHeight(size);
    }

    @Override
    public void draw(Canvas canvas, Caret caret) {
        caret.move(width());

//        canvas.drawRect(caret.x(), caret.y(), caret.x() + width(), caret.y() + height(), backGround);

        canvas.drawLine(caret.x(), caret.y(), caret.x(), caret.y() + height(), brush);
        caret.move(width());
    }

}