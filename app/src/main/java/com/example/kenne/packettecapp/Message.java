package com.example.kenne.packettecapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.kenne.packettecapp.List.LinkedList;
import com.example.kenne.packettecapp.Tree.ArbolAVL;
import com.example.kenne.packettecapp.Tree.ArbolB;
import com.example.kenne.packettecapp.Tree.ArbolSplay;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase de la ventana mensaje la cual permite escribir y adjuntar archivos
 */
public class Message extends AppCompatActivity {
    private Spinner spinner;
    private LinkedList listContact;
    private LinkedList listId;
    private String receptorContact;
    private String receptorID;
    private TextInputEditText mess;
    private org.json.simple.JSONObject jsonObject;
    private String emisorID;
    private final ArbolB<String> arbolRecibidos = new ArbolB<>(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        spinner = (Spinner) findViewById(R.id.spinner1);
        String[] a;
        mess = (TextInputEditText) findViewById(R.id.txMessage);
        updateContact();
    }

    /**
     * Actualiza la lista de contactos
     */
    public void updateContact() {
        Main main = new Main();
        String ruta = main.getURL();

        final String url = "http://"+ruta+":8080/PacketTEC/api/movil/getAll";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                listContact = new LinkedList();
                listId = new LinkedList();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = (JSONObject) response.get(i);
                        String a = jsonObject.getString("nombre");
                        String ix = jsonObject.getString("id");
                        listContact.addFirst(a);
                        listId.addFirst(ix);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                fill(listContact);


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Volley Log",error);

            }
        });

        requestQueue.add(jsonArrayRequest);
    }

    /**
     * Metódo que rellena el spinner
     * @param listaEnlazada
     */
    public void fill(LinkedList listaEnlazada){

        String axa = listaEnlazada.returnData(0);
        Log.d("First ID",""+axa);

        List<String> miArrayList = new ArrayList<String>();

        for (int e=0;e<listaEnlazada.size();e++){
            miArrayList.add(listaEnlazada.returnData(e));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,miArrayList);
        spinner.setAdapter(adapter);
    }

    public void recibirMensaje(final View view){
        Main main = new Main();
        String ruta = main.getURL();

        final String url = "http://"+ruta+":8080/PacketTEC/api/movil/mensajeEnviar";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());


        final JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url, new org.json.JSONObject(),
                new Response.Listener<org.json.JSONObject>() {
                    @Override
                    public void onResponse(org.json.JSONObject response) {
                        String msg = null;
                        String msg1 = null;
                        String msg2 = null;
                        String msgID = null;
                        Toast.makeText(getApplicationContext(), "Response:" + response.toString(), Toast.LENGTH_SHORT).show();


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
    public void sendMessage(View view){

        Spinner MySpinner = (Spinner)findViewById(R.id.spinner1);
        String text = spinner.getSelectedItem().toString();
        Integer indexValue = MySpinner.getSelectedItemPosition();

        jsonObject = new org.json.simple.JSONObject();

        emisorID = Main.getiD();
        receptorContact = listContact.returnData(indexValue);
        receptorID = listId.returnData(indexValue);
        String message = mess.getText().toString();

        Log.d("Message:",message + " lllllllllllll");


        jsonObject.put("idEmisor", emisorID);
        jsonObject.put("id", receptorID);
        jsonObject.put("message", message);

        //"{'message : '"+"'"+mensajeMovil+", 'id' : '" + idMovilReceptor+"'}  "
        postInfo(jsonObject);


        Snackbar.make(view, "", Snackbar.LENGTH_LONG).setText("----->  Send Message in process").show();
    }

    public void addImage(View view){
        Intent im = new Intent(this,Imagen.class);
        startActivity(im);
        Snackbar.make(view, "", Snackbar.LENGTH_LONG).setText("----->  Add Image in process").show();
        Intent i = new Intent(this, Imagen.class);
        startActivity(i);

    }
    public void addVideo(View view){
        recibirMensaje(view);

        Snackbar.make(view, "", Snackbar.LENGTH_LONG).setText("----->  Add Video in process").show();
    }
    public void addDocument(View view){
        Snackbar.make(view, "", Snackbar.LENGTH_LONG).setText("-----> Add Document in process").show();
    }

    public void postInfo(org.json.simple.JSONObject nombre){
        postInfo_Aux(nombre);
    }

    private void postInfo_Aux(final org.json.simple.JSONObject response){
        Main main = new Main();
        String ruta = main.getURL();
        Log.d("RUTA",ruta);
        final ArbolSplay<String> arbolSplay = new ArbolSplay<String>();
        final ArbolAVL<String> arbolAVL = new ArbolAVL<>();
        final String url = "http://"+ruta+":8080/PacketTEC/api/movil/mensaje";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String msgID = null;
        msgID = (String) response.get("id");
        Toast.makeText(getApplicationContext(), "Message to sent to:"+msgID, Toast.LENGTH_SHORT).show();
        final JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url, new org.json.JSONObject(response),
                new Response.Listener<org.json.JSONObject>() {
                    @Override
                    public void onResponse(org.json.JSONObject response) {
                        String msg = null;
                        String msg1 = null;
                        String msg2 = null;
                        String msgID = null;
                        try {
                            msg = (String) response.get("msg");
                            msg1 = (String) response.get("bandera");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        if (msg.equals("ok")) {
                            Toast.makeText(getApplicationContext(), "Message to sent", Toast.LENGTH_SHORT).show();

/*
                            if (msg1.equals("true")) {
                                String textoArbol = "Mensaje exitosamente enviado a :" + receptorID;
                                arbolSplay.insertar(textoArbol);

                            } else if (msg1.equals("false")) {
                                String textoArbol = "Mensaje fallido para :" + emisorID;
                                arbolAVL.insertar(textoArbol);
                            }
*/

                        }else{
                        if (msg.equals("no ok")) {
                            Toast.makeText(getApplicationContext(), "Message not sent ", Toast.LENGTH_SHORT).show();
                        }
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
        // hay que escribir en el FIL
        requestQueue.add(postRequest);

    }

}
