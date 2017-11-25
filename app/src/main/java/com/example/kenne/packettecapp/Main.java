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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

        path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/InfoPacketTECApp";
        dir = new File(path);

        createFiles();

        checkNetworkConnection();
        updateContact();
         }


    public void createFiles(){
        try {
            path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/InfoPacketTECApp";
            dir = new File(path);
            dir.mkdirs();


            FileWriter fileSplayTreeJSON = new FileWriter(path + "/SplayTreeJSON.json");
            fileSplayTreeJSON.flush();
            FileWriter AVLTreeJSON = new FileWriter(path + "/AVLTreeJSON.json");
            AVLTreeJSON.flush();
            FileWriter BinarySearchTreeTreeJSON = new FileWriter(path + "/BinarySearchTreeTreeJSON.json");
            BinarySearchTreeTreeJSON.flush();
            FileWriter BTreeJSON = new FileWriter(path + "/BTreeJSON.json");
            BTreeJSON.flush();

            Toast.makeText(getApplicationContext(), "Successful_LogIn ", Toast.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(), "File Create.( Fullname: "+fullname+ " Username: "+username+" Password: "+password+" )", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
        }
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
            //volleyCall(tUserName,tPassword);
            //ReadInfoJSON(tUserName,tPassword);
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
        final String url = "http://"+ip+":8080/PacketTEC/api/movil";
        //final String url = getURL()+"contactos/getAll";


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

    private void volleyCall(String name,String password){
        RequestQueue queue = Volley.newRequestQueue(this);
        //final String url = "http://"+ip+":8080/PacketTEC/api/movil";
        String URL = "http://"+ip+":8080/PacketTEC/api/movil/put";
        Map<String,String> jsonParams = new HashMap<String, String>();

        jsonParams.put("nombre",name);
        jsonParams.put("contrasenna",password);

        Log.d("","Json:"+new JSONObject(jsonParams));
        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, URL, new org.json.JSONObject(jsonParams),
                new Response.Listener<org.json.JSONObject>() {
                    @Override
                    public void onResponse(org.json.JSONObject response) {
                        String msg = null;
                        try {
                            msg = (String) response.get("msg");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (msg.equals("success"))
                            Toast.makeText(getApplicationContext(), "Your data is subite...", Toast.LENGTH_SHORT).show();


                        else
                            Toast.makeText(Main.this, "", Toast.LENGTH_SHORT).show();
                        Log.d("event", "JSON" + response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("event","Error "+error
                                                            +"\nmessage"+error.getMessage());
                    }
                }
        );
        queue.add(postRequest);
    }
}