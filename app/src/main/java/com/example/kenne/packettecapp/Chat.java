package com.example.kenne.packettecapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Chat extends AppCompatActivity {


    ListView list;
    String[] name = {"Jose","Mario","Carmen","Paulo","Ana","Silvia"};
    String[] messageShort ={"This is Jose","This is Mario","This is Carmen","This is Paulo from the Flower of Paraiso , Cartago","This is Ana","This is Silvia"};
    Integer[] imgid={R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f,R.drawable.g,R.drawable.h,R.drawable.i,R.drawable.j,R.drawable.k,R.drawable.l,R.drawable.m,R.drawable.n,R.drawable.n2,R.drawable.o,R.drawable.p,R.drawable.q,R.drawable.r,R.drawable.s,R.drawable.t,R.drawable.u,R.drawable.v,R.drawable.w,R.drawable.x,R.drawable.y,R.drawable.z};
    HashMap<String, Integer> meMap = new HashMap<String, Integer>();
    private static TextView id;
    private static int identifier = 0;          // HAcerlo en el servidor para no reiniciar el id en cada dispositivo = 0



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        meMap.put("a", 1);  meMap.put("f", 6);  meMap.put("k", 11);  meMap.put("o", 16); meMap.put("t", 21); meMap.put("y", 26);
        meMap.put("b", 2);  meMap.put("g", 7);  meMap.put("l", 12);  meMap.put("p", 17); meMap.put("u", 22); meMap.put("z", 27);
        meMap.put("c", 3);  meMap.put("h", 8);  meMap.put("m", 13);  meMap.put("q", 18); meMap.put("v", 23);
        meMap.put("d", 4);  meMap.put("i", 9);  meMap.put("n", 14);  meMap.put("r", 19); meMap.put("w", 24);
        meMap.put("e", 5);  meMap.put("j", 10); meMap.put("ñ", 15);  meMap.put("s", 20); meMap.put("x", 25);


        list = (ListView)findViewById(R.id.listViewMessage);
        CustomListview customListview=new CustomListview(this,name,messageShort,imgid,meMap);
        list.setAdapter(customListview);

        id = (TextView)findViewById(R.id.idxxxx);

        id.setText("ID : "+identifier);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void writeMessage(View view){
        System.out.print("The User go to Write a Message..................................!");
       // Intent i = new Intent(this, Chat.class);
        //startActivity(i);
    }
}
