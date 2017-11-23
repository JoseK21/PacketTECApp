package com.example.kenne.packettecapp;


import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by José Núñez on 23/11/2017.
 */

public class Protocol  extends AppCompatActivity implements View.OnClickListener {
    @Override


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server__android);


        findViewById(R.id.my_button).setOnClickListener(this);
    }


    @Override


    public void onClick(View arg0) {
        Button b = (Button)findViewById(R.id.my_button);


        b.setClickable(false);
        new LongRunningGetIO().execute();
    }

    private class LongRunningGetIO extends AsyncTask<Void, Void, String> {
        protected String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {
            InputStream in = entity.getContent();


            StringBuffer out = new StringBuffer();
            int n = 1;
            while (n>0) {
                byte[] b = new byte[4096];
                n =  in.read(b);


                if (n>0) out.append(new String(b, 0, n));
            }


            return out.toString();
        }


        @Override
        protected String doInBackground(Void... params) {
            HttpClient httpClient = new DefaultHttpClient();
            HttpContext localContext = new BasicHttpContext();

            HttpGet httpGet = new HttpGet("http://192.168.100.21:8080/Servidor/api/blackBoxes/put");
            String text = null;
            try {
                HttpResponse response = httpClient.execute(httpGet, localContext);
                HttpEntity entity = response.getEntity();
                text = getASCIIContentFromEntity(entity);

                if(!text.equals("")){
                    try{
                        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/InfoPacketTECApp/ContactJSON.json";

                        FileWriter fileWriter = new FileWriter(path);
                        fileWriter.write(text);
                        fileWriter.flush();

                    }catch (IOException e){
                        e.printStackTrace();

                    }
                }



            } catch (Exception e) {
                return e.getLocalizedMessage();
            }


            return text;
        }


        protected void onPostExecute(String results) {
            if (results!=null) {
                EditText et = (EditText)findViewById(R.id.my_edit);
                et.setText(results);
            }
            Button b = (Button)findViewById(R.id.my_button);
            b.setClickable(true);
        }


    }


}
