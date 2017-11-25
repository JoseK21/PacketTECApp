package com.example.kenne.packettecapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
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
import java.io.FileWriter;
import java.io.IOException;

/**
 * Registra los usuarios si no hay uno existente
 */
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
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("PASSWORD", passW);
            jsonObject.put("USERNAME", un);
            jsonObject.put("FULLNAME", fn);

            createFiles();
            postInfo(jsonObject);
            returnMain();
        }
    }

    /**
     * Metódo para regresar a la pantalla inicial
     */
    public void returnMain(){
        Intent i = new Intent(this, Main.class);
        startActivity(i);
    }

    public void postInfo(JSONObject nombre){
        postInfo_Aux(nombre);
    }

    /**
     * Envia al servdor la informcaión del registro
     * @param response
     */
    private void postInfo_Aux(final JSONObject response){
        Main main = new Main();
        String ruta = main.getURL();
        Log.d("RUTA",ruta);

        final String url = "http://"+ruta+":8080/PacketTEC/api/movil/post";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url, new org.json.JSONObject(response),
                new Response.Listener<org.json.JSONObject>() {
                    @Override
                    public void onResponse(org.json.JSONObject response) {
                        String msg = null;
                        String msgID = null;
                        try {
                            msg = (String) response.get("msg");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (msg.equals("ok"))

                            try {
                                msgID = (String) response.get("id");
                                setTEXid(msgID);
                                Toast.makeText(getApplicationContext(), "Created Accout", Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        else
                            if (msg.equals("no ok")){
                                Toast.makeText(getApplicationContext(), "Error---> Exist Accout ", Toast.LENGTH_SHORT).show();

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

    public void setTEXid(String id){
        Chat chat = new Chat();
        chat.showID(id);
    }

    /**
     * Crea los archivos donde serán guardadas las fotos
     */
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   }
