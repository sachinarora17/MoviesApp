package example.com.moviesapp.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.Map;

import example.com.moviesapp.model.MovieListData;
import example.com.moviesapp.retrofitServices.CommonService;

public class MovieListViewModel extends ViewModel {

    private CommonService mCommonService = new CommonService();

    public LiveData<MovieListData> getMovieList(String genreId, Map<String, String> data) {
        return mCommonService.getMovieList(genreId, data);
    }
}
