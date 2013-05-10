package com.prettybit.chords;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.LinearLayout;
import com.prettybit.chords.entity.Chor;
import com.prettybit.chords.entity.Column;
import com.prettybit.chords.entity.SongPart;
import com.prettybit.chords.entity.SongView;
import com.prettybit.chords.entity.Span;

public class ChordsStage extends Activity {

    private int size = 80;

    private SongPart.View partView;

    private SongView songView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chords_stage);

        SongPart part = new SongPart();

        Column column = new Column();

        Span span = new Span();
        span.addItem(new Chor("D"));
        span.addItem(new Chor("|"));
        span.addItem(new Chor("C"));
        span.addItem(new Chor("|"));
        span.addItem(new Chor("G"));
        span.addItem(new Chor("|"));
        span.addItem(new Chor("A"));
        span.addItem(new Chor("C"));
//        span.addItem(new Repeat());
//        span.addItem(new Repeat());
//        span.addItem(new Repeat());
//        span.addItem(new Repeat());
//        span.addItem(new Repeat());

        column.addItem(span);
        column.addItem(span);
        column.addItem(span);

        span = new Span();
        span.addItem(new Chor("D"));
        span.addItem(new Chor("|"));
        span.addItem(new Chor("C"));
        span.addItem(new Chor("|"));
        span.addItem(new Chor("G"));
        span.addItem(new Chor("|"));
        span.addItem(new Chor("A"));
        span.addItem(new Chor("F"));
//        span.addItem(new Repeat());
//        span.addItem(new Repeat());
//        span.addItem(new Repeat());

        column.addItem(span);

        Span sss = new Span();

//        sss.addItem(new Repeat());
//        sss.addItem(new Repeat());
        sss.addItem(column);
//        sss.addItem(new Repeat());

        Column ccc = new Column();
        ccc.addItem(sss);


        column = new Column();

        span = new Span();
        span.addItem(new Chor("D"));
        span.addItem(new Chor("|"));
        span.addItem(new Chor("C"));
        span.addItem(new Chor("|"));
        span.addItem(new Chor("G"));
        span.addItem(new Chor("|"));
        span.addItem(new Chor("A"));
        span.addItem(new Chor("C"));

        column.addItem(span);
        column.addItem(span);
        column.addItem(span);

        span = new Span();
        span.addItem(new Chor("D"));
        span.addItem(new Chor("|"));
        span.addItem(new Chor("C"));
        span.addItem(new Chor("|"));
        span.addItem(new Chor("G"));
        span.addItem(new Chor("|"));
        span.addItem(new Chor("A"));
        span.addItem(new Chor("F"));

        column.addItem(span);

        sss = new Span();

        sss.addItem(column);
//        sss.addItem(new Repeat());

        ccc.addItem(sss);

        part.addItem(ccc);

        LinearLayout stage = (LinearLayout) findViewById(R.id.chords_stage);

//        partView = part.toView(this);
//        partView.setSize(size);

        songView = new SongView(this, part);
        songView.setSize(size);
        stage.addView(songView);
        stage.setOnTouchListener(new TouchListener(this));
    }

    private class TouchListener implements View.OnTouchListener {

        private ScaleGestureDetector d;

        private TouchListener(Context context) {
            d = new ScaleGestureDetector(context, new Scaler());
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return d.onTouchEvent(event);
        }

        private class Scaler extends ScaleGestureDetector.SimpleOnScaleGestureListener {

            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                int newSize = Math.max((int) (detector.getScaleFactor() * size), 15);
                if (newSize != size) {
                    songView.setSize(size = newSize);
                    songView.invalidate();
                }
                return true;
            }

        }

    }

}