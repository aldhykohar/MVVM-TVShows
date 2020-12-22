package com.stimednp.mvvmjava.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.stimednp.mvvmjava.R;
import com.stimednp.mvvmjava.viewmodels.MostPopularTVShowsViewModel;

public class MainActivity extends AppCompatActivity {

    private MostPopularTVShowsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MostPopularTVShowsViewModel.class);
        getMostPopularTVShows();

        Toast.makeText(this, "Add Toast", Toast.LENGTH_SHORT).show();
    }

    private void getMostPopularTVShows() {
        viewModel.getMostPopularTVShows(0).observe(this, mostPopularTVShowsResponse ->
                Toast.makeText(getApplicationContext(),
                        "Total Pages : " + mostPopularTVShowsResponse.getTotalPages(),
                        Toast.LENGTH_SHORT
                ).show()
        );
    }
}