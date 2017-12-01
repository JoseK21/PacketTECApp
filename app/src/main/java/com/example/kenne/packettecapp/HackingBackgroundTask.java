package com.example.kenne.packettecapp;

import android.os.AsyncTask;

import java.util.List;

/**
 * Created by hp 14 on 30/11/2017.
 */
 /*
public class HackingBackgroundTask extends AsyncTask<Void, Void> {

    static final int DURACION = 3 * 1000; // 3 segundos de carga

    @Override
    protected List doInBackground(Void... params) {
        // Simulaci&oacute;n de la carga de items
        try {
            Thread.sleep(DURACION);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Retornar en nuevos elementos para el adaptador
        return ConjuntoListas.randomList(CANTIDAD_ITEMS);
    }

    @Override
    protected void onPostExecute(List result) {
        super.onPostExecute(result);

        // Limpiar elementos antiguos
        adapter.clear();

        // A&ntilde;adir elementos nuevos
        adapter.addAll(result);

        // Parar la animaci&oacute;n del indicador
        refreshLayout.setRefreshing(false);
    }

}
*/
