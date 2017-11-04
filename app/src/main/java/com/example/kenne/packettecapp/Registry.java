package com.example.kenne.packettecapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Registry extends AppCompatActivity {
    private int id  = 0; // Subir al Server para que de hay nos proporcione un id (sumando cada ves en una unidad)
    private Button b_1 ;
    private TextView t_1 ;
    private TextView t_2 ;
    private TextInputEditText tIeTFullName;
    private TextInputEditText tIeTUserName;
    private TextInputEditText tIeTPassword;
    private Button fab;
    private String fn;
    private String un;
    private String passW;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }
    public void signIn(View view){
        tIeTFullName = (TextInputEditText)findViewById(R.id.tiFullName);
        fn= tIeTFullName.getText().toString();
        Log.d("Full Name", fn);

        tIeTUserName = (TextInputEditText)findViewById(R.id.tiUserName);
        un = tIeTUserName.getText().toString();
        Log.d("User Name", un);

        tIeTPassword = (TextInputEditText)findViewById(R.id.tiPassword);
        passW = tIeTPassword.getText().toString();
        Log.d("Password", passW);

        fab= (Button) findViewById(R.id.b_SignIn); // Creo que no lo nesesito(Quitar)

        if (fn.trim().equals("")  && un.trim().equals("") && passW.trim().equals("") ) {
            Snackbar.make(view, "", Snackbar.LENGTH_LONG).setText("Warning!  Full Name, User Name and Password empty").show();
        }
        else if (fn.trim().equals("")  && un.trim().equals("") ) {
            Snackbar.make(view, "", Snackbar.LENGTH_LONG).setText("Warning!  Full Name and User Name empty").show();
        }
        else if(fn.trim().equals("")  && passW.trim().equals("")){
            Snackbar.make(view, "", Snackbar.LENGTH_LONG).setText("Warning!  Full Name and Password empty").show();
        }
        else if(un.trim().equals("")  && passW.trim().equals("")){
            Snackbar.make(view, "", Snackbar.LENGTH_LONG).setText("Warning!  User Name and Password empty").show();
        }
        else if(fn.trim().equals("")){
            Snackbar.make(view, "", Snackbar.LENGTH_LONG).setText("Warning!  Full Name empty").show();
        }
        else if(un.trim().equals("")){
            Snackbar.make(view, "", Snackbar.LENGTH_LONG).setText("Warning! User Name empty").show();
        }
        else if(passW.trim().equals("")){
            Snackbar.make(view, "", Snackbar.LENGTH_LONG).setText("Warning! Password empty").show();
        }
        else{
            openChat();
        }
    }
    public void openChat(){
        Intent i = new Intent(this, Chat.class);
        startActivity(i);
    }

}
