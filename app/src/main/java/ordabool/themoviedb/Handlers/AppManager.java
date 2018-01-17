package ordabool.themoviedb.Handlers;

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
