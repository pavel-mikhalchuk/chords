package com.prettybit.chords;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import com.prettybit.chords.views.Chor;
import com.prettybit.chords.views.SongView;

/**
 * @author Pavel Mikhalchuk
 */
public class ChordsStage2 extends Activity {

    private float scale = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chords_stage_views);

        SongView stage = (SongView) findViewById(R.id.chords_stage_views);
        stage.setOnTouchListener(new TouchListener(this, stage));

        stage.addView(new Chor(this, "B"));
        stage.addView(new Chor(this, "A"));
        stage.addView(new Chor(this, "B"));
    }

    private class TouchListener implements View.OnTouchListener {

        private ScaleGestureDetector d;

        private TouchListener(Context context, SongView stage) {
            d = new ScaleGestureDetector(context, new Scaler(stage));
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return d.onTouchEvent(event);
        }

        private class Scaler extends ScaleGestureDetector.SimpleOnScaleGestureListener {

            private SongView stage;

            public Scaler(SongView stage) {
                this.stage = stage;
            }

            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                stage.setScale(scale *= detector.getScaleFactor());
                return true;
            }

        }

    }

}