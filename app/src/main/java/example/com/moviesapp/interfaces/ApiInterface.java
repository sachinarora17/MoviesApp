package example.com.moviesapp.interfaces;

import java.util.Map;

import example.com.moviesapp.model.GenreListData;
import example.com.moviesapp.model.MovieListData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by sachin arora on 20/3/18.
 */

public interface ApiInterface {

    @GET("movie/list?api_key=f17e9c5e6c34ad9dc2bf6aab852c0cc7&language=en-US")
    Call<GenreListData> getGenreList();

    @GET("{genre_id}/movies")
    Call<MovieListData> getMovieList(@Path("genre_id") String genre_id,
                                     @QueryMap Map<String, String> data);

}
