package com.santiago.tiendita;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CategoriasAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<Categoria> datos;

    public CategoriasAdapter(Context context, int layout, List<Categoria> datos) {
        this.context = context;
        this.layout = layout;
        this.datos = datos;
    }

    @Override
    public int getCount() {
        return datos.size();
    }

    @Override
    public Object getItem(int position) {
        return datos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(layout,null);
        ImageView i = v.findViewById(R.id.imagen);
        //TextView t = v.findViewById(R.id.descripcion);
        //t.setText(datos.get(position).descripcion);
        Glide.with(context).load(datos.get(position).imagen_banner).into(i);

        return v;
    }
}
