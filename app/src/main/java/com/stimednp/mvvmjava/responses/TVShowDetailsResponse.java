package com.stimednp.mvvmjava.responses;

import com.google.gson.annotations.SerializedName;
import com.stimednp.mvvmjava.model.TVShowDetails;

public class TVShowDetailsResponse {

    public TVShowDetails getTvShowDetails() {
        return tvShowDetails;
    }

    @SerializedName("tvShow")
    private TVShowDetails tvShowDetails;
}
