package com.example.retrofitdemo.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.retrofitdemo.R;

import com.example.retrofitdemo.databinding.ActivityMovieInfoBinding;
import com.example.retrofitdemo.model.Result;
import com.example.retrofitdemo.viewmodel.FragmentViewModel;
import com.example.retrofitdemo.viewmodel.MainActivityViewModel;

import java.util.List;

public class MovieInfoActivity extends Fragment {

    ActivityMovieInfoBinding activityMovieInfoBinding;
    public static final String RESULT_MOVIE = "ResultMovie";
    private Result resultMovie;

    private View view;

    private FragmentViewModel fragmentViewModel;
    private MainActivityViewModel mainActivityViewModel;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        activityMovieInfoBinding = ActivityMovieInfoBinding.inflate(inflater,container,false);
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        fragmentViewModel = new ViewModelProvider(this).get(FragmentViewModel.class);
//        view = inflater.inflate(R.layout.activity_movie_info, container, false);
//
//        TextView textView = view.findViewById(R.id.descriptionMovieTextView);
//        textView.setText("adad");

        return activityMovieInfoBinding.getRoot();
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        //activityMovieInfoBinding.checkBox.setChecked(true);

        fragmentViewModel.getResult().observe(this, new Observer<Result>() {
            @Override
            public void onChanged(Result result) {
                if (result == null){
                    Log.d("RESMOVIE", "Result is null");
                }
                else {
                    Log.d("RESMOVIE", "Result is not null");

                    resultMovie = result;

                    activityMovieInfoBinding.setMovieResult(resultMovie);

                    OnClickCheckBox(activityMovieInfoBinding.checkBox);


                }

            }
        });



    }

    private void OnClickCheckBox(CheckBox checkBox) {

        checkBox.setChecked(resultMovie.isFavoriteCheckBox());


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(!resultMovie.isFavoriteCheckBox()){

                    resultMovie.setFavoriteCheckBox(true);
                    mainActivityViewModel.insertFavoriteMovies(resultMovie);
                }
                else {
                    resultMovie.setFavoriteCheckBox(false);
                    mainActivityViewModel.deleteFavoriteMovies(resultMovie);

                }
            }
        });
    }
}