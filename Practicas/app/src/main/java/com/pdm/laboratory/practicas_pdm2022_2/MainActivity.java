package com.pdm.laboratory.practicas_pdm2022_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnHelloW;
    private Button btnQuestionary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnHelloW = (Button) findViewById(R.id.btnHW);
        btnQuestionary = (Button) findViewById(R.id.btnQuestionary);

        btnHelloW.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent goHelloW = new Intent(MainActivity.this, HelloWorld.class);
                startActivity(goHelloW);
            }
        });

        btnQuestionary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goQuestionary = new Intent(MainActivity.this, Questionary.class);
                startActivity(goQuestionary);
            }
        });
    }




}