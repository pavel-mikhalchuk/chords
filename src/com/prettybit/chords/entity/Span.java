package com.prettybit.chords.entity;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.LinearLayout;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * @author Pavel Mikhalchuk
 */
public class Span implements Item {

    private Items items = new Items();

    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public void draw(Canvas canvas, Caret caret) {
        items.draw(canvas, caret);
    }

    @Override
    public android.view.View toView(Context context) {
        return new View(context);
    }

    private class View extends LinearLayout implements ItemView {

        public View(Context context) {
            super(context);
            setOrientation(HORIZONTAL);
            setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));

            for (Item item : items) {
                addView(item.toView(context));
            }
        }

        @Override
        public void setSize(int size) {
            for (int i = 0; i < getChildCount(); i++) {
                ((ItemView) getChildAt(i)).setSize(size);
            }
        }

    }

}