package ordabool.themoviedb.Model;

import java.net.URL;

import ordabool.themoviedb.Handlers.APIHandler;

/**
 * Created by Or on 16/01/2018.
 */

public class Season {
    int id;
    int number;
    URL imageUrl;
    String releaseDate;
    Episode[] episodes;

    public Season(int id, int number, String imagePath, String releaseDate, Episode[] episodes) {
        this.id = id;
        this.number = number;
        this.imageUrl = APIHandler.getImageUrl(imagePath);
        this.releaseDate = releaseDate;
        this.episodes = episodes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public URL getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(URL imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Episode[] getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Episode[] episodes) {
        this.episodes = episodes;
    }
}
