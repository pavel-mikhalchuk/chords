package com.prettybit.chords.entity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Pavel Mikhalchuk
 */
public class Keyboard extends View {

    private Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

    {
        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.BLACK);
        p.setTextSize(10);
    }

    private SongView songView;

    private List<Button> buttons = new LinkedList<Button>();

    public Keyboard(Context context, final SongView songView) {
        super(context);
        this.songView = songView;

        setBackgroundColor(Color.YELLOW);

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getActionMasked() == MotionEvent.ACTION_UP) {
                    String c = null;

                    for (Button button : buttons) {
                        if (button.contains((int) event.getX(), (int) event.getY())) {
                            c = button.text();
                        }
                    }

                    if (c != null) {
                        if (c.equals(">")) {

                        } else if (c.equals("<")) {

                        } else if (c.equals("^")) {
                            songView.getActiveItem().onMeasure(songView.getActiveItem().height() + 1);
                        } else if (c.equals("v")) {
                            songView.getActiveItem().onMeasure(songView.getActiveItem().height() - 1);
                        } else {
                            Chor item = new Chor(c);
                            item.onMeasure(20);

                            songView.getActive().addItem(item);
                        }
                        songView.invalidate();
                    }
                }

                return true;
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();

        buttons.clear();

        int x = 0;

        for (String c : Arrays.asList("A", "B", "C", ">", "<", "^", "v")) {
            buttons.add(new Button(new Rect(x, 0, x + 20, 20), c));

            canvas.drawRect(0, 0, 20, 20, p);
            canvas.drawText(c, 10, 10, p);
            canvas.translate(70, 0);

            x += 70;
        }

        canvas.restore();
    }

    private class Button {

        private Rect rect;
        private String text;

        private Button(Rect rect, String text) {
            this.rect = rect;
            this.text = text;
        }

        public boolean contains(int x, int y) {
            return rect.contains(x, y);
        }

        private String text() {
            return text;
        }

    }

}