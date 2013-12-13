package com.prettybit.chords.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Pavel Mikhalchuk
 */
public class SongView extends ViewGroup {

    public SongView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int x = l;
        int y = t;

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.layout(x, y, x + child.getMeasuredWidth(), y + child.getMeasuredHeight());

            x += child.getMeasuredWidth();
        }
    }

    public void setScale(float scale) {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setScaleX(scale);
            getChildAt(i).setScaleY(scale);
        }
    }

}