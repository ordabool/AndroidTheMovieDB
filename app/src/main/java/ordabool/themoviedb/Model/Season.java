package ordabool.themoviedb.Model;

import java.net.URL;

/**
 * Created by Or on 16/01/2018.
 */

public class Season {
    int id;
    int number;
    URL imageUrl;
    String releaseDate;
    Episode[] episodes;

    public Season(int id, int number, URL imageUrl, String releaseDate, Episode[] episodes) {
        this.id = id;
        this.number = number;
        this.imageUrl = imageUrl;
        this.releaseDate = releaseDate;
        this.episodes = episodes;
    }
}
