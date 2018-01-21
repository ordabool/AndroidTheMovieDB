package ordabool.themoviedb.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;

import ordabool.themoviedb.Adapters.FeaturedMediaListAdapter;
import ordabool.themoviedb.AsyncFunctions.GetMoviesGenres;
import ordabool.themoviedb.AsyncFunctions.GetTVShowsGenres;
import ordabool.themoviedb.Handlers.AppManager;
import ordabool.themoviedb.R;

public class FeaturedMediaList extends BaseActivity {
    public static ListView featuredListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_featured_media_list);
        getLayoutInflater().inflate(R.layout.activity_featured_media_list, frameLayout);
        featuredListView = findViewById(R.id.featuredMediaList);

        String mediaType;
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            mediaType = extras.getString("mediaType");
            switch (mediaType) {
                case "Movies":
                    this.setTitle("In Theaters");
                    if (AppManager.shared.getMoviesGenres() == null) {
                        GetMoviesGenres getMoviesGenres = new GetMoviesGenres(featuredListView);
                        getMoviesGenres.execute();
                    }
                    featuredListView.setAdapter(new FeaturedMediaListAdapter(this, AppManager.shared.getNowPlayingMovies()));
                    featuredListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(getApplicationContext(), MovieActivity.class);
                            MovieActivity.movie = AppManager.shared.getNowPlayingMovies()[i];
                            startActivity(intent);
                        }
                    });
                    break;
                case "TVShows":
                    this.setTitle("On TV");
                    if (AppManager.shared.getTvShowsGenres() == null) {
                        GetTVShowsGenres getTVShowsGenres = new GetTVShowsGenres(featuredListView);
                        getTVShowsGenres.execute();
                    }
                    featuredListView.setAdapter(new FeaturedMediaListAdapter(this, AppManager.shared.getOnAirTVShows()));
                    break;
            }

        }
    }
}
