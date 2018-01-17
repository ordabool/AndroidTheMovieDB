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

    public static String getNowPlayingMoviesUrlString() {
        String urlString = APIHandler.requestBaseUrl + APIHandler.nowPlayingMoviesConst + APIHandler.APIKey;
        return  urlString;
    }

    public static String getOnAirTVShowsUrlString() {
        String urlString = APIHandler.requestBaseUrl + APIHandler.onAirTVShowsConst + APIHandler.APIKey;
        return  urlString;
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
