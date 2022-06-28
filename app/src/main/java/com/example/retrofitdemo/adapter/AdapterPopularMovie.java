package com.example.retrofitdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofitdemo.R;
import com.example.retrofitdemo.databinding.MoviesPopularItemBinding;
import com.example.retrofitdemo.model.Result;


import java.util.ArrayList;

public class AdapterPopularMovie extends PagedListAdapter<Result ,AdapterPopularMovie.MyHolderMovie> {

    //private ArrayList<Result> resultArrayList;
    private Context context;
    private onClickItemMovie onClickItemMovie;

    public AdapterPopularMovie(Context context) {
        super(Result.CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolderMovie onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MoviesPopularItemBinding moviesPopularItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
                , R.layout.movies_popular_item, parent, false);
        return new MyHolderMovie(moviesPopularItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolderMovie holder, int position) {
        String imagePath = "https://image.tmdb.org/t/p/w500/" + getItem(position)
                .getPosterPath();

        getItem(position).setPosterPath(imagePath);
        holder.moviesPopularItemBinding.setMovie(getItem(position));


    }

//    @Override
//    public int getItemCount() {
//        return getItemCount();
//    }

    class MyHolderMovie extends RecyclerView.ViewHolder {

        MoviesPopularItemBinding moviesPopularItemBinding;
        ImageView movieImageView;

        public MyHolderMovie(MoviesPopularItemBinding moviesPopularItemBinding) {
            super(moviesPopularItemBinding.getRoot());
            this.moviesPopularItemBinding = moviesPopularItemBinding;
            movieImageView = moviesPopularItemBinding.imageMovie;

            moviesPopularItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if(position != RecyclerView.NO_POSITION && onClickItemMovie != null){
                        onClickItemMovie.onCLickItem(getItem(position));
                    }

                }
            });
        }
    }

    public void setOnClickItemList(onClickItemMovie onClickItemMovie){
        this.onClickItemMovie = onClickItemMovie;
    }

    public interface onClickItemMovie{
        public void onCLickItem(Result result);
    }



}
