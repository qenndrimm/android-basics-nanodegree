package com.qenndrimm.android.musicalstructure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BluesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blues);

        Button backToCategories = (Button) findViewById(R.id.backToCategories);
        backToCategories.setOnClickListener(backToCategorieListener);

        TextView song1 = (TextView) findViewById(R.id.song1);
        TextView song2 = (TextView) findViewById(R.id.song2);
        TextView song3 = (TextView) findViewById(R.id.song3);

        song1.setOnClickListener(song1Listener);
        song2.setOnClickListener(song1Listener);
        song3.setOnClickListener(song1Listener);
    }

    private View.OnClickListener backToCategorieListener = new View.OnClickListener(){

        @Override
        public void onClick(View v){
            Intent backHome = new Intent(BluesActivity.this, MainActivity.class);
            startActivity(backHome);
        }
    };

    private View.OnClickListener song1Listener = new View.OnClickListener(){

        @Override
        public void onClick(View v){
            Intent songDetailsIntent = new Intent(BluesActivity.this, SongDetailsActivity.class);
            startActivity(songDetailsIntent);
        }
    };
}