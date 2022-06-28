package com.example.retrofitdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.retrofitdemo.adapter.AdapterPopularMovie;
import com.example.retrofitdemo.databinding.ActivityMainBinding;
import com.example.retrofitdemo.model.Result;
import com.example.retrofitdemo.viewmodel.MainActivityViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    private PagedList<Result> results;
    private static final String API_KEY = "7c77b786e9a06ef5a5b26cd11dade856";
    private ActivityMainBinding activityMainBinding;

    private AdapterPopularMovie adapterPopularMovie;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MainActivityViewModel mainActivityViewModel;
    private RecyclerView recyclerView;
    private NavController navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setContentView(activityMainBinding.getRoot());


        navigation =Navigation.findNavController(this, R.id.nav_host_fragment_activity_main2);


        ClickItemBottomNavigationView(activityMainBinding.bottomNavigationView);

    }



    public void ClickItemBottomNavigationView(BottomNavigationView bottomNavigationView){
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home_menu:
                        navigation.navigate(R.id.navigation_home);
                        return true;
                    case R.id.favorite_menu:

                        navigation.navigate(R.id.navigation_favorite);
                        return true;
                    case R.id.account_menu:


                        return true;
                }
                return false;

            }
        });
    }
}