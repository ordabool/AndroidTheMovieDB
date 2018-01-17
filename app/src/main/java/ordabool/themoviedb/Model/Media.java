package ordabool.themoviedb.Model;

import java.net.URL;

import ordabool.themoviedb.Handlers.APIHandler;

/**
 * Created by Or on 16/01/2018.
 */

public class Media {
    String title;
    int id;
    String releaseDate;
    URL imageUrl;
    float voteAvg;
    String overview;
    int[] genres;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public URL getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(URL imageUrl) {
        this.imageUrl = imageUrl;
    }

    public float getVoteAvg() {
        return voteAvg;
    }

    public void setVoteAvg(float voteAvg) {
        this.voteAvg = voteAvg;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int[] getGenres() {
        return genres;
    }

    public void setGenres(int[] genres) {
        this.genres = genres;
    }

    public Media(String title, int id, String releaseDate, String imagePath, float voteAvg, String overview, int[] genres) {
        this.title = title;
        this.id = id;
        this.releaseDate = releaseDate;
        this.voteAvg = voteAvg;
        this.overview = overview;
        this.genres = genres;
        this.imageUrl = APIHandler.getImageUrl(imagePath);
    }
}
