package ordabool.themoviedb.Model;

/**
 * Created by Or on 16/01/2018.
 */

public class Movie extends Media {
    Video[] videos;

    public Movie(String title, int id, String releaseDate, String imagePath, float voteAvg, String overview, int[] genres, Video[] videos) {
        super(title, id, releaseDate, imagePath, voteAvg, overview, genres);
        this.videos = videos;
    }

    public Video[] getVideos() {
        return videos;
    }

    public void setVideos(Video[] videos) {
        this.videos = videos;
    }
}
