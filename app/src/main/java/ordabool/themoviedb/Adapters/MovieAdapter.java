package ordabool.themoviedb.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

import ordabool.themoviedb.AsyncFunctions.GetImageFromURL;
import ordabool.themoviedb.Handlers.AppManager;
import ordabool.themoviedb.Model.Movie;
import ordabool.themoviedb.R;

/**
 * Created by Or on 21/01/2018.
 */

public class MovieAdapter extends BaseAdapter {

    Movie movie;
    Context context;

    public MovieAdapter(Movie movie, Context context) {
        this.movie = movie;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (movie.getVideos() != null) {
            return movie.getVideos().length+1;
        } else {
            return 1;
        }
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (i == 0){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View customView = layoutInflater.inflate(R.layout.movie_base_cell, viewGroup, false);
            ImageView movieImageView = customView.findViewById(R.id.movieImageView);
            TextView movieTitleTextView = customView.findViewById(R.id.movieTitleTextView);
            TextView movieAverageRatingTextView = customView.findViewById(R.id.movieAverageRatingTextView);
            TextView movieReleaseDateTextView = customView.findViewById(R.id.movieReleaseDateTextView);
            TextView movieGenresTextView = customView.findViewById(R.id.movieGenresTextView);
            TextView movieOverviewTextView = customView.findViewById(R.id.movieOverviewTextView);
            String movieGenresText = "|";
            try {
                movieTitleTextView.setText(movie.getTitle());
                movieAverageRatingTextView.setText("Average Rating: " + movie.getVoteAvg());
                movieReleaseDateTextView.setText("Release Date: " + movie.getReleaseDate());
                movieOverviewTextView.setText(movie.getOverview());
                GetImageFromURL getImageFromURL = new GetImageFromURL(movieImageView);
                getImageFromURL.execute(movie.getImageUrl());
                if(movie.getGenres() != null) {
                    for (int j=0; j<movie.getGenres().length; j++){
                        for (int k = 0; k< AppManager.shared.getMoviesGenres().length(); k++){
                            JSONObject job = (JSONObject) AppManager.shared.getMoviesGenres().get(k);
                            if ((int)job.get("id") == movie.getGenres()[j]){
                                movieGenresText += " " + job.get("name") + " |";
                                break;
                            }
                        }
                    }
                    movieGenresTextView.setText(movieGenresText);
                }

                //movieGenresTextView.setText(movieGenresText);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return customView;
        }
        return null;
    }
}
