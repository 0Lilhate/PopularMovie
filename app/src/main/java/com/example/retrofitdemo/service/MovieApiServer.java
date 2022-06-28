package com.example.retrofitdemo.service;

import com.example.retrofitdemo.model.MovieApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiServer {

    @GET("movie/popular")
    Call<MovieApiResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<MovieApiResponse> getPopularMoviesWithPaging(@Query("api_key") String apiKey, @Query("page") long page);

}
