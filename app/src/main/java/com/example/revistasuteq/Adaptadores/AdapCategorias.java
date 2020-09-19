package com.example.revistasuteq.Adaptadores;

import android.content.ClipData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.revistasuteq.Modelos.ModAdapCatxDoc;
import com.example.revistasuteq.Modelos.ModCategorias;
import com.example.revistasuteq.R;

import java.util.List;

public class AdapCategorias extends RecyclerView.Adapter<AdapCategorias.ViewHolderCategorias>  {

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private List<ModAdapCatxDoc> ListaCategorias;

    public AdapCategorias(List<ModAdapCatxDoc> ListaCategorias) {
        this.ListaCategorias = ListaCategorias;
    }

    @NonNull
    @Override
    public ViewHolderCategorias onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemcategoria, parent, false);
        return new ViewHolderCategorias(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCategorias holder, int position) {
        ModAdapCatxDoc item = ListaCategorias.get(position);
        holder.txtTitulo.setText(item.getNombCateg());

        // Create layout manager with initial prefetch item count
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                holder.rvDocs.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        layoutManager.setInitialPrefetchItemCount(item.getLisDocxCate().size());

        // Create sub item view adapter
        AdapDocumentos adapDocumentos = new AdapDocumentos(item.getLisDocxCate());

        holder.rvDocs.setLayoutManager(layoutManager);
        holder.rvDocs.setAdapter(adapDocumentos);
        holder.rvDocs.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        return ListaCategorias.size();
    }

    public class ViewHolderCategorias extends  RecyclerView.ViewHolder  {
        private TextView txtTitulo;
        private RecyclerView rvDocs;

        public ViewHolderCategorias(@NonNull View itemView) {
            super(itemView);
            txtTitulo = itemView.findViewById(R.id.txtCategoria);
            rvDocs = itemView.findViewById(R.id.rvCategoria);
        }
    }
}
