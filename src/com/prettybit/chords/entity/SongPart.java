package com.prettybit.chords.entity;

import android.content.Context;
import android.widget.LinearLayout;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * @author Pavel Mikhalchuk
 */
public class SongPart {

    private Items items = new Items();

    public void addItem(Item item) {
        items.add(item);
    }

    public Items getItems() {
        return items;
    }

    public View toView(Context context) {
        return new View(context);
    }

    public class View extends LinearLayout {

        public View(Context context) {
            super(context);
            setOrientation(HORIZONTAL);
            setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
            for (Item item : items) { addView(item.toView(context)); }
        }

        public void setSize(int size) {
            for (int i = 0; i < getChildCount(); i++) {
                ((ItemView) getChildAt(i)).setSize(size);
            }
        }

    }

}