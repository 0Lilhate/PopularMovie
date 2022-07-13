package com.example.retrofitdemo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.retrofitdemo.model.MovieRepository;
import com.example.retrofitdemo.model.Result;
import com.example.retrofitdemo.model.roommodel.AppRepository;
import com.example.retrofitdemo.service.MovieApiServer;
import com.example.retrofitdemo.service.RetrofitInstance;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class MainActivityViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;
    private Executor executor;
    private LiveData<PagedList<Result>> pagedListLiveData;
    private LiveData<List<Result>> results;
    private AppRepository appRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        //Room
        appRepository = new AppRepository(application);


        movieRepository = new MovieRepository();
        MovieApiServer movieApiService = RetrofitInstance.getService();

    }



    public MutableLiveData<List<Result>> getAllMovies(){

        return movieRepository.getAllMovies();
    }

    public LiveData<List<Result>> getAllRoomFavoriteMovies(){
        results = appRepository.getAllFavoriteMovies();
        return results;
    }

    public void insertFavoriteMovies(Result movies){
        appRepository.insertFavoriteMovie(movies);
    }

    public void deleteFavoriteMovies(Result movies){
        appRepository.deleteFavoriteMovie(movies);
    }

    public Result getFavoriteRoomMovies(Integer id){
        return appRepository.getFavoriteMovie(id);
    }

    public LiveData<Result> getTest(Integer id){
        return appRepository.getTestMovie(id);
    }

}
