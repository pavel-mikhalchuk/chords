package com.prettybit.chords.entity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Pavel Mikhalchuk
 */
public class Tab extends Item {

    private Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

    public Tab() {
        p.setTextSize(10);
        p.setColor(Color.BLACK);
    }

    @Override
    public void onMeasure(int size) {
        setHeight(size);
        setWidth(12 * 10);
    }

    @Override
    public void draw(Canvas canvas, Caret caret) {
        int strings = 3;

        List<Cut> cuts = new LinkedList<Cut>();
        cuts.add(new Cut("6 - -"));
        cuts.add(new Cut("- - -"));
        cuts.add(new Cut("- 6 -"));
        cuts.add(new Cut("- - -"));
        cuts.add(new Cut("- 6 -"));
        cuts.add(new Cut("- - -"));
        cuts.add(new Cut("- 5 -"));
        cuts.add(new Cut("8 - -"));
        cuts.add(new Cut("- - -"));
        cuts.add(new Cut("6 - -"));

        canvas.save();

        canvas.translate(caret.x(), caret.y());

        canvas.save();

        for (int i = 1; i <= strings; i++) {
            canvas.drawLine(0, 0, width(), 0, p);
            canvas.translate(0, height() / (strings - 1));
        }

        canvas.restore();

        canvas.save();

        canvas.translate(10, 0);

        for (Cut c : cuts) {
            c.draw(canvas);
            canvas.translate(10, 0);
        }

        canvas.restore();

        canvas.restore();

        caret.move(width());
    }

    private class Cut {

        private String[] notes;

        public Cut(String c) {
            notes = c.split(" ");
        }

        public void draw(Canvas canvas) {
            canvas.save();

            for (int i = notes.length - 1; i >= 0; i--) {
                if (!notes[i].equals("-")) {
                    p.setColor(Color.WHITE);
                    p.setStyle(Paint.Style.FILL);
                    canvas.drawRect(0, -5, 10, 5, p);

                    p.setColor(Color.BLACK);
                    p.setStyle(Paint.Style.STROKE);
                    canvas.drawText(notes[i], (10 - p.measureText(notes[i])) / 2, 4, p);
                }
                canvas.translate(0, height() / (notes.length - 1));
            }

            canvas.restore();
        }

    }

}