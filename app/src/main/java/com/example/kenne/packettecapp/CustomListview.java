package com.example.kenne.packettecapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by kenne on 2/11/2017.
 */

public class CustomListview extends ArrayAdapter<String>{

    private String[] name;
    private String[] messageShort;
    private Integer[] imgid;
    private Activity context;
    private HashMap<String, Integer> meMap;


    // aqui hay que pasar solo el usuario que nos envio un msj.... es decir solo ir agregando uno en uno
    public CustomListview(Activity context,String[] name,String[] messageShort,Integer[] imgid,HashMap<String, Integer> meMap) {
        super(context, R.layout.listview_layout,name);
        this.context=context;
        this.name=name;
        this.messageShort=messageShort;
        this.imgid=imgid;
        this.meMap=meMap;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View r=convertView;
        ViewHolder viewHolder = null;
        if(r==null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r=layoutInflater.inflate(R.layout.listview_layout,null,true);
            viewHolder=new ViewHolder(r);
            r.setTag(viewHolder);

        }
        else{
            viewHolder= (ViewHolder) r.getTag();

        }

        String firstLName = name[position].substring(0,1).toString().toLowerCase();

        int value =meMap.get(firstLName);

        viewHolder.ivw.setImageResource(imgid[value-1]);
        viewHolder.tvw1.setText(name[position]);

        if (messageShort[position].length()<30){
            viewHolder.tvw2.setText(messageShort[position]);
        }else if (messageShort[position].length()>=30){
            viewHolder.tvw2.setText(messageShort[position].substring(0,30)+" ...");
        }
        return r;
    }

    class ViewHolder
    {
        TextView tvw1;
        TextView tvw2;
        ImageView ivw;
        ViewHolder(View v){
            tvw1=(TextView) v.findViewById(R.id.tvName);
            tvw2=(TextView) v.findViewById(R.id.tvShotMessage);
            ivw=(ImageView) v.findViewById(R.id.imageView);

        }

    }
}
