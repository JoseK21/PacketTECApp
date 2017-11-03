package com.example.kenne.packettecapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Registry extends AppCompatActivity {
    private static int id  = 0; // Subir al Server para que de hay nos proporcione un id (sumando cada ves en una unidad)
    private static Button b_1 ;
    private static TextView t_1 ;
    private static TextView t_2 ;
    private static TextInputEditText tIeTName;
    private static TextInputEditText tIeTLastName;
    private static TextInputEditText tIeTUserName;


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
        tIeTName = (TextInputEditText)findViewById(R.id.tiName);
        String n = tIeTName.getText().toString();
        tIeTLastName = (TextInputEditText)findViewById(R.id.tiLastName);
        String ln = tIeTLastName.getText().toString();
        tIeTUserName = (TextInputEditText)findViewById(R.id.tiUserName);
        String un = tIeTUserName.getText().toString();


        t_1 =(TextView)findViewById(R.id.tVMessageRegistry);
        t_2 =(TextView)findViewById(R.id.tVUserNameTrueFalse);
        if (n.isEmpty() || ln.isEmpty() || un.isEmpty()){
            t_1.setText("One o more option without text.  !Empty!");

        }else{

           // if (ListaUserNameServer.containt(un)){                                // quitar la comentación Cuandp se tenga el servidor y pueda pasar estos parametros
                //t_2.setText("User Name Exists °Try with other User Name");        }
            //else{
            t_1.setText("Succesfull");
            b_1 = (Button) findViewById(R.id.button4);
            b_1.setEnabled(true);       //}
        }

    }

}
