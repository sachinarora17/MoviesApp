/**
 * @Module Name/Class		:	MovieListActivity
 * @Author Name            :	Sachin Arora
 * @Date :	Sept 17, 2018
 */

package example.com.moviesapp.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import example.com.moviesapp.R;
import example.com.moviesapp.adapter.MoviesAdapter;
import example.com.moviesapp.databinding.ActivityMainBinding;
import example.com.moviesapp.interfaces.ClickListenerInterface;
import example.com.moviesapp.model.GenreListData;
import example.com.moviesapp.model.MoviesResults;
import example.com.moviesapp.utility.AppConstants;
import example.com.moviesapp.viewModel.GenreListViewModel;
import example.com.moviesapp.viewModel.MovieListViewModel;

public class MovieListActivity extends BaseActivity implements ClickListenerInterface {

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

        setToolbarHeading();

        getGenreList();

    }

    /**
     * @param
     * @Module Name/Class		:	init
     * @Author Name            :	Sachin Arora
     * @Date :	Sept 17, 2018
     * @Purpose :	Initializing the UI
     */

    private void init() {
        activityMainBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_main, activityBaseBinding.contentFrame, true);

    }

    /**
     * @param
     * @Module Name/Class		:	setToolbarHeading
     * @Author Name            :	Sachin Arora
     * @Date :	Sept 17, 2018
     * @Purpose :	Set Heading in toolbar
     */

    @Override
    public void setToolbarHeading() {
        activityBaseBinding.tvToolbarWriteNoteTitle.setText(getString(R.string.movies_list));

    }

    /**
     * @param
     * @Module Name/Class		:	getGenreList
     * @Author Name            :	Sachin Arora
     * @Date :	Sept 17, 2018
     * @Purpose :	Hit API and get genre list
     */

    private void getGenreList() {

        showProgress();

        GenreListViewModel genreListViewModel = ViewModelProviders.of(this).get(GenreListViewModel.class);

        genreListViewModel
                .getGenreList()
                .observe(this, genreListData -> {

                    if (genreListData.getGenres().size() > 0) {
                        for (int i = 0; i < genreListData.getGenres().size(); i++) {
                            addView(genreListData, i);

                        }
                    }
                });
    }

    /**
     * @param
     * @Module Name/Class		:	addView
     * @Author Name            :	Sachin Arora
     * @Date :	Sept 17, 2018
     * @Purpose :	Adding Views according to size of genre list
     */

    private void addView(GenreListData genreListData, int i) {

        String name = genreListData.getGenres().get(i).getName();
        String id = genreListData.getGenres().get(i).getId();

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.movie_list_layout, null);

        TextView tvGenreName = view.findViewById(R.id.tvGenreName);
        RecyclerView moviesRecyclerView = view.findViewById(R.id.rvMoviesList);

        tvGenreName.setText(name);
        moviesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        getMovieList(id, moviesRecyclerView, genreListData, i);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        view.setLayoutParams(layoutParams);
        activityMainBinding.llView.addView(view);
    }

    /**
     * @param
     * @Module Name/Class		:	getMovieList
     * @Author Name            :	Sachin Arora
     * @Date :	Sept 17, 2018
     * @Purpose :	Hit API and get Movie list according to genre
     */

    private void getMovieList(String id, RecyclerView moviesRecyclerView, GenreListData genreListData, int i) {

        Map<String, String> data = new HashMap<>();
        data.put("api_key", "f17e9c5e6c34ad9dc2bf6aab852c0cc7");
        data.put("language", "en-US");
        data.put("page", "1");

        MovieListViewModel movieListViewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);

        movieListViewModel
                .getMovieList(id, data)
                .observe(this, movieListData -> {

                    if (i == genreListData.getGenres().size() - 1)
                        hideProgress();

                    if (movieListData.getResults().size() > 0) {
                        setRecyclerAdapter(moviesRecyclerView, movieListData.getResults());
                    }

                });
    }

    /**
     * @param
     * @Module Name/Class		:	setRecyclerAdapter
     * @Author Name            :	Sachin Arora
     * @Date :	Sept 17, 2018
     * @Purpose :	Setting Adapter to recycler view
     */

    private void setRecyclerAdapter(RecyclerView moviesRecyclerView, ArrayList<MoviesResults> movieListData) {
        moviesRecyclerView.setAdapter(new MoviesAdapter(this, movieListData, this));

    }

    /**
     * @param
     * @Module Name/Class		:	showProgress
     * @Author Name            :	Sachin Arora
     * @Date :	Sept 17, 2018
     * @Purpose :	Showing the progress bar to user
     */

    private void showProgress() {
        activityMainBinding.scrollView.setVisibility(View.GONE);
        activityMainBinding.llProgressView.setVisibility(View.VISIBLE);

    }

    /**
     * @param
     * @Module Name/Class		:	hideProgress
     * @Author Name            :	Sachin Arora
     * @Date :	Sept 17, 2018
     * @Purpose :	Showing the progress bar from user
     */

    private void hideProgress() {
        activityMainBinding.scrollView.setVisibility(View.VISIBLE);
        activityMainBinding.llProgressView.setVisibility(View.GONE);

    }

    /**
     * @param
     * @Module Name/Class		:	onClick
     * @Author Name            :	Sachin Arora
     * @Date :	Sept 17, 2018
     * @Purpose :	Interface callback method when user tap on movie name
     */

    @Override
    public void onClick(MoviesResults movieData) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra(AppConstants.sMovieData, movieData);
        startActivity(intent);

    }
}
