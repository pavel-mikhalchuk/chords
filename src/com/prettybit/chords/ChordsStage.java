package com.prettybit.chords;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.widget.RelativeLayout;
import com.prettybit.chords.entity.Bar;
import com.prettybit.chords.entity.Chor;
import com.prettybit.chords.entity.Keyboard;
import com.prettybit.chords.entity.Line;
import com.prettybit.chords.entity.Repeat;
import com.prettybit.chords.entity.SongPart;
import com.prettybit.chords.entity.SongView;
import com.prettybit.chords.entity.Tab;
import com.prettybit.chords.entity.Title;
import com.prettybit.chords.entity.Verse;

import java.util.LinkedList;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class ChordsStage extends Activity {

    private SongView songView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chords_stage);

        RelativeLayout stage = (RelativeLayout) findViewById(R.id.chords_stage);

        songView = new SongView(this);

        Point display = new Point();
        getWindowManager().getDefaultDisplay().getSize(display);

        SongPart part = new SongPart(20);
        part.addItem(new Chor("Y"));
        part.init(display.x, display.y);
        songView.addPart(part);

//        songView.addParts(getParts());

        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        p.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        stage.addView(songView, p);

        p = new RelativeLayout.LayoutParams(MATCH_PARENT, 100);
        p.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        stage.addView(new Keyboard(this, songView), p);
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

}