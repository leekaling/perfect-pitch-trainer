package com.leekaling.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TuningResultActivity extends AppCompatActivity {

    private static int totalScore = 0;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuning_result);

        if (TuningGameActivity.round == 1) totalScore = 0;

        TextView countTextView = (TextView) findViewById(R.id.countTextView);
        countTextView.setText(TuningGameActivity.round + "");

        TextView scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        score = getIntent().getIntExtra("Score", 0);
        totalScore += score;
        scoreTextView.setText(score + "");


        findViewById(R.id.retryBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TuningGameActivity.round == 12) {
                    Intent gotoFinal = new Intent(getApplicationContext(), TuningFinalActivity.class);
                    gotoFinal.putExtra("avgScore", ((float) totalScore) / 12);
                    startActivity(gotoFinal);
                } else
                    startActivity(new Intent(getApplicationContext(), TuningGameActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
