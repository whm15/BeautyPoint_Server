package br.com.android.laudeni.beautypoint;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ISalaoWS {
    @GET("saloes")
    Call<List<Salao>> getSaloes();
}
