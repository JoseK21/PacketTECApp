package com.example.kenne.packettecapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

public class Mensajeria extends AppCompatActivity {

    public static TextInputEditText texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mensajeria);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                mostrarMensaje(view);
            }
        });


    }

    public void mostrarMensaje(final View view){
        Main main = new Main();
        String ruta = main.getURL();

        final String url = "http://"+ruta+":8080/PacketTEC/api/movil/mensajeEnviar";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        texto = (TextInputEditText) findViewById(R.id.textoo);


        final JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url, new org.json.JSONObject(),
                new Response.Listener<org.json.JSONObject>() {
                    @Override
                    public void onResponse(org.json.JSONObject response) {
                        String msg = null;
                        String msg1 = null;
                        String msg2 = null;
                        String msgID = null;
                        Toast.makeText(getApplicationContext(), "Response:" + response.toString(), Toast.LENGTH_SHORT).show();
                        try {
                            texto.setText((CharSequence) response.get("message"));
                        } catch (JSONException e) {
                            e.printStackTrace();
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

}
