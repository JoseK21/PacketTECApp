package com.example.kenne.packettecapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


/**
 * Clasa Principal
 */
public class Main extends AppCompatActivity {

    private Button b_LogIn;
    private TextInputEditText textViewUserName_Main;
    private EditText editTextPassword_Main;

    /**
     * Asigna la ventana/actividad (activity_main) y un Toolbar "Contenedor de opciones principales"
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //HttpClient httpClient = new DefaultHttpClient();                  !!! HAY QUE IMPORTA APACHE PARA LOS IMPORT DEL INICIO !!!
       // HttpPost httpPost =new HttpPost(URL DEL SERVIDOR);

        }

    /**
     * Asigna el menu (menu - menu_main) a esta actividad(Ventana)
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Opciones principales (Salir(Exit) y Crear una cuenta(Create account)
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_exit) {
            System.exit(0);
            return true;
        }
        if (id == R.id.action_create_account) {
            this.finish();
            Intent i = new Intent(this, Registry.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Metodo que permite acceder al chat del usuario luego de Logiarse en el sistema
     * @param view
     */
    public void logIn(View view) {
        textViewUserName_Main = (TextInputEditText) findViewById(R.id.textViewUserName_Main);
        editTextPassword_Main = (EditText) findViewById(R.id.editTextPassword_Main);

        String tUserName = textViewUserName_Main.getText().toString();
        String tPassword = editTextPassword_Main.getText().toString();

        if (tUserName.trim().equals("") && tPassword.trim().equals("")) {
            Snackbar.make(view, "", Snackbar.LENGTH_SHORT).setText("Warning! -> User Name and Password empty").show();
        }
        else if (tUserName.trim().equals("")) {
            Snackbar.make(view, "", Snackbar.LENGTH_SHORT).setText("Warning! -> User Name empty").show();
        }
        else if (tPassword.trim().equals("")) {
            Snackbar.make(view, "", Snackbar.LENGTH_SHORT).setText("Warning! -> Password empty").show();
        }
        else {
            this.finish();
            Intent i = new Intent(this, Chat.class);
            startActivity(i);
        }
    }
}
