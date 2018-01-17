package ordabool.themoviedb.Handlers;

import org.json.JSONArray;

import ordabool.themoviedb.Model.Movie;
import ordabool.themoviedb.Model.TVShow;

/**
 * Created by Or on 16/01/2018.
 */

public class AppManager {

    protected AppManager() {
    }

    public static AppManager shared = new AppManager();

    final public String[] featuredMediaOrder = {"Movies", "TVShows"};

    protected Movie[] nowPlayingMovies = null;
    protected TVShow[] onAirTVShows = null;
    protected JSONArray moviesGenres = null;
    protected JSONArray tvShowsGenres = null;

    public JSONArray getMoviesGenres() {
        return moviesGenres;
    }

    public void setMoviesGenres(JSONArray moviesGenres) {
        this.moviesGenres = moviesGenres;
    }

    public JSONArray getTvShowsGenres() {
        return tvShowsGenres;
    }

    public void setTvShowsGenres(JSONArray tvShowsGenres) {
        this.tvShowsGenres = tvShowsGenres;
    }

    public TVShow[] getOnAirTVShows() { return onAirTVShows; }

    public void setOnAirTVShows(TVShow[] onAirTVShows) {
        this.onAirTVShows = onAirTVShows;
    }

    public Movie[] getNowPlayingMovies() {
        return nowPlayingMovies;
    }

    public void setNowPlayingMovies(Movie[] nowPlayingMovies) {
        this.nowPlayingMovies = nowPlayingMovies;
    }
}
