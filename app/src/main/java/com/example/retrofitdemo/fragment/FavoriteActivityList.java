package com.example.retrofitdemo.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.example.retrofitdemo.R;
import com.example.retrofitdemo.adapter.AdapterFavoriteMovie;
import com.example.retrofitdemo.adapter.AdapterPopularMovie;
import com.example.retrofitdemo.databinding.ActivityFavoriteListBinding;
import com.example.retrofitdemo.databinding.HomeFragmentBinding;
import com.example.retrofitdemo.model.Result;
import com.example.retrofitdemo.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class FavoriteActivityList extends Fragment {
    ActivityFavoriteListBinding activityFavoriteListBinding;
    MainActivityViewModel viewModel;
    AdapterFavoriteMovie adapterFavoriteMovie;
    ArrayList<Result> resultArrayList;
    RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        activityFavoriteListBinding = ActivityFavoriteListBinding.inflate(inflater,container,false);

        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);



        return activityFavoriteListBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();

        getAllFavoriteMovies();
    }

    private void getAllFavoriteMovies() {
        viewModel.getAllRoomFavoriteMovies().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {
                resultArrayList = (ArrayList<Result>) results;

                initAdapter();
            }
        });
    }

    private void initAdapter() {
        adapterFavoriteMovie = new AdapterFavoriteMovie();
        adapterFavoriteMovie.setList(resultArrayList);
        recyclerView = activityFavoriteListBinding.recyclerView2;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapterFavoriteMovie);

        ItemTouchLeft();
    }

    private void ItemTouchLeft() {

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                viewModel.deleteFavoriteMovies(resultArrayList.get(viewHolder.getAdapterPosition() ));
            }
        }).attachToRecyclerView(recyclerView);

    }
}