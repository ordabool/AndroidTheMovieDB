package ordabool.themoviedb.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import ordabool.themoviedb.Adapters.MovieAdapter;
import ordabool.themoviedb.Model.Movie;
import ordabool.themoviedb.R;

public class MovieActivity extends BaseActivity {

    public static Movie movie;
    ListView movieListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_movie, frameLayout);

        setTitle(movie.getTitle());

        movieListView = findViewById(R.id.movieListView);
        movieListView.setAdapter(new MovieAdapter(movie, getApplicationContext()));
    }
}
