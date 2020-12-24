package com.stimednp.mvvmjava.network;

import com.stimednp.mvvmjava.responses.TVShowDetailsResponse;
import com.stimednp.mvvmjava.responses.TVShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("most-popular")
    Call<TVShowResponse> getMostPopularTVShows(@Query("page") int page);

    @GET("show-details")
    Call<TVShowDetailsResponse> getTVShowDetails(@Query("q") String tvShowId);
}
