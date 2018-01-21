package ordabool.themoviedb.Handlers;

import java.net.URL;

/**
 * Created by Or on 16/01/2018.
 */

public class APIHandler {
    final static String requestBaseUrl = "https://api.themoviedb.org/3/";
    final static String APIKey = "api_key=8733c01dbdcb1ed64534dd396e5ee532";

    final static String imageBaseUrl = "https://image.tmdb.org/t/p/w300";
    final static String youtubeBaseUrl = "https://www.youtube.com/watch?v=";

    final static String nowPlayingMoviesConst = "movie/now_playing?";
    final static String onAirTVShowsConst = "tv/on_the_air?";
    final static String popularTVShowsConst = "tv/popular?";
    final static String popularMoviesConst = "movie/popular?";

    final static String moviesGenresConst = "genre/movie/list?";
    final static String tvShowsGenresConst = "genre/tv/list?";

    final static String movieConst = "movie/";
    final static String movieVideosConst = "/videos?";

    public static String getVideoUrlString(String key){
        return youtubeBaseUrl + key;
    }

    public static String getMovieVideosUrlString(int id){
        return requestBaseUrl + movieConst + id + movieVideosConst + APIKey;
    }

    public static String getNowPlayingMoviesUrlString() {
        return  requestBaseUrl + nowPlayingMoviesConst + APIKey;
    }

    public static String getOnAirTVShowsUrlString() {
        return  requestBaseUrl + onAirTVShowsConst + APIKey;
    }

    public static String getMoviesGenresUrlString() {
        return requestBaseUrl + moviesGenresConst + APIKey;
    }

    public static String getTVShowsGenresUrlString() {
        return requestBaseUrl + tvShowsGenresConst + APIKey;
    }

    public static String getPopularTVShowsUrlString() {
        return requestBaseUrl + popularTVShowsConst + APIKey;
    }

    public static String getPopularMoviesUrlString() {
        return requestBaseUrl + popularMoviesConst + APIKey;
    }

    public static URL getImageUrl(String path){
        String urlString = APIHandler.imageBaseUrl + path;
        try {
            URL url = new URL(urlString);
            return  url;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
