package com.example.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    String posterPath;
    String title;
    String overview;

    static final String POSTER_PATH_URL = "https://image.tmdb.org/t/p/w342/%s";
    static final String KEY_POSTER_PATH = "poster_path";
    static final String KEY_TITLE = "title";
    static final String KEY_OVERVIEW = "overview";

    public Movie(JSONObject jsonObject) throws JSONException {
        posterPath = jsonObject.getString(KEY_POSTER_PATH);
        title = jsonObject.getString(KEY_TITLE);
        overview = jsonObject.getString(KEY_OVERVIEW);
    }

    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < movieJsonArray.length(); i++) {
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }
        return movies;
    }

    public String getPosterPath() {
        return String.format(POSTER_PATH_URL, posterPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }
}
