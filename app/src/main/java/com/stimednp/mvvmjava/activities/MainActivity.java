package com.stimednp.mvvmjava.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.stimednp.mvvmjava.R;
import com.stimednp.mvvmjava.adapters.TVShowAdapter;
import com.stimednp.mvvmjava.databinding.ActivityMainBinding;
import com.stimednp.mvvmjava.listener.TVShowsListener;
import com.stimednp.mvvmjava.model.TVShow;
import com.stimednp.mvvmjava.viewmodels.MostPopularTVShowsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TVShowsListener {

    public final static String EXTRA_TVSHOW = "extra_tvshow";

    private ActivityMainBinding activityMainBinding;
    private MostPopularTVShowsViewModel viewModel;
    private final List<TVShow> tvShows = new ArrayList<>();
    private TVShowAdapter adapter;
    private int currentPage = 1;
    private int totalAvailablePage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        doInitialization();
    }

    private void doInitialization() {
        activityMainBinding.tvShowsRecyclerView.setHasFixedSize(true);
        viewModel = new ViewModelProvider(this).get(MostPopularTVShowsViewModel.class);
        adapter = new TVShowAdapter(tvShows, this);
        activityMainBinding.tvShowsRecyclerView.setAdapter(adapter);
        activityMainBinding.tvShowsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!activityMainBinding.tvShowsRecyclerView.canScrollVertically(1)) {
                    if (currentPage <= totalAvailablePage) {
                        currentPage += 1;
                        getMostPopularTVShows();
                    }
                }
            }
        });
        activityMainBinding.imageWatchlist.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), WatchListActivity.class)));
        activityMainBinding.imageSearch.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), SearchActivity.class)));
        getMostPopularTVShows();
    }

    private void getMostPopularTVShows() {
        toggleLoading();
        viewModel.getMostPopularTVShows(currentPage).observe(this, mostPopularTVShowsResponse -> {
                    toggleLoading();
                    if (mostPopularTVShowsResponse != null) {
                        totalAvailablePage = mostPopularTVShowsResponse.getTotalPages();
                        if (mostPopularTVShowsResponse.getTvShows() != null) {
                            int oldCount = tvShows.size();
                            tvShows.addAll(mostPopularTVShowsResponse.getTvShows());
                            adapter.notifyItemRangeInserted(oldCount, tvShows.size());
                        }
                    }
                }
        );
    }

    private void toggleLoading() {
        if (currentPage == 1) {
            activityMainBinding.setIsLoading(activityMainBinding.getIsLoading() == null || !activityMainBinding.getIsLoading());
        } else {
            activityMainBinding.setIsLoadingMore(activityMainBinding.getIsLoadingMore() == null || !activityMainBinding.getIsLoadingMore());
        }
    }

    @Override
    public void onTVShowClicked(TVShow tvShow) {
        Intent intent = new Intent(getApplicationContext(), TVShowDetailsActivity.class);
        intent.putExtra(EXTRA_TVSHOW, tvShow);
        startActivity(intent);
    }
}