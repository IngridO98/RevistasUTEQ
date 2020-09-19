package com.example.revistasuteq.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.revistasuteq.Clases.GlideApp;
import com.example.revistasuteq.Modelos.ModEdiciones;
import com.example.revistasuteq.Modelos.ModRevistas;
import com.example.revistasuteq.R;

import java.util.List;

public class AdapEdiciones extends PagerAdapter {

    private List<ModEdiciones> modEdiciones;
    private LayoutInflater layoutInflater;
    private Context context;

    public AdapEdiciones(List<ModEdiciones> modEdiciones, Context context) {
        this.modEdiciones = modEdiciones;
        this.context = context;
    }

    @Override
    public int getCount() {
        return modEdiciones.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.itemediciones, container, false);

        ImageView imgPortada;
        TextView txtTitulo, txtTituloCab;
        TextView txtDescripcion;

        imgPortada=view.findViewById(R.id.imgEdiPort);
        txtTitulo= view.findViewById(R.id.txtEdiTitulo);
        txtTituloCab=view.findViewById(R.id.txtEdiTituloCab);
        txtDescripcion= view.findViewById(R.id.txtEdiDescri);

        GlideApp.with(context).load(modEdiciones.get(position).getCover()).into(imgPortada);

        txtTitulo.setText(modEdiciones.get(position).getTitle());

        txtDescripcion.setText("Vol. "+modEdiciones.get(position).getVolume()
                +" Núm. "+modEdiciones.get(position).getNumber()
                +" ("+modEdiciones.get(position).getYear()+")");

        container.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
