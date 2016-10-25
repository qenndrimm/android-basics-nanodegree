package com.qenndrimm.android.musicalstructure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ClassicalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classical);

        Button backToCategories = (Button) findViewById(R.id.backToCategories);
        backToCategories.setOnClickListener(backToCategorieListener);
    }

    private View.OnClickListener backToCategorieListener = new View.OnClickListener(){

        @Override
        public void onClick(View v){
            Intent backHome = new Intent(ClassicalActivity.this, MainActivity.class);
            startActivity(backHome);
        }
    };
}
