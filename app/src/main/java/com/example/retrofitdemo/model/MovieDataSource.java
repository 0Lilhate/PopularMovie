package com.example.retrofitdemo.model;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.paging.PageKeyedDataSource;

import com.example.retrofitdemo.MainActivity;
import com.example.retrofitdemo.service.MovieApiServer;
import com.example.retrofitdemo.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSource extends PageKeyedDataSource<Long, Result> {
    private static final String API_KEY = "7c77b786e9a06ef5a5b26cd11dade856";
    private MovieApiServer movieApiService;
    private Application application;

    public MovieDataSource(MovieApiServer movieApiService, Application application) {
        this.movieApiService = movieApiService;
        this.application = application;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params,
                            @NonNull LoadInitialCallback<Long, Result> callback) {

        movieApiService = RetrofitInstance.getService();
        Call<MovieApiResponse> call = movieApiService.getPopularMoviesWithPaging(API_KEY, 1);

        call.enqueue(new Callback<MovieApiResponse>() {
            @Override
            public void onResponse(Call<MovieApiResponse> call,
                                   Response<MovieApiResponse> response) {

                MovieApiResponse movieApiResponse = response.body();
                ArrayList<Result> results = new ArrayList<>();

                if (movieApiResponse != null &&
                        movieApiResponse.getResults() != null) {

                    results = (ArrayList<Result>) movieApiResponse.getResults();
                    callback.onResult(results, null, (long)2);

                }

            }

            @Override
            public void onFailure(Call<MovieApiResponse> call, Throwable t) {

            }
        });


    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params,
                           @NonNull LoadCallback<Long, Result> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params,
                          @NonNull final LoadCallback<Long, Result> callback) {

        movieApiService = RetrofitInstance.getService();
        Call<MovieApiResponse> call = movieApiService.getPopularMoviesWithPaging(API_KEY, params.key);

        call.enqueue(new Callback<MovieApiResponse>() {
            @Override
            public void onResponse(Call<MovieApiResponse> call,
                                   Response<MovieApiResponse> response) {

                MovieApiResponse movieApiResponse = response.body();
                ArrayList<Result> results = new ArrayList<>();

                if (movieApiResponse != null &&
                        movieApiResponse.getResults() != null) {

                    results = (ArrayList<Result>) movieApiResponse.getResults();
                    callback.onResult(results, params.key + 1);

                }


            }

            @Override
            public void onFailure(Call<MovieApiResponse> call, Throwable t) {

            }
        });

    }
}