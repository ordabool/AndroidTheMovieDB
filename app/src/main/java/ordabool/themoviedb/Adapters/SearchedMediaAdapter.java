package ordabool.themoviedb.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

import ordabool.themoviedb.Activities.SearchActivity;
import ordabool.themoviedb.AsyncFunctions.GetImageFromURL;
import ordabool.themoviedb.Handlers.AppManager;
import ordabool.themoviedb.Model.Media;
import ordabool.themoviedb.Model.Movie;
import ordabool.themoviedb.Model.TVShow;
import ordabool.themoviedb.R;

/**
 * Created by Or on 28/01/2018.
 */

public class SearchedMediaAdapter extends BaseAdapter {
    Context context;

    public SearchedMediaAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        if (AppManager.shared.getSearchResult() != null){
            return AppManager.shared.getSearchResult().length;
        } else {
            return 0;
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
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View customView = layoutInflater.inflate(R.layout.featured_media_list_cell, viewGroup, false);
        ImageView mediaImageView = customView.findViewById(R.id.mediaImageView);
        TextView mediaTitleTextView = customView.findViewById(R.id.mediaTitleTextView);
        TextView mediaAverageRatingTextView = customView.findViewById(R.id.mediaAverageRatingTextView);
        TextView mediaReleaseDateTextView = customView.findViewById(R.id.mediaReleaseDateTextView);
        TextView mediaGenresTextView = customView.findViewById(R.id.mediaGenresTextView);

        try {
            Media media = AppManager.shared.getSearchResult()[i];
            GetImageFromURL getImageFromURL = new GetImageFromURL(mediaImageView);
            getImageFromURL.execute(media.getImageUrl());
            mediaTitleTextView.setText(media.getTitle());
            mediaAverageRatingTextView.setText("Average Rating: " + media.getVoteAvg());
            mediaReleaseDateTextView.setText("Release Date: " + media.getReleaseDate());
            String mediaGenresText = "|";
            for (int k=0; k<media.getGenres().length; k++){
                if (media instanceof Movie){
                    for (int j=0; j<AppManager.shared.getMoviesGenres().length(); j++){
                        JSONObject job = (JSONObject) AppManager.shared.getMoviesGenres().get(j);
                        if ((int)job.get("id") == media.getGenres()[k]){
                            mediaGenresText += " " + job.get("name") + " |";
                            break;
                        }
                    }
                }
                if (media instanceof TVShow){
                    for (int j=0; j<AppManager.shared.getTvShowsGenres().length(); j++){
                        JSONObject job = (JSONObject) AppManager.shared.getTvShowsGenres().get(j);
                        if ((int)job.get("id") == media.getGenres()[k]){
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
