package ordabool.themoviedb.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), TVShowActivity.class);
                TVShowActivity.tvShow = AppManager.shared.getPopularTVShow()[i];
                startActivity(intent);
            }
        });
        setGridAdapter();
    }

    public static void setGridAdapter() {
        gridView.setAdapter(new MediaGridAdapter(AppManager.shared.getPopularTVShow(), context));
        gridView.invalidateViews();
    }
}
