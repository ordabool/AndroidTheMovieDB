package ordabool.themoviedb.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import ordabool.themoviedb.Adapters.MediaGridAdapter;
import ordabool.themoviedb.Handlers.AppManager;
import ordabool.themoviedb.R;

public class PopularMovies extends BaseActivity {

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_popular_movies);
        getLayoutInflater().inflate(R.layout.activity_popular_movies, frameLayout);

        setTitle("Popular Movies");

        gridView = findViewById(R.id.popularMoviesGridView);
        gridView.setAdapter(new MediaGridAdapter(AppManager.shared.getNowPlayingMovies(), getApplicationContext()));
    }
}
