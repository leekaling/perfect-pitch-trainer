package com.leekaling.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.*;
import java.lang.*;

public class TuningGameActivity extends AppCompatActivity {

    private int firstNoteIndex, currentNoteIndex;
    private float shift;

    static int round;

    private static int[] twelthNotes = {8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19};
    private int targetNoteIndex;

    private int targetProgress;
    private int score;

    private float best;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuning_game);

        if (round == 0) {
            twelthNotes = RandomizeArray(twelthNotes);
        }
        targetNoteIndex = twelthNotes[round];

        TextView countTextView = (TextView) findViewById(R.id.countTextView);
        countTextView.setText((round + 1) + "");

        final SeekBar tuningSeekBar = (SeekBar) findViewById(R.id.tuningSeekBar);
        tuningSeekBar.setProgress((int) (Math.random() * (tuningSeekBar.getMax() + 1)));

        setFirstNoteIndex();
        setNoteTextView();

        targetProgress = (targetNoteIndex - firstNoteIndex) * 10;

        final TextView temp = (TextView) findViewById(R.id.tempTextView); //debug

        calculateCurrentNoteIndex(tuningSeekBar.getProgress());
        calculateShift(tuningSeekBar.getProgress());

        tuningSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                temp.setText(firstNoteIndex + " " + targetNoteIndex + " " + targetProgress + " " + progress); //debug
                calculateCurrentNoteIndex(progress);
                calculateShift(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        findViewById(R.id.playBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuActivity.soundPool.autoPause();
                MenuActivity.soundPool.play(MenuActivity.notes[currentNoteIndex], 1, 1, 1, 0, shift);

            }
        });

        findViewById(R.id.retryBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuActivity.soundPool.autoPause();
                round++;
                int valueOff = Math.abs(targetProgress - tuningSeekBar.getProgress());
                score = 100 - valueOff;
                Intent gotoResult = new Intent(getApplicationContext(), TuningResultActivity.class);
                gotoResult.putExtra("Score", score);
                startActivity(gotoResult);

            }
        });


    }


    private void calculateCurrentNoteIndex(int progress) {
        currentNoteIndex = firstNoteIndex + progress / 10;
    }

    /*
     https://pages.mtu.edu/~suits/NoteFreqCalcs.html
     f(n) = f(0) * a^n
     a = the twelth root of 2
     n = the number of half steps away from f(0)

     shift = a^n
    */
    private void calculateShift(int progress) {
        double stepsOff = (double) (progress % 10) / 10;
        double a = Math.pow(2.0, 1.0 / 12.0);
        shift = (float) Math.pow(a, stepsOff);
    }

    //-2 to -8
    private void setFirstNoteIndex() {
        firstNoteIndex = targetNoteIndex - ((int) (Math.random() * 7) + 2);
    }

    private void setNoteTextView() {
        TextView noteTextView = (TextView) findViewById(R.id.noteTextView);
        switch (targetNoteIndex) {
            case 8:
                noteTextView.setText("C");
                break;
            case 9:
                noteTextView.setText("C#");
                break;
            case 10:
                noteTextView.setText("D");
                break;
            case 11:
                noteTextView.setText("D#");
                break;
            case 12:
                noteTextView.setText("E");
                break;
            case 13:
                noteTextView.setText("F");
                break;
            case 14:
                noteTextView.setText("F#");
                break;
            case 15:
                noteTextView.setText("G");
                break;
            case 16:
                noteTextView.setText("G#");
                break;
            case 17:
                noteTextView.setText("A");
                break;
            case 18:
                noteTextView.setText("A#");
                break;
            case 19:
                noteTextView.setText("B");
                break;
        }
    }

    private int[] RandomizeArray(int[] array) {
        Random rgen = new Random();  // Random number generator

        for (int i = 0; i < array.length; i++) {
            int randomPosition = rgen.nextInt(array.length);
            int temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }

        return array;
    }


    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

}


