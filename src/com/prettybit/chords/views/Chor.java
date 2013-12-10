package com.prettybit.chords.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import com.prettybit.chords.ChordsStage2;

import java.util.Random;

import static android.content.Context.MODE_PRIVATE;

/**
 * @author Pavel Mikhalchuk
 */
public class Chor extends View {

    private String c;
    private int size = 60;

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
        float scale = getContext().getSharedPreferences(ChordsStage2.STAGE_PREFS, MODE_PRIVATE).getFloat(ChordsStage2.SCALE_PREF, 1);

        size *= scale;
        p.setTextSize(size);

        setMeasuredDimension((int) p.measureText(c), size);
    }

    @Override
    public void onDraw(Canvas canvas) {
//        float scale = getContext().getSharedPreferences(ChordsStage2.STAGE_PREFS, MODE_PRIVATE).getFloat(ChordsStage2.SCALE_PREF, 1);
//        canvas.scale(scale, scale);

        float d = size - p.getFontMetrics().descent; //compensate descent
        int av = (size - charHeight()) / 2; //align vertically

        canvas.drawText(c, 0, d + av, p);
    }

    private int charHeight() {
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