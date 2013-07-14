package com.prettybit.chords.entity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * @author Pavel Mikhalchuk
 */
public class Repeat extends Item {

    float scale = 1;

    private Paint brush = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint backGround = new Paint();

    public Repeat() {
        brush.setStyle(Paint.Style.FILL);
        brush.setColor(Color.BLACK);
        backGround.setStyle(Paint.Style.STROKE);
        backGround.setColor(Color.BLACK);
    }

    @Override
    public void onMeasure(int size) {
        setWidth(8);
        setHeight(size);
    }

    @Override
    public void draw(Canvas canvas, Caret caret) {
        caret.move(5);

        canvas.save();
//        drawBackGround(canvas, caret);
        drawCircles(canvas, caret);
        drawLines(canvas, caret);
        caret.move(width() + 5);
        canvas.restore();
    }

    private void drawBackGround(Canvas canvas, Caret caret) {
        canvas.drawRect(caret.x(), caret.y(), caret.x() + width(), caret.y() + height(), backGround);
    }

    private void drawCircles(Canvas canvas, Caret caret) {
        canvas.drawCircle(caret.x() + 2, caret.y() + height() / 2 - 2, 1, brush);
        canvas.drawCircle(caret.x() + 2, caret.y() + height() / 2 + 2, 1, brush);
        canvas.translate(5, 0);
    }

    private void drawLines(Canvas canvas, Caret caret) {
        canvas.drawLine(caret.x(), caret.y(), caret.x(), caret.y() + height(), brush);
        canvas.translate(2, 0);
        canvas.drawLine(caret.x(), caret.y(), caret.x(), caret.y() + height(), brush);
    }

}