package example.com.moviesapp.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import example.com.moviesapp.R;
import example.com.moviesapp.databinding.MovieLayoutBinding;
import example.com.moviesapp.interfaces.ClickListenerInterface;
import example.com.moviesapp.model.MoviesResults;
import example.com.moviesapp.utility.AppConstants;
import example.com.moviesapp.utility.AppPreferences;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private ArrayList<MoviesResults> moviesResultsArrayList;
    private AppCompatActivity appCompatActivity;
    private ClickListenerInterface mInterface;

    public MoviesAdapter(AppCompatActivity appCompatActivity, ArrayList<MoviesResults> movieListResults, ClickListenerInterface clickListenerInterface) {
        moviesResultsArrayList = movieListResults;
        this.appCompatActivity = appCompatActivity;
        mInterface = clickListenerInterface;

    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private MovieLayoutBinding mViewDataBinding;

        public MyViewHolder(MovieLayoutBinding viewDataBinding) {
            super(viewDataBinding.getRoot());

            mViewDataBinding = viewDataBinding;
            mViewDataBinding.executePendingBindings();
        }

        public MovieLayoutBinding getViewDataBinding() {
            return mViewDataBinding;
        }
    }


    @Override
    public MoviesAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        MovieLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater
                .from(viewGroup.getContext()), R.layout.movie_layout, viewGroup, false);

        return new MoviesAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        MoviesResults movies = moviesResultsArrayList.get(position);
        MovieLayoutBinding viewDataBinding = holder.getViewDataBinding();

        AppPreferences.setImageInImageView(AppConstants.sPosterImageUrl + movies.getPoster_path(), viewDataBinding.imgMoviePoster, appCompatActivity, viewDataBinding.pbMovieView);
        viewDataBinding.iconViewItemName.setText(movies.getOriginal_title());

        viewDataBinding.cvMovie.setOnClickListener(view -> {
            mInterface.onClick(movies);
        });


    }

    @Override
    public int getItemCount() {
        return moviesResultsArrayList.size();

    }
}
