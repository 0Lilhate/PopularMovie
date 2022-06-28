package com.example.retrofitdemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitdemo.R;
import com.example.retrofitdemo.databinding.ActivityFavoriteListBinding;
import com.example.retrofitdemo.databinding.MoviesPopularItemBinding;
import com.example.retrofitdemo.model.Result;

import java.util.List;

public class AdapterFavoriteMovie extends RecyclerView.Adapter<AdapterFavoriteMovie.MyViewHolder> {
    List<Result> results;



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MoviesPopularItemBinding moviesPopularItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.movies_popular_item, parent, false);
        return new MyViewHolder(moviesPopularItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.moviesPopularItemBinding.setMovie(results.get(position));
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        MoviesPopularItemBinding moviesPopularItemBinding;

        public MyViewHolder(MoviesPopularItemBinding moviesPopularItemBinding) {
            super(moviesPopularItemBinding.getRoot());
            this.moviesPopularItemBinding = moviesPopularItemBinding;
        }
    }

    public void setList(List<Result> results){
        this.results = results;
        notifyDataSetChanged();
    }

}
