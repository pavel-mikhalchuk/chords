package com.prettybit.chords;

import android.app.Activity;
import android.os.Bundle;
import com.prettybit.chords.entity.Chor;
import com.prettybit.chords.entity.SongPart;
import com.prettybit.chords.entity.SongView;

public class ChordsStage extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chords_stage);

        final SongView song = (SongView) findViewById(R.id.song);
        SongPart part = new SongPart();
        part.addItem(new Chor("B"));
        part.addItem(new Chor("A"));
        part.addItem(new Chor("B"));
        song.addPart(part);

/*        KeyboardView keyboard = (KeyboardView) findViewById(R.id.keyboard);

        keyboard.setOnKeyboardActionListener(new KeyboardView.OnKeyboardActionListener() {
            @Override
            public void onPress(int primaryCode) {
                Log.i(ChordsStage.class.toString(), "Press " + primaryCode + "!!!");
            }

            @Override
            public void onRelease(int primaryCode) {
                Log.i(ChordsStage.class.toString(), "Release " + primaryCode + "!!!");

                Chor c = new Chor(String.valueOf((char) primaryCode));
                c.onMeasure(20);

                song.getActive().addItem(c);

                song.invalidate();
            }

            @Override
            public void onKey(int primaryCode, int[] keyCodes) {
                Log.i(ChordsStage.class.toString(), "Key " + primaryCode + "_" + keyCodes + "!!!");
            }

            @Override
            public void onText(CharSequence text) {
                Log.i(ChordsStage.class.toString(), "Text " + text + "!!!");
            }

            @Override
            public void swipeLeft() {
                Log.i(ChordsStage.class.toString(), "Swipe Left!!!");
            }

            @Override
            public void swipeRight() {
                Log.i(ChordsStage.class.toString(), "Swipe Right!!!");
            }

            @Override
            public void swipeDown() {
                Log.i(ChordsStage.class.toString(), "Swipe Down!!!");
            }

            @Override
            public void swipeUp() {
                Log.i(ChordsStage.class.toString(), "Swipe Up!!!");
            }
        });
        keyboard.setKeyboard(new Keyboard(this, R.xml.keyboard));*/
    }

}