package com.example.kenne.packettecapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Registry extends AppCompatActivity {

    private static Button b_1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }
    public void goChat_Account(View view){
        Intent i = new Intent(this, Chat.class);
        startActivity(i);
        System.out.print("HELLO CHAT");
    }

    public void check(View view){
        b_1 = (Button) findViewById(R.id.button4);
        b_1.setEnabled(true);
    }

}
