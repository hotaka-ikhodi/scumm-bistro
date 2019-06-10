package com.scumm.bdd;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ScummApi {

    private static ScummApi scummApiInstance;

    public ScummApiService getService() {
        return service;
    }

    private final ScummApiService service;

    private ScummApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(ScummApiService.class);
    }

    public static synchronized ScummApi getInstance() {
        if (scummApiInstance == null) {
            scummApiInstance = new ScummApi();
        }
        return scummApiInstance;
    }
}
