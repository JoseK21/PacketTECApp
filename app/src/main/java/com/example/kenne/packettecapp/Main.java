package com.example.kenne.packettecapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.simple.JSONObject;

import java.io.File;

/**
 * Clase Principal
 */
public class Main extends AppCompatActivity {
    private static String ip = "192.168.100.11";


    private Button b_LogIn;
    private TextInputEditText textViewUserName_Main;
    private EditText editTextPassword_Main;
    private static String path;
    private static File dir;
    private static String iD;

    /**
     * Asigna la ventana/actividad (activity_main) y un Toolbar "Contenedor de opciones principales"
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/InfoPacketTECApp";
        dir = new File(path);
        checkNetworkConnection();
    }

    public static String getiD(){
        return iD;
    }


    /**
     * Metódo para obtener información de la conección
     * @return
     */
    public boolean checkNetworkConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        boolean isConnected=false;

        if (networkInfo !=null && (isConnected=networkInfo.isConnected())){
            Toast.makeText(getApplicationContext(), "Connected -> "+networkInfo.getTypeName(), Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(getApplicationContext(), "Not Connected", Toast.LENGTH_SHORT).show();

        }
        return isConnected;
    }


    /**
     * Asigna el menu (menu - menu_main) a esta actividad(Ventana)     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Opciones principales (Salir(Exit) y Crear una cuenta(Create account)     *
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
            Intent i = new Intent(this, Registry.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Metodo que permite acceder al chat del usuario luego de Logiarse en el sistema     *
     * @param view
     */
    public void logIn(View view) {
        textViewUserName_Main = (TextInputEditText) findViewById(R.id.textViewUserName_Main);
        editTextPassword_Main = (EditText) findViewById(R.id.editTextPassword_Main);

        final String tUserName = textViewUserName_Main.getText().toString();
        final String tPassword = editTextPassword_Main.getText().toString();

        if (tUserName.trim().equals("") && tPassword.trim().equals("")) {
            Snackbar.make(view, "", Snackbar.LENGTH_SHORT).setText("Warning! -> User Name and Password empty").show();
        } else if (tUserName.trim().equals("")) {
            Snackbar.make(view, "", Snackbar.LENGTH_SHORT).setText("Warning! -> User Name empty").show();
        } else if (tPassword.trim().equals("")) {
            Snackbar.make(view, "", Snackbar.LENGTH_SHORT).setText("Warning! -> Password empty").show();

        } else {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("passW", tPassword);
            jsonObject.put("userN", tUserName);
            postInfo(jsonObject);
        }
    }

    /**
     * Metódo que obtiene el ip(URL)
     * @return
     */
    public String getURL(){
        return (String)ip;
    }

    public void postInfo(JSONObject nombre){
        postInfo_Aux(nombre);
    }


    private void postInfo_Aux(final JSONObject response){
        Main main = new Main();
        String ruta = main.getURL();
        Log.d("RUTA",ruta);

        final String url = "http://"+ruta+":8080/PacketTEC/api/movil/inicio";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url, new org.json.JSONObject(response),
                new Response.Listener<org.json.JSONObject>() {
                    @Override
                    public void onResponse(org.json.JSONObject response) {
                        String msg = null;
                        String msgName = null;
                        try {
                            msg = (String) response.get("msg");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (msg.equals("ok"))
                            try {

                                iD = (String) response.get("id");
                                msgName = (String) response.get("nombre");
                                Log.d(".....",iD+"-"+msgName);
                                openChat(msgName,iD);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        else
                        if (msg.equals("no ok")){
                            Toast.makeText(getApplicationContext(), "This account is not created", Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("event","Error "+error
                        +"\nmessage"+error.getMessage());
            }
        }
        );

        requestQueue.add(postRequest);

    }

    public void openChat(String n,String id){
        //-----------------
        Toast.makeText(getApplicationContext(), "Welcome "+n, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this,Chat.class);
        startActivity(i);



    }

}