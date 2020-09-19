package com.example.revistasuteq.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.revistasuteq.Modelos.ModDocumentos;
import com.example.revistasuteq.R;

import java.util.List;

public class AdapDocumentos extends RecyclerView.Adapter<AdapDocumentos.ViewHolderAdapDocumentos>{

    private List<ModDocumentos> ListaDocumentos;

    AdapDocumentos(List<ModDocumentos> ListaDocumentos) {
        this.ListaDocumentos = ListaDocumentos;
    }

    @NonNull
    @Override
    public ViewHolderAdapDocumentos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemdocxcateg, parent, false);
        return new ViewHolderAdapDocumentos(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAdapDocumentos holder, int position) {

        ModDocumentos modDocumentos = ListaDocumentos.get(position);
        holder.txtNomArt.setText(modDocumentos.getTitle());
        String Autores="";
        for (int i=0;i<modDocumentos.getAuthors().size();i++){
          Autores=Autores+modDocumentos.getAuthors().get(i).getNombres() +", ";
        }
        Autores.substring(0,Autores.length()-2);
        holder.txtNomArt.setText(Autores);

        for (int e=0;e<modDocumentos.getGaleys().size();e++){
            if(modDocumentos.getGaleys().get(e).getLabel()=="PDF"){
                holder.btnpdf.setVisibility(View.VISIBLE);
            }
            if(modDocumentos.getGaleys().get(e).getLabel()=="HTML"){
                holder.btnhtml.setVisibility(View.VISIBLE);
            }
        }

    }

    @Override
    public int getItemCount() {
        return ListaDocumentos.size();
    }

    public class ViewHolderAdapDocumentos extends RecyclerView.ViewHolder {
        TextView txtNomArt, txtAutores;
        Button  btnpdf, btnhtml;
        public ViewHolderAdapDocumentos(@NonNull View itemView) {
            super(itemView);
            txtNomArt=itemView.findViewById(R.id.txtDocTitulo);
            txtAutores=itemView.findViewById(R.id.txtDocAutores);
            btnpdf=itemView.findViewById(R.id.btnPDF);
            btnhtml=itemView.findViewById(R.id.btnHTML);


        }
    }
}
