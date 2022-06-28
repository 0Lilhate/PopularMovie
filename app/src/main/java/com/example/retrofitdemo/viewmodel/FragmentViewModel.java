package com.example.retrofitdemo.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.retrofitdemo.model.Result;

public class FragmentViewModel extends ViewModel {
    private static MutableLiveData<Result> resultMutableLiveData;

    public synchronized MutableLiveData<Result> getResult(){

        if(resultMutableLiveData == null){
            resultMutableLiveData = new MutableLiveData<>();
            Log.d("RESMOVIE", "Firs mutable");
        }
        return resultMutableLiveData;
    }
}
