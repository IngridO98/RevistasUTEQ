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
import com.example.revistasuteq.Clases.MyGlideModule;
import com.example.revistasuteq.Modelos.ModRevistas;
import com.example.revistasuteq.R;

import java.util.List;

public class AdapRevistas extends PagerAdapter {

    private List<ModRevistas> modRevistas;
    private LayoutInflater layoutInflater;
    private Context context;

    public AdapRevistas(List<ModRevistas> modRevistas, Context context) {
        this.modRevistas = modRevistas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return modRevistas.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.itemrevistas, container, false);

        ImageView imgPortada;
        TextView txtTitulo;
        TextView txtDescripcion;

        imgPortada=view.findViewById(R.id.imgRevisLogo);
        txtTitulo= view.findViewById(R.id.txtRevisTitulo);
        txtDescripcion= view.findViewById(R.id.txtRevisDescrip);


        GlideApp.with(context).load(modRevistas.get(position).getPortada()).into(imgPortada);

        txtTitulo.setText(modRevistas.get(position).getName());
        txtDescripcion.setText(modRevistas.get(position).getDescription()
                .replace("</p>","\n")
                .replace("<p>","")
                .replace("<strong>","")
                .replace("</strong>","")
                .replace("<em>","")
                .replace("</em>","")
                .replace("<br />",""));

        container.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
