package ordabool.themoviedb.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import ordabool.themoviedb.Adapters.FeaturedMediaAdapter;
import ordabool.themoviedb.AsyncFunctions.GetNowPlayingMovies;
import ordabool.themoviedb.AsyncFunctions.GetOnAirTVShows;
import ordabool.themoviedb.Handlers.AppManager;
import ordabool.themoviedb.R;

public class MainActivity extends AppCompatActivity {

    public static ListView featuredListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetNowPlayingMovies getNowPlayingMovies = new GetNowPlayingMovies();
        getNowPlayingMovies.execute();

        GetOnAirTVShows getOnAirTVShows = new GetOnAirTVShows();
        getOnAirTVShows.execute();

        featuredListView = findViewById(R.id.featuredMediaListView);
        featuredListView.setAdapter(new FeaturedMediaAdapter(this, AppManager.shared.featuredMediaOrder));
    }
}
