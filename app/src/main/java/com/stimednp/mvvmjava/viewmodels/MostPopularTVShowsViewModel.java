package com.stimednp.mvvmjava.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.stimednp.mvvmjava.repositories.MostPopularTVShowRepository;
import com.stimednp.mvvmjava.responses.TVShowResponse;

public class MostPopularTVShowsViewModel extends ViewModel {

    private MostPopularTVShowRepository mostPopularTVShowRepository;

    public MostPopularTVShowsViewModel() {
        mostPopularTVShowRepository = new MostPopularTVShowRepository();
    }

    public LiveData<TVShowResponse> getMostPopularTVShows(int page) {
        return mostPopularTVShowRepository.getMostPopularTvShows(page);
    }
}
