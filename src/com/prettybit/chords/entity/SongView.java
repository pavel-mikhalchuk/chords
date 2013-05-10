package com.prettybit.chords.entity;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

/**
 * @author Pavel Mikhalchuk
 */
public class SongView extends View {

    private SongPart part;

    private int size = 0;

    public SongView(Context context, SongPart part) {
        super(context);
        this.part = part;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        part.getItems().draw(canvas, new Caret(size));
    }

    public void setSize(int size) {
        this.size = size;
    }

}