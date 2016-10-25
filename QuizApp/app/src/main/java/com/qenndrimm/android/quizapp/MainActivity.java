package com.qenndrimm.android.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public boolean answers[] = {false, false, false, false, false};
    int numratori = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question1);
    }

    public void checkAnswer1(View v){
        EditText answer1 = (EditText) findViewById(R.id.answer1);
        if(answer1.getText().toString().equals("prishtina")){
            answers[0]= true;
        }

        setContentView(R.layout.question2);
    }

    public void checkAnswer2(View v){
        CheckBox ans1 = (CheckBox) findViewById(R.id.ans1);
        CheckBox ans2 = (CheckBox) findViewById(R.id.ans2);
        CheckBox ans3 = (CheckBox) findViewById(R.id.ans3);
        CheckBox ans4 = (CheckBox) findViewById(R.id.ans4);

        if(ans1.isChecked() && ans2.isChecked() && !ans3.isChecked() && ans4.isChecked()){
            answers[1] = true;
        }

        setContentView(R.layout.question3);
    }

    public void checkAnswer3(View v){
        RadioGroup answer3 = (RadioGroup) findViewById(R.id.question3);

        if(answer3.getCheckedRadioButtonId() == R.id.ques3ans2){
            answers[2] = true;
        }

        setContentView(R.layout.question4);
    }

    public void checkAnswer4(View v){
        RadioGroup answer4 = (RadioGroup) findViewById(R.id.question4);

        if(answer4.getCheckedRadioButtonId() == R.id.ques4ans3){
            answers[3] = true;
        }

        setContentView(R.layout.question5);
    }

    public void checkAnswer5(View v){
        RadioGroup answer5 = (RadioGroup) findViewById(R.id.question5);

        if(answer5.getCheckedRadioButtonId() == R.id.ques5ans4){
            answers[4] = true;
        }

        for (boolean answer : answers) {
            if (answer) {
                numratori++;
            }
        }

        setContentView(R.layout.results);

        TextView result = (TextView) findViewById(R.id.result);
        result.setText(String.valueOf(numratori) + " / " + answers.length);
    }
}