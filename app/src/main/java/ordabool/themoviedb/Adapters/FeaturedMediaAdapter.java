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

import ordabool.themoviedb.AsyncFunctions.GetImageFromURL;
import ordabool.themoviedb.Handlers.AppManager;
import ordabool.themoviedb.R;

/**
 * Created by Or on 17/01/2018.
 */

public class FeaturedMediaAdapter extends ArrayAdapter<String> {
    public FeaturedMediaAdapter(@NonNull Context context, String[] featuredMedia) {
        super(context, R.layout.now_playing_media_cell ,featuredMedia);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View customView = layoutInflater.inflate(R.layout.now_playing_media_cell, parent, false);
        TextView title = customView.findViewById(R.id.cellTitle);
        ImageView firstImage = customView.findViewById(R.id.firstImage);
        ImageView secondImage = customView.findViewById(R.id.secondImage);
        TextView description = customView.findViewById(R.id.description);
        final int numOfMediaInDescription = 5;
        String descriptionText = "";

        switch (getItem(position)) {
            case "Movies":
                title.setText("In Theaters");
                try {
                    for(int i = 0; i < AppManager.shared.getNowPlayingMovies().length; i++){
                        if (i == numOfMediaInDescription) {
                            break;
                        }
                        descriptionText += AppManager.shared.getNowPlayingMovies()[i].getTitle() + ", ";
                    }
                    GetImageFromURL getImageFromURL = new GetImageFromURL(firstImage);
                    getImageFromURL.execute(AppManager.shared.getNowPlayingMovies()[0].getImageUrl());
                    getImageFromURL = new GetImageFromURL(secondImage);
                    getImageFromURL.execute(AppManager.shared.getNowPlayingMovies()[1].getImageUrl());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                descriptionText += "and more..";
                description.setText(descriptionText);
                break;
            case "TVShows":
                title.setText("On TV");
                try {
                    for(int i = 0; i < AppManager.shared.getOnAirTVShows().length; i++){
                        if (i == numOfMediaInDescription) {
                            break;
                        }
                        descriptionText += AppManager.shared.getOnAirTVShows()[i].getTitle() + ", ";
                    }
                    GetImageFromURL getImageFromURL = new GetImageFromURL(firstImage);
                    getImageFromURL.execute(AppManager.shared.getOnAirTVShows()[0].getImageUrl());
                    getImageFromURL = new GetImageFromURL(secondImage);
                    getImageFromURL.execute(AppManager.shared.getOnAirTVShows()[1].getImageUrl());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                descriptionText += "and more..";
                description.setText(descriptionText);
                break;
        }
        return customView;
    }
}
