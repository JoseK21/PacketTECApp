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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clasa Principal
 */
public class Main extends AppCompatActivity {

    private Button b_LogIn;
    private TextInputEditText textViewUserName_Main;
    private EditText editTextPassword_Main;

    private static String path;
    private static File dir;
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
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost =new HttpPost("192.168.43.75:8080/PacketTECServer1/index.xml");       // ver ruta del servidor
        //Nombre del servidor donde vamos a acceder la informacion(JSON->)



        try {
            HttpResponse response = httpClient.execute(httpPost);           // ejecuta el HttpResponse
            String jsonResult = inputStreamToString(response.getEntity().getContent()).toString();      //inputStreamToString: lee la información.!

            org.json.JSONObject object = new org.json.JSONObject(jsonResult);                           // Uso de la libreria que trae Android Studio(JSON)
            String name = object.getString("nombre");
            String version = object.getString("blog");

            Toast.makeText(getApplicationContext(), name + "<-> " + version, Toast.LENGTH_SHORT).show();

        }catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "Error 1 : "+ e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        catch (ClientProtocolException e ){
            Toast.makeText(getApplicationContext(), "Error 2 : "+ e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        catch (IOException e){
            Toast.makeText(getApplicationContext(), "Error 3 : "+ e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        */

        path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/InfoPacketTECApp";
        dir = new File(path);
        if (dir.exists()){
            this.finish();
            Intent i = new Intent(this, Chat.class);
            startActivity(i);
        }
        checkNetworkConnection();
    }

/*
    private StringBuilder inputStreamToString(InputStream is){
        String rLine = "";
        StringBuilder answer = new StringBuilder();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));

        try{
            while ((rLine = rd.readLine())!= null){
                answer.append(rLine);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return answer;
    }

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
     * Asigna el menu (menu - menu_main) a esta actividad(Ventana)
     *
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
     *
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
     *
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
            this.finish();
            Intent i = new Intent(this, Chat.class);
            startActivity(i);
            dir.mkdirs();
            JSONLoginIn(tUserName, tPassword);
        }
    }


    public void JSONLoginIn(String username, String password) {
        JSONObject jsonObject = new JSONObject();        //JSON object and values
        jsonObject.put("userName", username);
        jsonObject.put("password", password);

        try {
            FileWriter fileWriter = new FileWriter(path + "/infoJSON.json");
            fileWriter.write(jsonObject.toJSONString());
            fileWriter.flush();

            Toast.makeText(getApplicationContext(), "File::::: Susccefuly", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


/*

        //JSON array and values
        JSONArray jsonArray= new JSONArray();
        jsonArray.add("Java");
        jsonArray.add("Struts");
        jsonArray.add("jQuery");
        jsonArray.add("JavaScript");
        jsonArray.add("Database");

        jsonObject.put("technology", jsonArray);

        // writing the JSONObject into a file(info.json)
        try {
            FileWriter fileWriter = new FileWriter("info.json");
            fileWriter.write(jsonObject.toJSONString());
            fileWriter.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
*/