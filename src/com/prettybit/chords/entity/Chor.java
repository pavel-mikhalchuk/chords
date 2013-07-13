package com.prettybit.chords.entity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * @author Pavel Mikhalchuk
 */
public class Chor extends Item {

    private String c;
    private Rect cRect;

    private Paint text = new Paint();
    private Paint backGround = new Paint();

    public Chor(String c) {
        this.c = c;
        text.setColor(Color.BLACK);
        backGround.setStyle(Paint.Style.STROKE);
        backGround.setColor(Color.RED);
    }

    @Override
    public void onMeasure(int size) {
        setWidth(size);
        setHeight(size);

        text.setTextSize(height());

        Rect bounds = new Rect();
        text.getTextBounds(c, 0, c.length(), bounds);

        cRect = new Rect((width() - bounds.width()) / 2, (height() - bounds.height()) / 2, bounds.width(), bounds.height());
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
        canvas.drawText(c, caret.x() + cRect.left, caret.y() + cRect.top + (height() - text.getFontMetrics().descent), text);
    }

}