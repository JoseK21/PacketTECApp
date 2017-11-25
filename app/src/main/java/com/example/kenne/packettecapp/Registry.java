package com.example.kenne.packettecapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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
    private static String path;
    private static File dir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    /**
     * Metódo para registrarse en el sistema
     * @param view
     */
    public void signIn(View view){
        tIeTFullName = (TextInputEditText)findViewById(R.id.tiFullName);
        fn= tIeTFullName.getText().toString();

        tIeTUserName = (TextInputEditText)findViewById(R.id.tiUserName);
        un = tIeTUserName.getText().toString();

        tIeTPassword = (TextInputEditText)findViewById(R.id.tiPassword);
        passW = tIeTPassword.getText().toString();

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
            //createFiles();
            JSONObject jsonObject = new JSONObject();        //JSON object and values
            jsonObject.put("PASSWORD", passW);
            jsonObject.put("USERNAME", un);
            jsonObject.put("FULLNAME", fn);

            postInfo(jsonObject);
            returnMain();
        }
    }

    /**
     * Metódo para regresar a la pantalla inicial
     */
    public void returnMain(){
        Toast.makeText(getApplicationContext(), "Created account.", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, Main.class);
        startActivity(i);
    }

    public void postInfo(JSONObject nombre){
        postInfo_Aux(nombre);
    }

    private void postInfo_Aux(final JSONObject response){
        Main main = new Main();
        main.getURL();
        final String respuesta2 = "respuestaaaa";
        final String requestBody = response.toString();
        final String url = "http://192.168.1.13:8080/PacketTEC/api/movil/post";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url, new org.json.JSONObject(response),
                new Response.Listener<org.json.JSONObject>() {
                    @Override
                    public void onResponse(org.json.JSONObject response) {
                        String msg = null;
                        try {
                            msg = (String) response.get("msg");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (msg.equals("ok"))
                            Toast.makeText(getApplicationContext(), "PERFECT...", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(getApplicationContext(), "ERRORRRRRRR line131-Registry", Toast.LENGTH_SHORT).show();
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

        requestQueue.add(postRequest);

    }

    /*
    private void volleyCall(String fullname,String username,String password){
        RequestQueue queue = Volley.newRequestQueue(this);
// cambiar para pedir la IP
        String URL = "http://192.168.1.13:8080/PacketTEC/api/movil/put";
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
                            //Toast.makeText(Main.this, "", Toast.LENGTH_SHORT).show();
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
*/
   }
