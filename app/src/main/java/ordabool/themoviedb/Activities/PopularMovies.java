package ordabool.themoviedb.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import ordabool.themoviedb.Adapters.MediaGridAdapter;
import ordabool.themoviedb.AsyncFunctions.GetPopularMovies;
import ordabool.themoviedb.Handlers.AppManager;
import ordabool.themoviedb.R;

public class PopularMovies extends BaseActivity {

    static GridView gridView;
    static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_popular_movies, frameLayout);
        context = getApplicationContext();

        GetPopularMovies getPopularMovies = new GetPopularMovies();
        getPopularMovies.execute();

        setTitle("Popular Movies");

        gridView = findViewById(R.id.popularMoviesGridView);
        setGridAdapter();
    }

    public static void setGridAdapter() {
        gridView.setAdapter(new MediaGridAdapter(AppManager.shared.getPopularMovies(), context));
        gridView.invalidateViews();
    }
}
