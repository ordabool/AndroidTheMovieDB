package ordabool.themoviedb.Model;

import java.net.URL;

import ordabool.themoviedb.Handlers.APIHandler;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public URL getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(URL imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Episode(int id, int number, String name, String airDate, String imagePath, String overview) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.airDate = airDate;
        this.imageUrl = APIHandler.getImageUrl(imagePath);
        this.overview = overview;
    }
}
