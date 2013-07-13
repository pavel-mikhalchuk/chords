package com.prettybit.chords.entity;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Pavel Mikhalchuk
 */
public class SongView extends View {

    private float scale = 1;
    private List<SongPart> parts = new LinkedList<SongPart>();

    public SongView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int screenWidth = MeasureSpec.getSize(widthMeasureSpec);
        int screenHeight = MeasureSpec.getSize(heightMeasureSpec);

        for (SongPart part : parts) {
            part.init(screenWidth, screenHeight);
        }
        setMeasuredDimension(screenWidth, screenHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.scale(scale, scale);
        for (SongPart part : parts) {
            part.draw(canvas);
            canvas.translate(0, part.size() + 20);
        }
    }

    public void addParts(List<SongPart> parts) {
        this.parts.addAll(parts);
    }

    public void setScale(float scale) {
        this.scale *= scale;
    }

}