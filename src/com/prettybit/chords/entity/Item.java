package com.prettybit.chords.entity;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

/**
 * @author Pavel Mikhalchuk
 */
public interface Item {

    void draw(Canvas canvas, Caret caret);

    View toView(Context context);

}