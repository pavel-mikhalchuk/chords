package com.prettybit.chords.entity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * @author Pavel Mikhalchuk
 */
public class Repeat extends Item {

    float scale = 1;
    float middleLine = 0;

    private Paint brush = new Paint();
    private Paint backGround = new Paint();

    public Repeat() {
        brush.setStyle(Paint.Style.FILL);
        brush.setColor(Color.BLACK);
        backGround.setStyle(Paint.Style.STROKE);
        backGround.setColor(Color.BLACK);
    }

    @Override
    public void onMeasure(int size) {
        setWidth(size * 2 / 5);
        setHeight(size);
        scale = size / 80f;
        middleLine = size / 17;
    }

    @Override
    public void draw(Canvas canvas, Caret caret) {
        drawBackGround(canvas, caret);
        drawThickLine(canvas, caret);
        drawThinLine(canvas, caret);
        drawCircles(canvas, caret);
        caret.move(width());
    }

    private void drawBackGround(Canvas canvas, Caret caret) {
        canvas.drawRect(caret.x(), caret.y(), caret.x() + width(), caret.y() + height(), backGround);
    }

    private void drawThickLine(Canvas canvas, Caret caret) {
        canvas.drawRect(caret.x() + width() / 2, caret.y(), caret.x() + width() / 2 + 1 * scale, caret.y() + height(), brush);
    }

    private void drawThinLine(Canvas canvas, Caret caret) {
        canvas.drawRect(caret.x() + width() / 2 + middleLine, caret.y(), caret.x() + width() / 2 + middleLine + 1 * 6 * scale, caret.y() + height(), brush);
    }

    private void drawCircles(Canvas canvas, Caret caret) {
        canvas.drawCircle(caret.x() + width() / 2 - middleLine - 3 * scale, caret.y() + height() / 2 - middleLine, 3.66f * scale, brush);
        canvas.drawCircle(caret.x() + width() / 2 - middleLine - 3 * scale, caret.y() + height() / 2 + middleLine, 3.66f * scale, brush);
    }

}