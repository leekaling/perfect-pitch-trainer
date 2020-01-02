package com.leekaling.test;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TuningFinalActivity extends AppCompatActivity {

    private float finalScore;
    private float best;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuning_final);

        finalScore = getIntent().getFloatExtra("avgScore", 0f);
        String finalScoreString = String.format("%.2f", finalScore);
        TextView scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        scoreTextView.setText(finalScoreString);

        setBest();
        String bestString = String.format("%.2f", best);
        TextView bestTextView = (TextView) findViewById(R.id.bestTextView);
        bestTextView.setText(bestString);


        findViewById(R.id.menuBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MenuActivity.class));
            }
        });

        findViewById(R.id.retryBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TuningGameActivity.round = 0;
                startActivity(new Intent(getApplicationContext(), TuningGameActivity.class));
            }
        });
    }

    private void setBest() {
        SharedPreferences settings = getSharedPreferences("GameData", Context.MODE_PRIVATE);
        best = settings.getFloat("HighScore", 0);

        if (finalScore > best) {
            best = finalScore;

            //save
            SharedPreferences.Editor editor = settings.edit();
            editor.putFloat("HighScore", best);
            editor.commit();

        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
