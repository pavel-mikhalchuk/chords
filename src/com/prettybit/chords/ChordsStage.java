package com.prettybit.chords;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.LinearLayout;
import com.prettybit.chords.entity.Bar;
import com.prettybit.chords.entity.Chor;
import com.prettybit.chords.entity.Line;
import com.prettybit.chords.entity.Repeat;
import com.prettybit.chords.entity.SongPart;
import com.prettybit.chords.entity.SongView;
import com.prettybit.chords.entity.Tab;
import com.prettybit.chords.entity.Title;
import com.prettybit.chords.entity.Verse;

import java.util.LinkedList;
import java.util.List;

public class ChordsStage extends Activity {

    private SongView songView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chords_stage);

        LinearLayout stage = (LinearLayout) findViewById(R.id.chords_stage);

        songView = new SongView(this);
        songView.addParts(getParts());

        stage.addView(songView);
        stage.setOnTouchListener(new TouchListener(this));
    }

    private List<SongPart> getParts() {
        LinkedList<SongPart> parts = new LinkedList<SongPart>();

        SongPart p = new SongPart(17);
        p.addItem(new Title("Shadows of the Night"));

        parts.add(p);

        p = new SongPart(40);
        p.addItem(new Line("Intro: Voc = "));
        p.addItem(new Chor("E", true));
        p.addItem(new Bar());
        p.addItem(new Chor("A", true));
        p.addItem(new Repeat());

        parts.add(p);

        p = new SongPart(40);
        p.addItem(new Verse(1));
        p.addItem(new Chor("E", true));
        p.addItem(new Bar());
        p.addItem(new Chor("A", true));
        p.addItem(new Repeat());
        p.addItem(new Chor("C"));
        p.addItem(new Bar());
        p.addItem(new Chor("B", true));
        p.addItem(new Bar());
        p.addItem(new Chor("F"));
        p.addItem(new Chor("G"));
        p.addItem(new Bar());
        p.addItem(new Tab());
        p.addItem(new Bar());

        parts.add(p);

        return parts;
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
                songView.setScale(detector.getScaleFactor());
                songView.invalidate();
                return true;
            }

        }

    }

}