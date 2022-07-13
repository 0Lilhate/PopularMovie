package com.example.retrofitdemo.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitdemo.R;

import com.example.retrofitdemo.databinding.MoviesPopularItemBinding;
import com.example.retrofitdemo.model.Result;

import java.util.List;

public class AdapterFavoriteMovie extends RecyclerView.Adapter<AdapterFavoriteMovie.MyViewHolder> {
    private List<Result> results;
    private AdapterPopularMovie.onClickItemMovie onClickItemMovie;



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MoviesPopularItemBinding moviesPopularItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.movies_popular_item, parent, false);
        return new MyViewHolder(moviesPopularItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        String imagePath = "https://image.tmdb.org/t/p/w500/" + results.get(position)
                .getPosterPath();

        Log.d("RETROFIT_DEBUG", imagePath);
        results.get(position).setPosterPath("https://image.tmdb.org/t/p/w500//8p9zXB7M78nZpm215zHfqpknMeM.jpg");

        holder.moviesPopularItemBinding.setMovie(results.get(position));
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        MoviesPopularItemBinding moviesPopularItemBinding;
        ImageView movieImageView;

        public MyViewHolder(MoviesPopularItemBinding moviesPopularItemBinding) {
            super(moviesPopularItemBinding.getRoot());
            this.moviesPopularItemBinding = moviesPopularItemBinding;
            movieImageView = moviesPopularItemBinding.imageMovie;



            moviesPopularItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if(position != RecyclerView.NO_POSITION && onClickItemMovie != null){
                        onClickItemMovie.onCLickItem(results.get(position));
                    }

                }
            });
        }
    }

    public void setOnClickItemList(AdapterPopularMovie.onClickItemMovie onClickItemMovie){
        this.onClickItemMovie = onClickItemMovie;
    }

    public interface onClickItemMovie{
        public void onCLickItem(Result result);
    }

    public void setList(List<Result> results){
        this.results = results;
        notifyDataSetChanged();
    }

}
