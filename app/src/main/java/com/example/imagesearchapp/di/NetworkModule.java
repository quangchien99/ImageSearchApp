package com.example.imagesearchapp.di;

import com.example.imagesearchapp.constant.Const;
import com.example.imagesearchapp.network.HeaderInterceptor;
import com.example.imagesearchapp.network.UnsplashAPI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(ApplicationComponent.class)
public class NetworkModule {

    @Provides
    @Singleton
    public static GsonConverterFactory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    public static Retrofit provideRetrofit(GsonConverterFactory gsonConverterFactory) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HeaderInterceptor(Const.API_KEY)).build();
        return new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .client(client)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public static UnsplashAPI provideAPI(Retrofit retrofit) {
        return retrofit.create(UnsplashAPI.class);
    }
}
