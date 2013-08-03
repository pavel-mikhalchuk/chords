package com.prettybit.chords.entity;

import android.graphics.Canvas;

/**
 * @author Pavel Mikhalchuk
 */
public class SongPart {

    private int screenWidth;
    private int screenHeight;

    private int size;

    private Items items = new Items();

    public SongPart(int size) {
        this.size = size;
    }

    public void init(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        items.measure(size);
    }

    public int screenWidth() {
        return screenWidth;
    }

    public int screenHeight() {
        return screenHeight;
    }

    public void addItem(Item item) {
        item.setPart(this);
        items.add(item);
    }

    public Items getItems() {
        return items;
    }

    public int size() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void draw(Canvas canvas) {
        items.draw(canvas, new Caret(size));
    }

    public Item contains(int x, int y) {
        for (Item item : items) {
            if (item.contains(x, y)) return item;
        }
        return null;
    }

}