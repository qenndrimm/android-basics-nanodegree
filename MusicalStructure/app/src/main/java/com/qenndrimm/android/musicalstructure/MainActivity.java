package com.qenndrimm.android.musicalstructure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtBlues = (TextView) findViewById(R.id.txtBlues);
        TextView txtClassical = (TextView) findViewById(R.id.txtClassical);
        TextView txtCountry = (TextView) findViewById(R.id.txtCountry);
        TextView txtPopRock = (TextView) findViewById(R.id.txtPopRock);

        txtBlues.setOnClickListener(bluesListener);
        txtClassical.setOnClickListener(classicalListener);
        txtCountry.setOnClickListener(countryListener);
        txtPopRock.setOnClickListener(popRockListener);
    }

    private View.OnClickListener bluesListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent intentBlues = new Intent(MainActivity.this, BluesActivity.class);
            startActivity(intentBlues);
        }
    };

    private View.OnClickListener classicalListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent intentClassical = new Intent(MainActivity.this, ClassicalActivity.class);
            startActivity(intentClassical);
        }
    };

    private View.OnClickListener countryListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent intentCountry = new Intent(MainActivity.this, CountryActivity.class);
            startActivity(intentCountry);
        }
    };

    private View.OnClickListener popRockListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent intentPopRock = new Intent(MainActivity.this, PopRockActivity.class);
            startActivity(intentPopRock);
        }
    };
}