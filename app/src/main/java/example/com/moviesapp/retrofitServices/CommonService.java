package example.com.moviesapp.retrofitServices;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import example.com.moviesapp.model.GenreListData;
import example.com.moviesapp.model.MovieListData;
import example.com.moviesapp.interfaces.ApiInterface;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static example.com.moviesapp.utility.AppConstants.sMainUrl;

public class CommonService {

    private static Retrofit retrofit = null;

    public static Retrofit getRetrofitClient() {
        Gson gson = new GsonBuilder().setLenient().create();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(sMainUrl)
                    .client(new OkHttpClient.Builder()
                            .connectTimeout(60, TimeUnit.SECONDS)
                            .writeTimeout(60, TimeUnit.SECONDS)
                            .readTimeout(60, TimeUnit.SECONDS)
                            .build())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

    /**
     * @Module class/module		:	getGenreList
     * @Author Name                :	Sachin Arora
     * @Date :	Sept 17 , 2018
     * @Purpose :	This method return the MutableLiveData for Genre list
     */
    public LiveData<GenreListData> getGenreList() {

        final MutableLiveData<GenreListData> liveUserResponse = new MutableLiveData<>();

        getRetrofitClient()
                .create(ApiInterface.class)
                .getGenreList()
                .enqueue(new Callback<GenreListData>() {

                    @Override
                    public void onResponse(Call<GenreListData> call, Response<GenreListData> response) {

                        GenreListData genreListData = response.body();
                        liveUserResponse.setValue(genreListData);
                    }

                    @Override
                    public void onFailure(Call<GenreListData> call, Throwable t) {
                        Log.e("on Failure :", "retrofit error");

                    }
                });

        return liveUserResponse;
    }

    /**
     * @Module class/module		:	getMovieList
     * @Author Name                :	Sachin Arora
     * @Date :	Sept 17 , 2018
     * @Purpose :	This method return the MutableLiveData for Movie list
     */
    public LiveData<MovieListData> getMovieList(String genreId, Map<String, String> data) {

        final MutableLiveData<MovieListData> liveUserResponse = new MutableLiveData<>();

        getRetrofitClient()
                .create(ApiInterface.class)
                .getMovieList(genreId, data)
                .enqueue(new Callback<MovieListData>() {
                    @Override
                    public void onResponse(Call<MovieListData> call, Response<MovieListData> response) {

                        MovieListData movieListData = response.body();
                        liveUserResponse.setValue(movieListData);
                    }

                    @Override
                    public void onFailure(Call<MovieListData> call, Throwable t) {
                        Log.e("on Failure :", "retrofit error");
                        Log.e("call", call.toString());
                        Log.e("t is", t.getMessage());

                    }
                });

        return liveUserResponse;
    }
}
