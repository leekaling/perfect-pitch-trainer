package com.leekaling.test;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    static SoundPool soundPool;
    static int[] notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        findViewById(R.id.TuningChallengeBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSoundPool();
                loadSoundPool();
                TuningGameActivity.round = 0;
                Intent beginGameIntent = new Intent(getApplicationContext(),TuningGameActivity.class);
                startActivity(beginGameIntent);

            }
        });
    }

    private void createSoundPool(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(28)
                    .setAudioAttributes(audioAttributes)
                    .build();

        } else{
            soundPool = new SoundPool(28, AudioManager.STREAM_MUSIC,0);
        }

    }

    private void loadSoundPool(){
        notes = new int[28];
        notes[0] = soundPool.load(this,R.raw.e3,1);
        notes[1] = soundPool.load(this,R.raw.f3,1);
        notes[2] = soundPool.load(this,R.raw.fs3,1);
        notes[3] = soundPool.load(this,R.raw.g3,1);
        notes[4] = soundPool.load(this,R.raw.gs3,1);
        notes[5] = soundPool.load(this,R.raw.a3,1);
        notes[6] = soundPool.load(this,R.raw.as3,1);
        notes[7] = soundPool.load(this,R.raw.b3,1);
        notes[8] = soundPool.load(this,R.raw.c4,1);
        notes[9] = soundPool.load(this,R.raw.cs4,1);
        notes[10] = soundPool.load(this,R.raw.d4,1);
        notes[11] = soundPool.load(this,R.raw.ds4,1);
        notes[12] = soundPool.load(this,R.raw.e4,1);
        notes[13] = soundPool.load(this,R.raw.f4,1);
        notes[14] = soundPool.load(this,R.raw.fs4,1);
        notes[15] = soundPool.load(this,R.raw.g4,1);
        notes[16] = soundPool.load(this,R.raw.gs4,1);
        notes[17] = soundPool.load(this,R.raw.a4,1);
        notes[18] = soundPool.load(this,R.raw.as4,1);
        notes[19] = soundPool.load(this,R.raw.b4,1);
        notes[20] = soundPool.load(this,R.raw.c5,1);
        notes[21] = soundPool.load(this,R.raw.cs5,1);
        notes[22] = soundPool.load(this,R.raw.d5,1);
        notes[23] = soundPool.load(this,R.raw.ds5,1);
        notes[24] = soundPool.load(this,R.raw.e5,1);
        notes[25] = soundPool.load(this,R.raw.f5,1);
        notes[26] = soundPool.load(this,R.raw.fs5,1);
        notes[27] = soundPool.load(this,R.raw.g5,1);
    }
}
