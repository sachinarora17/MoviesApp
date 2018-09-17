package example.com.moviesapp.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import example.com.moviesapp.model.GenreListData;
import example.com.moviesapp.retrofitServices.CommonService;

public class GenreListViewModel extends ViewModel {

    private CommonService mCommonService = new CommonService();

    public LiveData<GenreListData> getGenreList() {
        return mCommonService.getGenreList();
    }
}
