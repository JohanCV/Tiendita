package com.santiago.tiendita;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

public class ProveedorAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<Proveedores> datos;

    public ProveedorAdapter(Context context, int layout, List<Proveedores> datos) {
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

        TextView ruc,razon, direccion;

        ruc = v.findViewById(R.id.textViewruc);
        razon = v.findViewById(R.id.textViewRazonSocial);
        direccion = v.findViewById(R.id.txtdireccion);

        ruc.setText(datos.get(position).ruc);
        razon.setText(datos.get(position).razonsocial);
        direccion.setText(datos.get(position).direcion);

        return v;
    }
}
