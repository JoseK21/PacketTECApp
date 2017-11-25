package com.example.kenne.packettecapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Clase para observar el chat del usuario.
 *
 */
public class Chat extends AppCompatActivity {


    ListView list;
    String[] name = {"Pamela","Mario","Allison","Paulo","Carmen","Mery","Manfred","Wilson"};
    String[] messageShort ={"Hi :)","I thing that is a very option","NO, I don´t.","In the House. Why?","Hello ? How are you!","Mum please!","$20 ","I don´t know!"};
    Integer[] imgid={R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f,R.drawable.g,R.drawable.h,R.drawable.i,R.drawable.j,R.drawable.k,R.drawable.l,R.drawable.m,R.drawable.n,R.drawable.n2,R.drawable.o,R.drawable.p,R.drawable.q,R.drawable.r,R.drawable.s,R.drawable.t,R.drawable.u,R.drawable.v,R.drawable.w,R.drawable.x,R.drawable.y,R.drawable.z};
    HashMap<String, Integer> meMap = new HashMap<String, Integer>();
    private static TextView id;
    private static int identifier = 0;          // HAcerlo en el servidor para no reiniciar el id en cada dispositivo = 0
    private TextView t_1 ;


    /**
     * Creación de contactos y mensajes pre-existentes
     * @param savedInstanceState
     */
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
        t_1 = (TextView) findViewById(R.id.textID);
        CustomListview customListview=new CustomListview(this,name,messageShort,imgid,meMap);
        list.setAdapter(customListview);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    /**
     * Instanciamos del chat a la clase de los mensajes
     * @param view
     */
    public void writeMessage(View view){
        Intent i = new Intent(this, Message.class);
        startActivity(i);
    }

    /**
     * Conformación del ID
     * @param idFromServer
     */
    public void showID(String idFromServer){
        String t ="ID: "+idFromServer.toString();
        t_1.setText(t);
    }
}
