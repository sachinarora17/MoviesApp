package example.com.moviesapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MoviesResults implements Parcelable {

    private String backdrop_path;
    private int id;
    private String original_language;
    private String original_title;
    private String overview;
    private String poster_path;
    private String release_date;
    private String title;
    private float vote_average;
    private float vote_count;
    private float popularity;

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public float getVote_count() {
        return vote_count;
    }

    public void setVote_count(float vote_count) {
        this.vote_count = vote_count;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.backdrop_path);
        dest.writeInt(this.id);
        dest.writeString(this.original_language);
        dest.writeString(this.original_title);
        dest.writeString(this.overview);
        dest.writeString(this.poster_path);
        dest.writeString(this.release_date);
        dest.writeString(this.title);
        dest.writeFloat(this.vote_average);
        dest.writeFloat(this.vote_count);
        dest.writeFloat(this.popularity);
    }

    public MoviesResults() {
    }

    protected MoviesResults(Parcel in) {
        this.backdrop_path = in.readString();
        this.id = in.readInt();
        this.original_language = in.readString();
        this.original_title = in.readString();
        this.overview = in.readString();
        this.poster_path = in.readString();
        this.release_date = in.readString();
        this.title = in.readString();
        this.vote_average = in.readFloat();
        this.vote_count = in.readFloat();
        this.popularity = in.readFloat();
    }

    public static final Parcelable.Creator<MoviesResults> CREATOR = new Parcelable.Creator<MoviesResults>() {
        @Override
        public MoviesResults createFromParcel(Parcel source) {
            return new MoviesResults(source);
        }

        @Override
        public MoviesResults[] newArray(int size) {
            return new MoviesResults[size];
        }
    };
}