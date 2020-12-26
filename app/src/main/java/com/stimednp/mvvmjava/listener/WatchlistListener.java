package com.stimednp.mvvmjava.listener;

import com.stimednp.mvvmjava.model.TVShow;

public interface WatchlistListener {
    void onTVShowClicked(TVShow tvShow);

    void removeTVShowFromWatchlist(TVShow tvShow, int position);
}
