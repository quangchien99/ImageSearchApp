package com.example.imagesearchapp.network;
import com.example.imagesearchapp.constant.Const;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class UnsplashClient {
    private static Retrofit retrofit = null;
    public static Retrofit getUnsplashClient() {
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new HeaderInterceptor(Const.API_KEY)).build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(Const.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
