package com.example.retrofitdemo.model.roommodel;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.retrofitdemo.model.Result;

import java.util.List;

@Dao
public interface ResultDao {

    @Query("SELECT * FROM favorite_movie")
    public LiveData<List<Result>> getAllFavoriteMovie();

    @Query("SELECT * FROM favorite_movie WHERE id ==:movie_id")
    public Result getFavoriteMovie(Integer movie_id);

    @Query("SELECT * FROM favorite_movie WHERE id ==:movie_id")
    public LiveData<Result> getTestFavoriteMovie(Integer movie_id);

    @Insert
    public void insert(Result favoriteMovie);

    @Delete
    public void delete(Result favoriteMovie);

}
