package com.quectel.recyclerviewdaggerpractice.di.modules;

import com.quectel.recyclerviewdaggerpractice.di.scopes.ApplicationScope;
import com.quectel.recyclerviewdaggerpractice.retrofit.APIInterface;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule
{
    @Provides
    @ApplicationScope
    APIInterface getApiInterface(Retrofit retrofit)
    {
        return retrofit.create(APIInterface.class);
    }
    
    @Provides
    @ApplicationScope
    Retrofit getRetrofit(OkHttpClient okHttpClient)
    {
        return new Retrofit.Builder()
                .baseUrl("https://swapi.co/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }
    
    @Provides
    @ApplicationScope
    OkHttpClient getOkHttpCleint(HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }
    
    @Provides
    @ApplicationScope
    HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }
}
