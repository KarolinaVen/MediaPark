package com.example.rozhe.mediapark;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class App extends Application {

    private MutableLiveData<List<Info>> infoCache = new MutableLiveData<>();

    @Override
    public void onCreate() {
        super.onCreate();

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<Info>> call = service.getAllCars();
        call.enqueue(new Callback<List<Info>>() {
            @Override
            public void onResponse(Call<List<Info>> call, Response<List<Info>> response) {
                infoCache.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Info>> call, Throwable t) {
                infoCache.setValue(null);
            }
        });
    }

    public LiveData<List<Info>> getInfoCache() {
        return infoCache;
    }
}