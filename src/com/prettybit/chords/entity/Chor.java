package com.prettybit.chords.entity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;

/**
 * @author Pavel Mikhalchuk
 */
public class Chor implements Item {

    private String c;

    private Paint paint = new Paint(Color.BLACK);

    @Override
    public void draw(Canvas canvas, Caret caret) {
//        paint.setTextSize(caret.getSize() * 2 / 3);
        paint.setTextSize(caret.getSize());

//        Rect r = new Rect();
//        paint.getTextBounds(c, 0, 1, r);

//        canvas.drawText(c, caret.getX() - r.left, caret.getY() - r.top, paint);
//        caret.move(r.width());

        Path p = new Path();
        p.addRect(caret.getX(), caret.getY(), caret.getX() + caret.getSize() * c.length(), caret.getY() + caret.getSize(), Path.Direction.CW);

        Paint ppp = new Paint(Color.RED);
        ppp.setStyle(Paint.Style.STROKE);

        canvas.drawPath(p, ppp);

        canvas.drawTextOnPath(c, p, 0, caret.getSize() - paint.getFontMetrics().descent, paint);

        caret.move(caret.getSize());
    }

    public Chor(String c) {
        this.c = c;
    }

    @Override
    public View toView(Context context) {
        return new View(context, c);
    }

    private class View extends android.view.View implements ItemView {

        private String c;

        private Paint paint = new Paint(Color.BLACK);
        private Integer x, y;

        public View(Context context, String c) {
            super(context);
            this.c = c;

            setBackgroundColor(Color.GREEN);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawText(c, x, y, paint);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            Rect r = new Rect();
            paint.getTextBounds(c, 0, c.length(), r);
            x = -r.left;
            y = -r.top;

            setMeasuredDimension(r.width(), r.height());
        }

        @Override
        public void setSize(int size) {
            paint.setTextSize(size);
            if (x != null) {
                requestLayout();
                invalidate();
            }
        }

    }

}