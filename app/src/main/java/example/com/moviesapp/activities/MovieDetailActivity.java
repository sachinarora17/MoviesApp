/**
 * @Module Name/Class		:	MovieDetailActivity
 * @Author Name            :	Sachin Arora
 * @Date :	Sept 17, 2018
 */

package example.com.moviesapp.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;

import java.util.Locale;

import example.com.moviesapp.R;
import example.com.moviesapp.databinding.ActivityMovieDetailBinding;
import example.com.moviesapp.model.MoviesResults;
import example.com.moviesapp.utility.AppConstants;
import example.com.moviesapp.utility.AppPreferences;

public class MovieDetailActivity extends BaseActivity {

    private ActivityMovieDetailBinding movieDetailBinding;
    private MoviesResults movieData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

        setToolbarHeading();

        fillViews();

    }

    /**
     * @param
     * @Module Name/Class		:	init
     * @Author Name            :	Sachin Arora
     * @Date :	Sept 17, 2018
     * @Purpose :	Initializing the UI
     */

    private void init() {
        movieDetailBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_movie_detail, activityBaseBinding.contentFrame, true);

        movieData = getIntent().getParcelableExtra(AppConstants.sMovieData);
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
        activityBaseBinding.tvToolbarWriteNoteTitle.setText(getString(R.string.details));

    }

    /**
     * @param
     * @Module Name/Class		:	fillViews
     * @Author Name            :	Sachin Arora
     * @Date :	Sept 17, 2018
     * @Purpose :	Calling method to set text
     */

    private void fillViews() {
        AppPreferences.setImageInImageView(AppConstants.sPosterImageUrl + movieData.getPoster_path(), movieDetailBinding.includeLayout.imgMoviePoster, this, movieDetailBinding.includeLayout.pbMovieView);

        AppPreferences.setImageInImageView(AppConstants.sCoverImageUrl + movieData.getBackdrop_path(), movieDetailBinding.imgCover, this, movieDetailBinding.pbCoverImage);

        setTextInTextView(movieDetailBinding.includeLayout.iconViewItemName, movieData.getOriginal_title());

        setTextInTextView(movieDetailBinding.tvDescription, movieData.getOverview());

        setTextInTextView(movieDetailBinding.tvLanguage, getLocaleNameFromLanguage(movieData.getOriginal_language()));

        setTextInTextView(movieDetailBinding.tvPopularity, movieData.getVote_average() + " Rating");

        setTextInTextView(movieDetailBinding.tvReleaseDate, movieData.getRelease_date());

    }

    /**
     * @param
     * @Module Name/Class		:	getLocaleNameFromLanguage
     * @Author Name            :	Sachin Arora
     * @Date :	Sept 17, 2018
     * @Purpose :	Get language name from language code
     */

    private String getLocaleNameFromLanguage(String original_language) {
        Locale loc = new Locale(original_language);
        return loc.getDisplayLanguage(loc);
    }

    /**
     * @param
     * @Module Name/Class		:	setTextInTextView
     * @Author Name            :	Sachin Arora
     * @Date :	Sept 17, 2018
     * @Purpose :	setting text in text view
     */

    private void setTextInTextView(TextView textView, String text) {
        textView.setText(text);
    }
}
