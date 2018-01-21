package ordabool.themoviedb.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import ordabool.themoviedb.Adapters.MediaGridAdapter;
import ordabool.themoviedb.AsyncFunctions.GetPopularTVShows;
import ordabool.themoviedb.Handlers.AppManager;
import ordabool.themoviedb.R;

public class PopularTVShows extends BaseActivity {

    static GridView gridView;
    static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_popular_tvshows, frameLayout);
        context = getApplicationContext();

        GetPopularTVShows getPopularTVShows = new GetPopularTVShows();
        getPopularTVShows.execute();

        setTitle("Popular TV Shows");

        gridView = findViewById(R.id.popularTVShowsGridView);
        setGridAdapter();
    }

    public static void setGridAdapter() {
        gridView.setAdapter(new MediaGridAdapter(AppManager.shared.getPopularTVShow(), context));
        gridView.invalidateViews();
    }
}
