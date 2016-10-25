package com.qenndrimm.court.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public TextView scoreTeamA;
    public TextView scoreTeamB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreTeamA = (TextView) findViewById(R.id.txtscore1);
        scoreTeamB = (TextView) findViewById(R.id.txtscore2);
    }

    public void resetScore(View v) {
        scoreTeamA.setText("0");
        scoreTeamA.setText("0");
    }

    public void teamAplus1(View v) {
        pointsAdder(1, scoreTeamA);
    }

    public void teamAplus2(View v) {
        pointsAdder(2, scoreTeamA);
    }

    public void teamAplus3(View v) {
        pointsAdder(3, scoreTeamA);
    }

    public void teamBplus1(View v) {
        pointsAdder(1, scoreTeamB);
    }

    public void teamBplus2(View v) {
        pointsAdder(2, scoreTeamB);
    }

    public void teamBplus3(View v) {
        pointsAdder(3, scoreTeamB);
    }

    public void pointsAdder(int b, TextView c) {
        int a = Integer.parseInt(c.getText().toString());
        c.setText(String.valueOf(a + b));
    }
}
