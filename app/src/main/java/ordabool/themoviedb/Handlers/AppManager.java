package ordabool.themoviedb.Handlers;

import ordabool.themoviedb.Model.Movie;

/**
 * Created by Or on 16/01/2018.
 */

public class AppManager {

    protected AppManager() {
    }

    public static AppManager shared = new AppManager();

    final public String[] featuredMediaOrder = {"Movies", "TVShows"};

    protected Movie[] nowPlayingMovies = null;

    public Movie[] getNowPlayingMovies() {
        return nowPlayingMovies;
    }

    public void setNowPlayingMovies(Movie[] nowPlayingMovies) {
        this.nowPlayingMovies = nowPlayingMovies;
    }
}
