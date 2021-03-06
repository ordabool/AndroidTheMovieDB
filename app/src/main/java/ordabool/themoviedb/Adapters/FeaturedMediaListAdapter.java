package ordabool.themoviedb.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

import ordabool.themoviedb.AsyncFunctions.GetImageFromURL;
import ordabool.themoviedb.Handlers.AppManager;
import ordabool.themoviedb.Model.Media;
import ordabool.themoviedb.Model.Movie;
import ordabool.themoviedb.Model.TVShow;
import ordabool.themoviedb.R;

/**
 * Created by Or on 17/01/2018.
 */

public class FeaturedMediaListAdapter extends ArrayAdapter<Media> {
    public FeaturedMediaListAdapter(@NonNull Context context, Media[] featuredMedia) {
        super(context, R.layout.featured_media_list_cell ,featuredMedia);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View customView = layoutInflater.inflate(R.layout.featured_media_list_cell, parent, false);
        ImageView mediaImageView = customView.findViewById(R.id.mediaImageView);
        TextView mediaTitleTextView = customView.findViewById(R.id.mediaTitleTextView);
        TextView mediaAverageRatingTextView = customView.findViewById(R.id.mediaAverageRatingTextView);
        TextView mediaReleaseDateTextView = customView.findViewById(R.id.mediaReleaseDateTextView);
        TextView mediaGenresTextView = customView.findViewById(R.id.mediaGenresTextView);

        try {
            Media media = getItem(position);
            GetImageFromURL getImageFromURL = new GetImageFromURL(mediaImageView);
            getImageFromURL.execute(media.getImageUrl());
            mediaTitleTextView.setText(media.getTitle());
            mediaAverageRatingTextView.setText("Average Rating: " + media.getVoteAvg());
            mediaReleaseDateTextView.setText("Release Date: " + media.getReleaseDate());
            String mediaGenresText = "|";
            for (int i=0; i<media.getGenres().length; i++){
                if (media instanceof Movie){
                    for (int j=0; j<AppManager.shared.getMoviesGenres().length(); j++){
                        JSONObject job = (JSONObject) AppManager.shared.getMoviesGenres().get(j);
                        if ((int)job.get("id") == media.getGenres()[i]){
                            mediaGenresText += " " + job.get("name") + " |";
                            break;
                        }
                    }
                }
                if (media instanceof TVShow){
                    for (int j=0; j<AppManager.shared.getTvShowsGenres().length(); j++){
                        JSONObject job = (JSONObject) AppManager.shared.getTvShowsGenres().get(j);
                        if ((int)job.get("id") == media.getGenres()[i]){
                            mediaGenresText += " " + job.get("name") + " |";
                            break;
                        }
                    }
                }

            }
            mediaGenresTextView.setText(mediaGenresText);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return customView;
    }
}
