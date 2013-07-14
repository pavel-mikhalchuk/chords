package com.prettybit.chords.entity;

import android.graphics.Canvas;

/**
 * @author Pavel Mikhalchuk
 */
public class Verse extends Chor {

    public Verse(int number) {
        super(number + "");
    }

    @Override
    public void draw(Canvas canvas, Caret caret) {
        super.draw(canvas, caret);
        caret.move(20);
    }
}