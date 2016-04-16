package com.neerajsingh.myntrahd;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by anshulika.ks on 16/04/16.
 */
public class MyntraHDApplication extends Application{

    public static final String BASE_URL = "http://10.0.13.68:8080";
    private static Retrofit retrofit;
    @Override
    public void onCreate() {
        super.onCreate();

        initializeRetrofit(BASE_URL);

    }

    public static void initializeRetrofit(String BASE_URL) {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static Retrofit getRetrofit() {
        return retrofit;
    }
}
