package ordabool.themoviedb.Model;

import java.net.URL;

/**
 * Created by Or on 16/01/2018.
 */

public class Episode {
    int id;
    int number;
    String name;
    String airDate;
    URL imageUrl;
    String overview;

    public Episode(int id, int number, String name, String airDate, URL imageUrl, String overview) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.airDate = airDate;
        this.imageUrl = imageUrl;
        this.overview = overview;
    }
}
