package com.example.kenne.packettecapp;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.kenne.packettecapp.List.LinkedList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Message extends AppCompatActivity {
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        spinner = (Spinner) findViewById(R.id.spinner1);
        String[] a;

        updateContact();
    }

    public void updateContact() {
        //final  String url = "http://172.18.194.91:8080/Servidor/api/contactos/getAll";


        Main main = new Main();
        main.getURL();
        Log.d("Information:",main.getURL());

        final String url = main.getURL()+"contactos/getAll";

        Log.d("Information:",url);

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                Log.d("BEFORE-----for","a");
                LinkedList listContact = new LinkedList();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = (JSONObject) response.get(i);

                        String a = jsonObject.getString("nombre");
                        Log.d("---- >", a);
                        listContact.addFirst(a);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                Log.d("INFO_SIZE", "--->"+listContact.size());
                Log.d("Lista de Contactos", "--->"+listContact.mostrarLista());
                fill(listContact);


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Volley Log",error);

            }
        });

        Log.i("inf","go here");
        requestQueue.add(jsonArrayRequest);
        Toast.makeText(getApplicationContext(),"Data Loader Successefully",Toast.LENGTH_SHORT).show();


    }



    public void fill(LinkedList listaEnlazada){
        //listaEnlazada.addFirst("Jonathan");
        //listaEnlazada.addFirst("Jose Carlos");
        //listaEnlazada.addFirst("Alejandro");
        //listaEnlazada.addFirst("Elias");

        String axa = listaEnlazada.returnData(0);
        Log.d("First ID",""+axa);

        List<String> miArrayList = new ArrayList<String>();

        for (int e=0;e<listaEnlazada.size();e++){
            miArrayList.add(listaEnlazada.returnData(e));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,miArrayList);
        spinner.setAdapter(adapter);
    }

    public void sendMessage(View view){
        Snackbar.make(view, "", Snackbar.LENGTH_LONG).setText("----->  Send Message in process").show();
    }
    public void addImage(View view){
        Snackbar.make(view, "", Snackbar.LENGTH_LONG).setText("----->  Add Image in process").show();
    }
    public void addVideo(View view){
        Snackbar.make(view, "", Snackbar.LENGTH_LONG).setText("----->  Add Video in process").show();
    }
    public void addDocument(View view){
        Snackbar.make(view, "", Snackbar.LENGTH_LONG).setText("-----> Add Document in process").show();
    }

    public void loadUser(String[] listUser) {
            // enviar una lista de todos los usuario ingresados desde el servidor

    }
}
