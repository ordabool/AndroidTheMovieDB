package ordabool.themoviedb.Model;

/**
 * Created by Or on 16/01/2018.
 */

public class TVShow extends Media {
    Season[] seasons;
    int numberOfSeasons;

    public TVShow(String title, int id, String releaseDate, String imagePath, float voteAvg, String overview, int[] genres, Season[] seasons, int numberOfSeasons) {
        super(title, id, releaseDate, imagePath, voteAvg, overview, genres);
        this.seasons = seasons;
        this.numberOfSeasons = numberOfSeasons;
    }

    public Season[] getSeasons() {
        return seasons;
    }

    public void setSeasons(Season[] seasons) {
        this.seasons = seasons;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }
}
