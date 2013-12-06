package com.prettybit.chords.entity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * @author Pavel Mikhalchuk
 */
public class Line extends Item {

    private String line;
    private RectF lRect;

    private Paint text = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint backGround = new Paint();

    public Line(String line) {
        this.line = line;
        text.setColor(Color.BLACK);
        backGround.setStyle(Paint.Style.STROKE);
        backGround.setColor(Color.GREEN);
    }

    @Override
    public void onMeasure(float size) {
        setHeight(size);

        text.setTextSize(height());

        Rect bounds = new Rect();
        text.getTextBounds(line, 0, line.length(), bounds);

        setWidth((int) text.measureText(line));

        lRect = new RectF((width() - bounds.width()) / 2, (height() - bounds.height()) / 2, bounds.width(), bounds.height());
    }

    @Override
    public void draw(Canvas canvas, Caret caret) {
        drawBackGround(canvas, caret);
        drawText(canvas, caret);
        caret.move(width());
    }

    private void drawBackGround(Canvas canvas, Caret caret) {
        canvas.drawRect(caret.x(), caret.y(), caret.x() + width(), caret.y() + height(), backGround);
    }

    private void drawText(Canvas canvas, Caret caret) {
        canvas.drawText(line, caret.x() + lRect.left, caret.y() + lRect.top + (height() - text.getFontMetrics().descent), text);
    }

}