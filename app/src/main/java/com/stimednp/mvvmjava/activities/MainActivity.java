package com.stimednp.mvvmjava.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.stimednp.mvvmjava.R;
import com.stimednp.mvvmjava.adapters.TVShowAdapter;
import com.stimednp.mvvmjava.databinding.ActivityMainBinding;
import com.stimednp.mvvmjava.model.TVShow;
import com.stimednp.mvvmjava.viewmodels.MostPopularTVShowsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private MostPopularTVShowsViewModel viewModel;
    private List<TVShow> tvShows = new ArrayList<>();
    private TVShowAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        doInitialization();
    }

    private void doInitialization() {
        activityMainBinding.tvShowsRecyclerView.setHasFixedSize(true);
        viewModel = new ViewModelProvider(this).get(MostPopularTVShowsViewModel.class);
        adapter = new TVShowAdapter(tvShows);
        activityMainBinding.tvShowsRecyclerView.setAdapter(adapter);
        getMostPopularTVShows();
    }

    private void getMostPopularTVShows() {
        activityMainBinding.setIsLoading(true);
        viewModel.getMostPopularTVShows(0).observe(this, mostPopularTVShowsResponse -> {
                    activityMainBinding.setIsLoading(false);
                    if (mostPopularTVShowsResponse != null) {
                        if (mostPopularTVShowsResponse.getTvShows() != null) {
                            tvShows.addAll(mostPopularTVShowsResponse.getTvShows());
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
        );
    }
}