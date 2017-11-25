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
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase Principal
 */
public class Main extends AppCompatActivity {

    final String url = "http://"+getURL()+":8080/PacketTEC/api/movil/put";

    private Button b_LogIn;
    private TextInputEditText textViewUserName_Main;
    private EditText editTextPassword_Main;

    private static String path;
    private static File dir;

    private static String ip = "192.168.1.13";

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

        /*
        @SuppressLint("WifiManagerLeak") WifiManager wifiMgr = (WifiManager) getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
        int ipbd = wifiInfo.getIpAddress();
        ip = ipbd;

        String ipAddress = Formatter.formatIpAddress(ip);
        Log.d("Your Host addr: " , "-- : "+ipAddress);  // often returns "127.0.0.1"

*/

        path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/InfoPacketTECApp";
        dir = new File(path);
        checkNetworkConnection();
        updateContact();
       // updateContact("1","2","3");
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

        String tUserName = textViewUserName_Main.getText().toString();
        String tPassword = editTextPassword_Main.getText().toString();

        if (tUserName.trim().equals("") && tPassword.trim().equals("")) {
            Snackbar.make(view, "", Snackbar.LENGTH_SHORT).setText("Warning! -> User Name and Password empty").show();
        } else if (tUserName.trim().equals("")) {
            Snackbar.make(view, "", Snackbar.LENGTH_SHORT).setText("Warning! -> User Name empty").show();
        } else if (tPassword.trim().equals("")) {
            Snackbar.make(view, "", Snackbar.LENGTH_SHORT).setText("Warning! -> Password empty").show();


        } else {
            ReadInfoJSON(tUserName,tPassword);
            // ---------------------------------------------


        }
    }

    /**
     * Lector del contenido de carpetas
     * @param User
     * @param Pass
     */
    public void ReadInfoJSON(String User,String Pass){
        JSONParser parser = new JSONParser();
        try {
            String rootInfoJSON = Environment.getExternalStorageDirectory().getAbsolutePath() + "/InfoPacketTECApp/infoJSON.json";
            Object obj = null;
            obj = parser.parse(new FileReader(rootInfoJSON));

            JSONObject jsonObject = (JSONObject) obj;
            String Name =(String) jsonObject.get("USERNAME");
            String PassWord =(String) jsonObject.get("PASSWORD");

            if(User.equals(Name) && Pass.equals(PassWord)){
                this.finish();
                Intent i = new Intent(this, Chat.class);
                startActivity(i);
            }
            else{
                textViewUserName_Main = (TextInputEditText) findViewById(R.id.textViewUserName_Main);
                editTextPassword_Main = (EditText) findViewById(R.id.editTextPassword_Main);
                textViewUserName_Main.setText("");
                editTextPassword_Main.setText("");

                Toast.makeText(getApplicationContext(), "Fault in the account start\nPlease write again", Toast.LENGTH_SHORT).show();
            }



        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public String getURL(){
        Log.d("Your Host addr: " , "-- : "+ip);  // often returns "127.0.0.1"
        return ""+ip;
    }

    public void updateContact() {
        final String url = getURL();
        //final String url = main.getURL()+"contactos/getAll";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        org.json.JSONObject jsonObject = (org.json.JSONObject) response.get(i);
                        jsonObject.has("contrasenna");

                        String f = jsonObject.getString("nombreUsuario");
                        String n = jsonObject.getString("nombre");
                        String p = jsonObject.getString("contrasenna");

                        Log.d("--FullName-- >", f);
                        Log.d("--Name-- >", n);
                        Log.d("--Password-- >", p);

                       // listContact.addFirst(a);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
               // fill(listContact);


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Volley Log",error);

            }
        });

        requestQueue.add(jsonArrayRequest);
        Toast.makeText(getApplicationContext(),"Data Loader Successefully",Toast.LENGTH_SHORT).show();
    }

    /*
    public void updateContact(final String fName,final String uName,final String pWord) {
        Main main = new Main();
        String ruta =main.getURL();
        Log.d("RUTA -- >  ",ruta);

        final String url = main.getURL()+"contactos/getAll";
        Log.d("URL -- >  ",url);
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {

                        org.json.JSONObject jsonObject = (org.json.JSONObject) response.get(i);
                        String f = jsonObject.getString("nombreUsuario");
                        String u = jsonObject.getString("nombre");
                        String p = jsonObject.getString("contrasena");

                        Log.d("Nombre de Usuario ",f);
                        Log.d("Nombre de Usuario",u);
                        Log.d("Nombre de Usuario",p);
/*
                        if (fName.equals(f)||uName.equals(u) && pWord.equals(p)){
                            Toast.makeText(getApplicationContext(),"Log in",Toast.LENGTH_SHORT).show();
                            break;
                            //returnMain();
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Volley Log",error);

            }
        });

        requestQueue.add(jsonArrayRequest);
      //  Toast.makeText(getApplicationContext(),"Account Create",Toast.LENGTH_SHORT).show();
    }
    */

}
