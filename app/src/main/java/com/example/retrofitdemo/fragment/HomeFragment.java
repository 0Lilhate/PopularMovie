package com.example.retrofitdemo.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.retrofitdemo.R;
import com.example.retrofitdemo.adapter.AdapterFavoriteMovie;
import com.example.retrofitdemo.adapter.AdapterPopularMovie;
import com.example.retrofitdemo.databinding.HomeFragmentBinding;
import com.example.retrofitdemo.model.Result;
import com.example.retrofitdemo.viewmodel.FragmentViewModel;
import com.example.retrofitdemo.viewmodel.MainActivityViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private HomeFragmentBinding homeFragmentBinding;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MainActivityViewModel mainActivityViewModel;
    private RecyclerView recyclerView;
    private NavController navigation;
    private ArrayList<Result> resultArrayList;

    private FragmentViewModel fragmentViewModel;

    private static final String API_KEY = "7c77b786e9a06ef5a5b26cd11dade856";

    private AdapterFavoriteMovie adapterFavoriteMovie;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        homeFragmentBinding =HomeFragmentBinding.inflate(inflater, container, false);

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);



        fragmentViewModel = new ViewModelProvider(this).get(FragmentViewModel.class);
        return homeFragmentBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        getResultArrayList();


        navigation = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main2);



        swipeRefreshLayout = homeFragmentBinding.swipeRefresh;
        swipeRefreshLayout.setColorSchemeResources(R.color.purple_700);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getResultArrayList();
            }
        });
    }



    private void initMoviePopularAdapter() {

        recyclerView = homeFragmentBinding.recyclerView;
        adapterFavoriteMovie = new AdapterFavoriteMovie();
        adapterFavoriteMovie.setList(resultArrayList);
        Log.d("RETROFIT_DEBUG", resultArrayList.get(2).getPosterPath());


        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
            recyclerView.setLayoutManager(gridLayoutManager);

        }else {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),4);
            recyclerView.setLayoutManager(gridLayoutManager);
        }



//        mainActivityViewModel.getAllRoomFavoriteMovies().observe(this, new Observer<List<Result>>() {
//            @Override
//            public void onChanged(List<Result> results) {
//                for(Result result1: results){
//                    Log.d("CheckRoom", "Room id: " + result1.getId().toString());
//                }
//            }
//        });



        recyclerView.setAdapter(adapterFavoriteMovie);

        adapterFavoriteMovie.setOnClickItemList(new AdapterPopularMovie.onClickItemMovie() {
            @Override
            public void onCLickItem(Result resultOnclick) {


                mainActivityViewModel.getTest(resultOnclick.getId()).observe(getViewLifecycleOwner(), new Observer<Result>() {
                    @Override
                    public void onChanged(Result result) {

                        if (result != null){
                            fragmentViewModel.getResult().postValue(result);
                            Log.d("CheckRoom", "room object");
                        }
                        else {
                            fragmentViewModel.getResult().postValue(resultOnclick);
                        }

                        // Info Movies
                        Toast.makeText(getContext(), "!!!", Toast.LENGTH_SHORT).show();

                        navigation.navigate(R.id.navigation_movie_info);


                    }
                });



            }
        });
        swipeRefreshLayout.setRefreshing(false);


    }

    private void getResultArrayList() {

        mainActivityViewModel.getAllMovies().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {
                resultArrayList = (ArrayList<Result>) results;
                initMoviePopularAdapter();
            }
        });


    }
}
