package com.example.kenne.packettecapp;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Message extends AppCompatActivity {
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        spinner = (Spinner) findViewById(R.id.spinner1);

        ArrayList list = new ArrayList<>();
        list.add("");
        list.add("User_1");
        list.add("User_2");
        list.add("User_3");
        list.add("User_4");
        list.add("User_1");
        list.add("User_2");
        list.add("User_3");
        list.add("User_4");
        list.add("User_1");
        list.add("User_2");
        list.add("User_3");
        list.add("User_4");
        list.add("User_1");
        list.add("User_2");
        list.add("User_3");
        list.add("User_4");
        list.add("User_1");
        list.add("User_2");
        list.add("User_3");
        list.add("User_4");
        list.add("User_1");
        list.add("User_2");
        list.add("User_3");
        list.add("User_4");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



        spinner.setAdapter(adapter);


    }

    public void sendMessage(View view){
        Snackbar.make(view, "", Snackbar.LENGTH_LONG).setText("----->  Send Message in process").show();
    }
    public void addImage(View view){
        Snackbar.make(view, "", Snackbar.LENGTH_LONG).setText("----->  Add Image in process").show();
    }
    public void addVideo(View view){
        Snackbar.make(view, "", Snackbar.LENGTH_LONG).setText("----->  Add Video in process").show();
    }
    public void addDocument(View view){
        Snackbar.make(view, "", Snackbar.LENGTH_LONG).setText("-----> Add Document in process").show();
    }

    public void loadUser(String[] listUser) {
            // enviar una lista de todos los usuario ingresados desde el servidor

    }
}
