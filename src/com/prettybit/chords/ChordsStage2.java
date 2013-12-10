package com.prettybit.chords;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.prettybit.chords.views.Chor;

/**
 * @author Pavel Mikhalchuk
 */
public class ChordsStage2 extends Activity {

    public static final String STAGE_PREFS = "CHORDS_STAGE_PREFERENCES";
    public static final String SCALE_PREF = "SCALE";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chords_stage_views);

        RelativeLayout stage = (RelativeLayout) findViewById(R.id.chords_stage_views);
        stage.setOnTouchListener(new TouchListener(this, stage));

        LinearLayout part = new LinearLayout(this);
        part.addView(new Chor(this, "B"));
        part.addView(new Chor(this, "A"));
        part.addView(new Chor(this, "B"));
        stage.addView(part);
    }

    private class TouchListener implements View.OnTouchListener {

        private ScaleGestureDetector d;

        private TouchListener(Context context, RelativeLayout stage) {
            d = new ScaleGestureDetector(context, new Scaler(stage));
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return d.onTouchEvent(event);
        }

        private class Scaler extends ScaleGestureDetector.SimpleOnScaleGestureListener {

            private RelativeLayout stage;

            public Scaler(RelativeLayout stage) {
                this.stage = stage;
            }

            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                saveScale(detector.getScaleFactor());
                stage.invalidate();
                return true;
            }

            private void saveScale(float scaleFactor) {
                SharedPreferences sp = getSharedPreferences(STAGE_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor e = sp.edit();
                e.putFloat(SCALE_PREF, sp.getFloat(SCALE_PREF, 1) * scaleFactor);
                e.commit();
            }

        }

    }

}