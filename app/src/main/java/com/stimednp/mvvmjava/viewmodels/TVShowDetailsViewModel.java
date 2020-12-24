package com.stimednp.mvvmjava.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.stimednp.mvvmjava.repositories.TVShowDetailsRepository;
import com.stimednp.mvvmjava.responses.TVShowDetailsResponse;

public class TVShowDetailsViewModel extends ViewModel {
    private TVShowDetailsRepository tvShowDetailsRepository;

    public TVShowDetailsViewModel() {
        tvShowDetailsRepository = new TVShowDetailsRepository();
    }

    public LiveData<TVShowDetailsResponse> getTVShowDetails(String tvShowId) {
        return tvShowDetailsRepository.getTVShowDetail(tvShowId);
    }
}
