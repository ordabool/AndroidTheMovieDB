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
import ordabool.themoviedb.Model.TVShow;
import ordabool.themoviedb.R;

/**
 * Created by Or on 21/01/2018.
 */

public class TVShowAdapter extends BaseAdapter {

    TVShow tvShow;
    Context context;

    public TVShowAdapter(TVShow tvShow, Context context) {
        this.tvShow = tvShow;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (tvShow.getSeasons() == null) {
            return 1;
        } else {
            return tvShow.getSeasons().length+1;
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
            View customView = layoutInflater.inflate(R.layout.tvshow_base_cell, viewGroup, false);
            ImageView tvShowImageView = customView.findViewById(R.id.tvShowImageView);
            TextView tvShowTitleTextView = customView.findViewById(R.id.tvShowTitleTextView);
            TextView tvShowAverageRatingTextView = customView.findViewById(R.id.tvShowAverageRatingTextView);
            TextView tvShowReleaseDateTextView = customView.findViewById(R.id.tvShowReleaseDateTextView);
            TextView tvShowGenresTextView = customView.findViewById(R.id.tvShowGenresTextView);
            TextView tvShowOverviewTextView = customView.findViewById(R.id.tvShowOverviewTextView);
            TextView tvNumOfSeasonsTextView = customView.findViewById(R.id.numOfSeasonsTextView);
            String tvShowGenresText = "|";
            try {
                tvShowTitleTextView.setText(tvShow.getTitle());
                tvShowAverageRatingTextView.setText("Average Rating: " + tvShow.getVoteAvg());
                tvShowReleaseDateTextView.setText("Release Date: " + tvShow.getReleaseDate());
                tvShowOverviewTextView.setText(tvShow.getOverview());
                GetImageFromURL getImageFromURL = new GetImageFromURL(tvShowImageView);
                getImageFromURL.execute(tvShow.getImageUrl());
                if (tvShow.getNumberOfSeasons() != 0){
                    tvNumOfSeasonsTextView.setText(tvShow.getNumberOfSeasons() + " seasons");
                }
                if(tvShow.getGenres() != null) {
                    for (int j=0; j<tvShow.getGenres().length; j++){
                        for (int k = 0; k< AppManager.shared.getTvShowsGenres().length(); k++){
                            JSONObject job = (JSONObject) AppManager.shared.getTvShowsGenres().get(k);
                            if ((int)job.get("id") == tvShow.getGenres()[j]){
                                tvShowGenresText += " " + job.get("name") + " |";
                                break;
                            }
                        }
                    }
                    tvShowGenresTextView.setText(tvShowGenresText);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return customView;
        }

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View customView = layoutInflater.inflate(R.layout.video_base_cell, viewGroup, false);
        TextView seasonNameTextView = customView.findViewById(R.id.videoNameTextView);
        if (tvShow.getSeasons()[i-1].getNumber() == 0) {
            seasonNameTextView.setText("Specials");
        } else {
            seasonNameTextView.setText("Season " + tvShow.getSeasons()[i-1].getNumber());
        }
        return customView;

    }
}
