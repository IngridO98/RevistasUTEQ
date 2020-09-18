package com.example.revistasuteq.Interfaces;

import com.example.revistasuteq.Modelos.ModRevistas;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterfaz {

    @GET("ws/journals.php")
    Call<List<ModRevistas>> getModRevistas();
}
