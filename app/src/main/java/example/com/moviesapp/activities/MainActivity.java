package example.com.moviesapp.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;

import example.com.moviesapp.R;
import example.com.moviesapp.databinding.ActivityMainBinding;
import example.com.moviesapp.viewModel.MovieListViewModel;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

        setToolbarHeading();

        getMoviesList();

    }

    private void getMoviesList() {
        MovieListViewModel movieListViewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);
    }

    private void init() {
        activityMainBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_main, activityBaseBinding.contentFrame, true);

    }

    @Override
    public void setToolbarHeading() {
        activityBaseBinding.tvToolbarWriteNoteTitle.setText(getString(R.string.movies_list));

    }
}
