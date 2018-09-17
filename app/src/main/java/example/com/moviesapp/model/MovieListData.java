package example.com.moviesapp.model;

import java.util.ArrayList;

public class MovieListData {

    private int id;
    private int page;
    private ArrayList<MoviesResults> results;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ArrayList<MoviesResults> getResults() {
        return results;
    }

    public void setResults(ArrayList<MoviesResults> results) {
        this.results = results;
    }
}