package com.prettybit.chords.entity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;

import java.math.BigInteger;

/**
 * @author Pavel Mikhalchuk
 */
public class Chor extends Item {

    private String c;
    private boolean flat;
    private RectF cRect;

    private Paint text = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint backGround = new Paint();

    public Chor(String c) {
        this(c, false);
    }

    public Chor(String c, boolean flat) {
        this.c = c;
        this.flat = flat;

        text.setColor(Color.BLACK);
        text.setTypeface(Typeface.create(Typeface.SERIF, Typeface.NORMAL));

        backGround.setStyle(Paint.Style.STROKE);
        backGround.setColor(Color.RED);
    }

    @Override
    public void onMeasure(float size) {
        setHeight(size);

        text.setTextSize(height());

        setWidth(text.measureText(c));

        Rect bounds = new Rect();
        text.getTextBounds(c, 0, c.length(), bounds);

        cRect = new RectF((width() - bounds.width()) / 2, (height() - bounds.height()) / 2, bounds.width(), bounds.height());
    }

    @Override
    public void draw(Canvas canvas, Caret caret) {
        drawBackGround(canvas, caret);
        drawText(canvas, caret);
        caret.move(width() + (flat ? 10 : 0));
    }

    private void drawBackGround(Canvas canvas, Caret caret) {
        canvas.drawRect(caret.x(), caret.y(), caret.x() + width(), caret.y() + height(), backGround);
    }

    private void drawText(Canvas canvas, Caret caret) {
//        canvas.drawText(c, caret.x() + cRect.left, caret.y() + cRect.top + (height() - text.getFontMetrics().descent), text);
        canvas.drawText(c, caret.x(), caret.y() + cRect.top + (height() - text.getFontMetrics().descent), text);
        if (flat) drawFlatSymbol(canvas, caret);
    }

    private void drawFlatSymbol(Canvas canvas, Caret caret) {
        String flat = new String(new BigInteger("e299ad", 16).toByteArray());

        canvas.save();

        text.setTextSize(20);
        canvas.translate(caret.x() + 20, caret.y() + 15);

        canvas.drawText(flat, 0, 0, text);

        text.setTextSize(height());

        canvas.restore();
    }

}