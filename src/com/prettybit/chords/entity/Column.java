package com.prettybit.chords.entity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.widget.LinearLayout;

import java.util.Iterator;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * @author Pavel Mikhalchuk
 */
public class Column implements Item {

    private Items items = new Items();

    private int width = 0;

    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public void draw(Canvas canvas, Caret caret) {
        drawLines(canvas, caret.split(items.size()));
        caret.move(width);
    }

    private void drawLines(Canvas canvas, Caret caret) {
        for (Item item : items) {
            item.draw(canvas, caret);
            width = Math.max(width, caret.getWidth());
            caret.newLine();
        }
    }

    @Override
    public View toView(Context context) {
        View c = new View(context);

        c.setBackgroundColor(Color.YELLOW);
        return c;
    }

    public class View extends LinearLayout implements ItemView {

        public View(Context context) {
            super(context);
            setOrientation(VERTICAL);
            setLayoutParams(new LayoutParams(WRAP_CONTENT, WRAP_CONTENT));

            Iterator<Item> i = items.iterator();
            addView(i.next().toView(context));
            while (i.hasNext()) {
                addSpacer(context);
                addView(i.next().toView(context));
            }
        }

        @Override
        public void setSize(int size) {
            int spacerSize = size / 20;
            int itemSize = (size - spacerSize * (items.size() - 1)) / items.size();

            for (int i = 0; i < getChildCount(); i++) {
                if (getChildAt(i) instanceof ItemView) {
                    ((ItemView) getChildAt(i)).setSize(itemSize);
                } else {
                    if (i == getChildCount() - 2) {
                        getChildAt(i).setLayoutParams(new LayoutParams(MATCH_PARENT, size - (items.size() * itemSize + (items.size() - 2) * spacerSize)));
                    } else {
                        getChildAt(i).setLayoutParams(new LayoutParams(MATCH_PARENT, spacerSize));
                    }
                }
            }
        }

        private void addSpacer(Context context) {
            android.view.View s = new android.view.View(context);
            s.setBackgroundColor(Color.BLUE);
            addView(s);
        }

    }

}