package ordabool.themoviedb.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import ordabool.themoviedb.Adapters.FeaturedMediaAdapter;
import ordabool.themoviedb.AsyncFunctions.GetMoviesGenres;
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

        this.setTitle("Featured Media");

        GetNowPlayingMovies getNowPlayingMovies = new GetNowPlayingMovies();
        getNowPlayingMovies.execute();

        GetOnAirTVShows getOnAirTVShows = new GetOnAirTVShows();
        getOnAirTVShows.execute();

        featuredListView = findViewById(R.id.featuredMediaListView);
        featuredListView.setAdapter(new FeaturedMediaAdapter(this, AppManager.shared.featuredMediaOrder));
        featuredListView.setOnItemClickListener(new FeaturedListOnClickListener());

    }


    class FeaturedListOnClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String mediaType = AppManager.shared.featuredMediaOrder[i];
            Intent intent = new Intent(MainActivity.this, FeaturedMediaList.class);
            intent.putExtra("mediaType", mediaType);
            startActivity(intent);
        }
    }
}
