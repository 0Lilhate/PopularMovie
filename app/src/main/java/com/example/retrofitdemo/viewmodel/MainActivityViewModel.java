package com.example.retrofitdemo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.retrofitdemo.MainActivity;
import com.example.retrofitdemo.model.MovieDataSource;
import com.example.retrofitdemo.model.MovieDataSourceFactory;
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
    private MutableLiveData<MovieDataSource> movieDataSourceMutableLiveData;
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

        MovieDataSourceFactory movieDataSourceFactory = new MovieDataSourceFactory(application, movieApiService);
        movieDataSourceMutableLiveData = movieDataSourceFactory.getMutableLiveData();

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(3)
                .build();

        executor = Executors.newCachedThreadPool();

        pagedListLiveData = new LivePagedListBuilder<Long, Result>(movieDataSourceFactory, config)
                .setFetchExecutor(executor)
                .build();
    }

    public LiveData<PagedList<Result>> getPagedListLiveData() {
        return pagedListLiveData;
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
