package com.example.revistasuteq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.revistasuteq.Adaptadores.AdapEdiciones;
import com.example.revistasuteq.Adaptadores.AdapRevistas;
import com.example.revistasuteq.Clases.SelfSigningClientBuilder;
import com.example.revistasuteq.Interfaces.RetrofitInterfaz;
import com.example.revistasuteq.Modelos.ModEdiciones;
import com.example.revistasuteq.Modelos.ModRevistas;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Ediciones extends AppCompatActivity {
    Retrofit retrofit;
    ViewPager viewPager;
    AdapEdiciones adapEdiciones;
    ArgbEvaluator argbEvaluator=new ArgbEvaluator();
    List<ModEdiciones> listaEdiciones=new ArrayList<>();

    TextView txtEdiTituloCab, txtEdiAbrevia, txtTituCard;
    String IdRevista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ediciones);

        txtEdiTituloCab= findViewById(R.id.txtEdiTituloCab);
        txtEdiAbrevia= findViewById(R.id.txtEdiAbreCab);


        Bundle bundle = this.getIntent().getExtras();
        IdRevista=bundle.getString("IdRevista");
        txtEdiTituloCab.setText(bundle.getString("Titulo"));
        txtEdiAbrevia.setText(bundle.getString("Abreviatura"));
        //txtTituCard.setText(bundle.getString("Titulo"));

        retrofit = new Retrofit.Builder()
                .baseUrl("https://revistas.uteq.edu.ec/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(SelfSigningClientBuilder.createClient(this))
                .build();
        final RetrofitInterfaz interfaz=retrofit.create(RetrofitInterfaz.class);
        Call<List<ModEdiciones>> call=interfaz.getEdiciones("https://revistas.uteq.edu.ec/ws/issues.php?j_id="+IdRevista);
        call.enqueue(new Callback<List<ModEdiciones>>() {
            @Override
            public void onResponse(Call<List<ModEdiciones>> call, Response<List<ModEdiciones>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Error Codigo: "+response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                listaEdiciones=response.body();

                adapEdiciones=new AdapEdiciones(listaEdiciones,Ediciones.this);
                viewPager = findViewById(R.id.viewPagerEdi);
                viewPager.setAdapter(adapEdiciones);
                viewPager.setPadding(100,0,100,0);

            }

            @Override
            public void onFailure(Call<List<ModEdiciones>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.toString(), Toast.LENGTH_LONG).show();
            }
        });






    }
}