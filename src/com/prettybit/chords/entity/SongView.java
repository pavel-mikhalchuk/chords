package com.prettybit.chords.entity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Pavel Mikhalchuk
 */
public class SongView extends View {

    private float scale = 1;
    private List<SongPart> parts = new LinkedList<SongPart>();

    private SongPart active;

    private Item activeItem;

    public SongView(Context context) {
        super(context);
        setBackgroundColor(Color.CYAN);

        setOnTouchListener(new TouchListener(context));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int screenWidth = MeasureSpec.getSize(widthMeasureSpec);
        int screenHeight = MeasureSpec.getSize(heightMeasureSpec);

        for (SongPart part : parts) {
            part.init(screenWidth, screenHeight);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.scale(scale, scale);
        for (SongPart part : parts) {
            part.draw(canvas);
            canvas.translate(0, part.size() + 20);
        }
    }

    public void addPart(SongPart part) {
        this.parts.add(part);
        active = part;
    }

    public void addParts(List<SongPart> parts) {
        this.parts.addAll(parts);
    }

    public void setScale(float scale) {
        this.scale *= scale;
    }

    public SongPart getActive() {
        return active;
    }

    public Item getActiveItem() {
        return activeItem;
    }

    private class TouchListener implements View.OnTouchListener {

        private ScaleGestureDetector d;

        private TouchListener(Context context) {
            d = new ScaleGestureDetector(context, new Scaler());
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getActionMasked() == MotionEvent.ACTION_UP) {
                if (activeItem != null) {
                    activeItem.deselect();
                }

                for (SongPart part : parts) {
                    activeItem = part.contains((int) event.getX(), (int) event.getY());
                }

                if (activeItem != null) {
                    activeItem.select();
                }
            }

            invalidate();

            return d.onTouchEvent(event);
        }

        private class Scaler extends ScaleGestureDetector.SimpleOnScaleGestureListener {

            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                setScale(detector.getScaleFactor());
                invalidate();
                return true;
            }

        }

    }

}