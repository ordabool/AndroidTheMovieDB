package ordabool.themoviedb.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import ordabool.themoviedb.AsyncFunctions.GetImageFromURL;
import ordabool.themoviedb.Model.Media;
import ordabool.themoviedb.R;

/**
 * Created by Or on 21/01/2018.
 */

public class MediaGridAdapter extends BaseAdapter {

    public MediaGridAdapter(Media[] media, Context context) {
        this.media = media;
        this.context = context;
    }

    Media[] media;
    Context context;

    @Override
    public int getCount() {
        if (media != null){
            return media.length;
        }
        return 0;
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
        View customView = layoutInflater.inflate(R.layout.grid_view_media_cell, viewGroup, false);
        ImageView mediaImageView = customView.findViewById(R.id.gridMediaImageView);
        mediaImageView.setImageResource(R.mipmap.placeholder);
        TextView mediaTitleTextView = customView.findViewById(R.id.gridMediaTitleTextView);
        try {
            mediaTitleTextView.setText(media[i].getTitle());
            GetImageFromURL getImageFromURL = new GetImageFromURL(mediaImageView);
            getImageFromURL.execute(media[i].getImageUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customView;
    }
}
