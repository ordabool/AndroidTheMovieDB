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
import ordabool.themoviedb.Model.Season;
import ordabool.themoviedb.R;

/**
 * Created by Or on 21/01/2018.
 */

public class SeasonAdapter extends BaseAdapter {

    Season season;
    Context context;
    String tvShowName;

    public SeasonAdapter(Season season, Context context, String tvShowName) {
        this.season = season;
        this.context = context;
        this.tvShowName = tvShowName;
    }

    @Override
    public int getCount() {
        if (season.getEpisodes() == null) {
            return 1;
        } else {
            return season.getEpisodes().length+1;
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
            View customView = layoutInflater.inflate(R.layout.season_base_cell, viewGroup, false);
            ImageView seasonImageView = customView.findViewById(R.id.seasonImageView);
            TextView seasonTitleTextView = customView.findViewById(R.id.seasonTitleTextView);
            TextView seasonNumberTextView = customView.findViewById(R.id.seasonNumberTextView);
            TextView seasonNumOfEpisodes = customView.findViewById(R.id.numOfEpisodesInSeasonTextView);
            try {
                if(season.getEpisodes() != null){
                    seasonNumOfEpisodes.setText(season.getEpisodes().length + " episodes");
                } else {
                    seasonNumOfEpisodes.setText("");
                }
                seasonTitleTextView.setText(tvShowName);
                if (season.getNumber() == 0){
                    seasonNumberTextView.setText("Specials");
                } else {
                    seasonNumberTextView.setText("Season " + season.getNumber());
                }
                GetImageFromURL getImageFromURL = new GetImageFromURL(seasonImageView);
                getImageFromURL.execute(season.getImageUrl());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return customView;
        }

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View customView = layoutInflater.inflate(R.layout.episode_base_cell, viewGroup, false);
        ImageView episodeImageView = customView.findViewById(R.id.episodeImageView);
        TextView episodeNumberTextView = customView.findViewById(R.id.episodeNumberTextView);
        TextView episodeNameTextView = customView.findViewById(R.id.episodeNameTextView);
        TextView episodeReleaseDateTextView = customView.findViewById(R.id.episodeReleaseDateTextView);
        TextView episodeOverviewTextView = customView.findViewById(R.id.episodeOverviewTextView);
        try {
            GetImageFromURL getImageFromURL = new GetImageFromURL(episodeImageView);
            getImageFromURL.execute(season.getEpisodes()[i-1].getImageUrl());
            episodeNumberTextView.setText("Episode " + season.getEpisodes()[i-1].getNumber());
            episodeNameTextView.setText(season.getEpisodes()[i-1].getName());
            if (season.getEpisodes()[i-1].getAirDate() != null){
                episodeReleaseDateTextView.setText(season.getEpisodes()[i-1].getAirDate());
            } else {
                episodeReleaseDateTextView.setText("N/A");
            }
            episodeOverviewTextView.setText(season.getEpisodes()[i-1].getOverview());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return customView;
    }
}
