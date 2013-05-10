package com.prettybit.chords.entity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * @author Pavel Mikhalchuk
 */
public class Repeat implements Item {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);


    @Override
    public void draw(Canvas canvas, Caret caret) {
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.BLACK);

        float height = caret.getSize();
        float width = height * 2 / 5;
        float scale = height / 80.0f;
        float middleLine = height / 17;

        Path p = new Path();
        p.addRect(caret.getX(), caret.getY(), caret.getX() + width, caret.getY() + height, Path.Direction.CW);

        Paint ppp = new Paint(Color.RED);
        ppp.setStyle(Paint.Style.STROKE);

        canvas.drawPath(p, ppp);

        //1 line
        canvas.drawRect(caret.getX() + width / 2, caret.getY(), caret.getX() + width / 2 + 1 * scale, caret.getY() + height, paint);

        //2 line
        canvas.drawRect(caret.getX() + width / 2 + middleLine, caret.getY(), caret.getX() + width / 2 + middleLine + 1 * 6 * scale, caret.getY() + height, paint);

        //circles
        canvas.drawCircle(caret.getX() + width / 2 - middleLine - 3 * scale, caret.getY() + height / 2 - middleLine, 3.66f * scale, paint);
        canvas.drawCircle(caret.getX() + width / 2 - middleLine - 3 * scale, caret.getY() + height / 2 + middleLine, 3.66f * scale, paint);

        caret.move((int) width);
    }

    @Override
    public android.view.View toView(Context context) {
        return new View(context);
    }

    private class View extends android.view.View implements ItemView {

        private int height;
        private int width;
        private float scale;
        private float middleLine;

        private Paint paint;

        public View(Context context) {
            super(context);
            initPaint();

            setBackgroundColor(Color.RED);
        }

        private void initPaint() {
            paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setColor(Color.BLACK);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            //1 line
            canvas.drawRect(width / 2, 0, width / 2 + 1 * scale, height, paint);

            //2 line
            canvas.drawRect(width / 2 + middleLine, 0, width / 2 + middleLine + 1 * 6 * scale, height, paint);

            //circles
            canvas.drawCircle(width / 2 - middleLine - 3 * scale, height / 2 - middleLine, 3.66f * scale, paint);
            canvas.drawCircle(width / 2 - middleLine - 3 * scale, height / 2 + middleLine, 3.66f * scale, paint);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            setMeasuredDimension(width, height);
        }

        @Override
        public void setSize(int size) {
            height = size;
            width = height * 3 / 6;
            scale = size / 80.0f;
            middleLine = size / 17;
            requestLayout();
            invalidate();
        }

    }

}