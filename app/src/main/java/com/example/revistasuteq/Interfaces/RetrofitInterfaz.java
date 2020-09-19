package com.example.revistasuteq.Interfaces;

import com.example.revistasuteq.Modelos.ModCategorias;
import com.example.revistasuteq.Modelos.ModDocumentos;
import com.example.revistasuteq.Modelos.ModEdiciones;
import com.example.revistasuteq.Modelos.ModRevistas;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RetrofitInterfaz {

    @GET("ws/journals.php")
    Call<List<ModRevistas>> getModRevistas();

    @GET
    Call<List<ModEdiciones>> getEdiciones(@Url String url);

    @GET
    Call<List<ModCategorias>> getModCategorias(@Url String url);

    @GET
    Call<List<ModDocumentos>> getModDocumentos(@Url String url);
}
