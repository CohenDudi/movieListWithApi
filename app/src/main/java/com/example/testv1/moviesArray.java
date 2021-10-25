package com.example.testv1;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class moviesArray {
    @SerializedName("movies")
    private List<movie> movies;

    public moviesArray() {
    }

    public List<movie> getMovies() {
        return movies;
    }

    public void setMovies(List<movie> movies) {
        this.movies = movies;
    }

    public moviesArray(List<movie> movies) {
        this.movies = movies;
    }
}
