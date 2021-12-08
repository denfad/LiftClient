package ru.denfad.project1.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 * A specific Retrofit library class
 *  */
public class NetworkService {
    private static NetworkService mInstance;
    private static final String BASE_URL = "http://192.168.137.162:8080";
    private Retrofit mRetrofit;

    private NetworkService() {


        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    // Realising Singleton pattern
    public static NetworkService getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkService();
        }
        return mInstance;
    }

    // Returns interaction with server Interface
    public JsonBuildingHolderNetwork getJSONApi() {
        return mRetrofit.create(JsonBuildingHolderNetwork.class);
    }
}
