package com.prettybit.chords.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

/**
 * @author Pavel Mikhalchuk
 */
public class Chor extends View {

    private String c;
    private float size = 60;

    private Paint p = initPaint();

    public Chor(Context context, String c) {
        super(context, null);
        this.c = c;

        setBackgroundColor(Color.CYAN);

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                setBackgroundColor(new Random().nextInt());
                return false;
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        p.setTextSize(size);

        setMeasuredDimension((int) p.measureText(c), (int) size);
    }

    @Override
    public void onDraw(Canvas canvas) {
        float d = size - p.getFontMetrics().descent; //compensate descent
        float av = (size - charHeight()) / 2; //align vertically

        canvas.drawText(c, 0, d + av, p);
    }

    private float charHeight() {
        Rect bounds = new Rect();
        p.getTextBounds(c, 0, c.length(), bounds);
        return bounds.height();
    }

    private Paint initPaint() {
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(Color.BLACK);
        p.setTypeface(Typeface.create(Typeface.SERIF, Typeface.NORMAL));
        p.setTextSize(size);
        return p;
    }

}