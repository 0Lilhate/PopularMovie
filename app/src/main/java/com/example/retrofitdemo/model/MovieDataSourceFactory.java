package com.example.retrofitdemo.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.retrofitdemo.service.MovieApiServer;

public class MovieDataSourceFactory extends DataSource.Factory {
    MovieApiServer movieApiService;
    private MovieDataSource movieDataSource;
    private Application application;
    private MutableLiveData<MovieDataSource> mutableLiveData;

    public MovieDataSourceFactory(Application application, MovieApiServer movieApiService) {
        this.application = application;
        mutableLiveData = new MutableLiveData<>();
        this.movieApiService = movieApiService;
        this.mutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource create() {
        movieDataSource = new MovieDataSource(movieApiService, application);
        mutableLiveData.postValue(movieDataSource);
        return movieDataSource;
    }

    public MutableLiveData<MovieDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
