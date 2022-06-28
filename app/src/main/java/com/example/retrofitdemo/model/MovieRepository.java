package com.example.retrofitdemo.model;


import androidx.lifecycle.MutableLiveData;

import com.example.retrofitdemo.service.MovieApiServer;
import com.example.retrofitdemo.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private ArrayList<Result> resultArrayList = new ArrayList<>();
    private MutableLiveData<List<Result>> mutableLiveData = new MutableLiveData<>();
    private static final String API_KEY = "7c77b786e9a06ef5a5b26cd11dade856";

    public MutableLiveData<List<Result>> getAllMovies(){

        MovieApiServer countryService = RetrofitInstance.getService();
        Call<MovieApiResponse> call = countryService.getPopularMovies(API_KEY);
        call.enqueue(new Callback<MovieApiResponse>() {
            @Override
            public void onResponse(Call<MovieApiResponse> call, Response<MovieApiResponse> response) {
                MovieApiResponse movieApiResponseInfo = response.body();

                if(movieApiResponseInfo != null && movieApiResponseInfo.getResults() != null){
                    resultArrayList = (ArrayList<Result>) movieApiResponseInfo.getResults();
                    mutableLiveData.postValue(resultArrayList);
                }
            }

            @Override
            public void onFailure(Call<MovieApiResponse> call, Throwable t) {

            }
        });

        return mutableLiveData;
    }

}
