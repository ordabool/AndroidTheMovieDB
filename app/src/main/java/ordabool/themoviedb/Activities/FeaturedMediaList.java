package ordabool.themoviedb.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;

import ordabool.themoviedb.Adapters.FeaturedMediaListAdapter;
import ordabool.themoviedb.AsyncFunctions.GetMoviesGenres;
import ordabool.themoviedb.AsyncFunctions.GetTVShowsGenres;
import ordabool.themoviedb.Handlers.AppManager;
import ordabool.themoviedb.R;

public class FeaturedMediaList extends AppCompatActivity {
    public static ListView featuredListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_featured_media_list);
        featuredListView = findViewById(R.id.featuredMediaList);

        GetMoviesGenres getMoviesGenres = new GetMoviesGenres();
        getMoviesGenres.execute();

        GetTVShowsGenres getTVShowsGenres = new GetTVShowsGenres();
        getTVShowsGenres.execute();

        String mediaType;
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            mediaType = extras.getString("mediaType");
            switch (mediaType) {
                case "Movies":
                    this.setTitle("In Theaters");
                    featuredListView.setAdapter(new FeaturedMediaListAdapter(this, AppManager.shared.getNowPlayingMovies()));
                    break;
                case "TVShows":
                    this.setTitle("On TV");
                    featuredListView.setAdapter(new FeaturedMediaListAdapter(this, AppManager.shared.getOnAirTVShows()));
                    break;
            }

        }
    }
}
