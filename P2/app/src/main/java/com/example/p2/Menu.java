package com.example.p2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity implements View.OnClickListener{

    public Button cButton;

    @Override protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        cButton = (Button) findViewById(R.id.button5);
        cButton.setOnClickListener(this);
    }

    @Override public void onClick(View v){

        Intent intent = new Intent(Menu.this, Opciones.class);
        startActivity(intent);

    }
}
