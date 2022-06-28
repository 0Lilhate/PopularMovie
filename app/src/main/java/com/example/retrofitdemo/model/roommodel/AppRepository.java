package com.example.retrofitdemo.model.roommodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.retrofitdemo.model.Result;

import java.util.List;

public class AppRepository {
    private ResultDao resultDao;
    private LiveData<List<Result>> results;
    private Result getResult;

    public AppRepository(Application application) {
        FavoriteMovieDatabase favoriteMovieDatabase = FavoriteMovieDatabase.getInstance(application);

        this.resultDao = favoriteMovieDatabase.getResultDao();
        this.results = resultDao.getAllFavoriteMovie();
    }

    public LiveData<List<Result>> getAllFavoriteMovies(){
        return resultDao.getAllFavoriteMovie();
    }

    public void insertFavoriteMovie(Result result){
        new InsertAsyncTask(resultDao).execute(result);
    }


    private class InsertAsyncTask extends AsyncTask<Result, Void, Void>{
        private ResultDao resultDao;

        public InsertAsyncTask(ResultDao resultDao) {
            this.resultDao = resultDao;
        }

        @Override
        protected Void doInBackground(Result... results) {
            resultDao.insert(results[0]);
            return null;
        }
    }

    public void deleteFavoriteMovie(Result result){
        new DeleteAsyncTask(resultDao).execute(result);
    }


    private class DeleteAsyncTask extends AsyncTask<Result, Void, Void>{
        private ResultDao resultDao;

        public DeleteAsyncTask(ResultDao resultDao) {
            this.resultDao = resultDao;
        }

        @Override
        protected Void doInBackground(Result... results) {
            resultDao.delete(results[0]);
            return null;
        }
    }

    public Result getFavoriteMovie(Integer id){
        new GetFavoriteAsyncTask(resultDao).execute(id);
        return getResult;
    }

    public LiveData<Result> getTestMovie(Integer id){
        return resultDao.getTestFavoriteMovie(id);
    }


    private class GetFavoriteAsyncTask extends AsyncTask<Integer, Void, Result>{
        private ResultDao resultDao;

        public GetFavoriteAsyncTask(ResultDao resultDao) {
            this.resultDao = resultDao;
        }


        @Override
        protected Result doInBackground(Integer... integers) {
            getResult = new Result();
            getResult = resultDao.getFavoriteMovie(integers[0]);
            return getResult;
        }

        @Override
        protected void onPostExecute(Result result) {
            super.onPostExecute(result);

        }
    }
}
