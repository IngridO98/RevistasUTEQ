package com.example.revistasuteq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.provider.SyncStateContract.Constants;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.revistasuteq.Adaptadores.AdapRevistas;
import com.example.revistasuteq.Clases.SelfSigningClientBuilder;
import com.example.revistasuteq.Interfaces.RetrofitInterfaz;
import com.example.revistasuteq.Modelos.ModRevistas;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Retrofit retrofit;
    ViewPager viewPager;
    AdapRevistas adapter;
    Integer[] colores=null;

    ArgbEvaluator argbEvaluator=new ArgbEvaluator();
    List<ModRevistas> listaRevista=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://revistas.uteq.edu.ec/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(SelfSigningClientBuilder.createClient(this))
                .build();

        final RetrofitInterfaz interfaz=retrofit.create(RetrofitInterfaz.class);
        Call<List<ModRevistas>> call=interfaz.getModRevistas();

        call.enqueue(new Callback<List<ModRevistas>>() {
            @Override
            public void onResponse(Call<List<ModRevistas>> call, Response<List<ModRevistas>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Error Codigo: "+response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                listaRevista=response.body();


                adapter=new AdapRevistas(listaRevista,MainActivity.this);
                viewPager = findViewById(R.id.viewPager);
                viewPager.setAdapter(adapter);
                viewPager.setPadding(130,0,130,0);

                /*
                Integer[] color_temp={
                        getResources().getColor(R.color.color1),
                        getResources().getColor(R.color.color3),
                        getResources().getColor(R.color.color2)
                };
                colores=color_temp;*/

                viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        if(position==0){
                            viewPager.setBackgroundResource(R.drawable.portada1);
                            //viewPager.setBackgroundColor((Integer) argbEvaluator.evaluate(positionOffset, colores[position],colores[position+1]));
                        }
                        if(position==1){
                            viewPager.setBackgroundResource(R.drawable.portada2);
                        }
                        if(position==2){
                            viewPager.setBackgroundResource(R.drawable.portada3);
                        }
                    }

                    @Override
                    public void onPageSelected(int position) {

                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
            }

            @Override
            public void onFailure(Call<List<ModRevistas>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initNetwork() {

    }
}