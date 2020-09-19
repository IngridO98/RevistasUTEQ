package com.example.revistasuteq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.revistasuteq.Adaptadores.AdapCategorias;
import com.example.revistasuteq.Clases.SelfSigningClientBuilder;
import com.example.revistasuteq.Interfaces.RetrofitInterfaz;
import com.example.revistasuteq.Modelos.ModAdapCatxDoc;
import com.example.revistasuteq.Modelos.ModCategorias;
import com.example.revistasuteq.Modelos.ModDocumentos;
import com.example.revistasuteq.Modelos.ModEdiciones;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Documentos extends AppCompatActivity {
    Retrofit retrofit;
    TextView txtTitulo, txtdoi,txtpublicado;
    String id;
    RecyclerView recy;

    List<ModCategorias> listacatego=new ArrayList<>();
    List<ModDocumentos> listaDocu=new ArrayList<>();
    List<ModAdapCatxDoc> listGeneral=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documentos);

        txtTitulo= findViewById(R.id.txtEdiTituloCab);
        txtdoi= findViewById(R.id.txtEdiDoi);
        txtpublicado= findViewById(R.id.txtEdiPublic);

        Bundle bundle = this.getIntent().getExtras();
        id=bundle.getString("Id");
        txtTitulo.setText(bundle.getString("Titulo"));
        txtdoi.setText(bundle.getString("Doi"));
        txtpublicado.setText(bundle.getString("Publicada"));

        retrofit = new Retrofit.Builder()
                .baseUrl("https://revistas.uteq.edu.ec/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(SelfSigningClientBuilder.createClient(this))
                .build();
        final RetrofitInterfaz interfaz=retrofit.create(RetrofitInterfaz.class);
        Call<List<ModCategorias>> call=interfaz.getModCategorias("https://revistas.uteq.edu.ec/ws/pubssections.php?i_id="+id);
        call.enqueue(new Callback<List<ModCategorias>>() {
            @Override
            public void onResponse(Call<List<ModCategorias>> call, Response<List<ModCategorias>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Error Codigo: "+response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                listacatego=response.body();
            }
            @Override
            public void onFailure(Call<List<ModCategorias>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.toString(), Toast.LENGTH_LONG).show();
            }
        });
        String idcat="";

        for (int i=0;i<listacatego.size();i++){
            String cat=listacatego.get(i).getSection();
            Call<List<ModDocumentos>> call1=interfaz.getModDocumentos("https://revistas.uteq.edu.ec/ws/pubs.php?i_id="+id+"&section="+listacatego.get(i).getSection_id());
            call1.enqueue(new Callback<List<ModDocumentos>>() {
                @Override
                public void onResponse(Call<List<ModDocumentos>> call, Response<List<ModDocumentos>> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Error Codigo: "+response.code(), Toast.LENGTH_LONG).show();
                        return;
                    }
                    listaDocu=response.body();
                    listGeneral.add(new ModAdapCatxDoc(cat,listaDocu));

                }

                @Override
                public void onFailure(Call<List<ModDocumentos>> call, Throwable t) {

                }
            });
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvGeneral);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        AdapCategorias adapter = new AdapCategorias(listGeneral);
        recyclerView.setAdapter(adapter);




    }
}