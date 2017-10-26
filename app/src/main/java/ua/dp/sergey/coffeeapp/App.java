package ua.dp.sergey.coffeeapp;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ua.dp.sergey.coffeeapp.api.IServerAPI;

/**
 * Created by Sergey-PC on 21.10.2017.
 */

public class App extends Application {
    private static IServerAPI mServerApi;

    public static IServerAPI getApi() {
        return mServerApi;
    }

    private void initServerApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://coffee-server-api.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mServerApi = retrofit.create(IServerAPI.class);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initServerApi();
    }
}