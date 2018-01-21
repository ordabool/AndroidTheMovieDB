package ordabool.themoviedb.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import ordabool.themoviedb.Adapters.MovieAdapter;
import ordabool.themoviedb.AsyncFunctions.GetMovieVideos;
import ordabool.themoviedb.AsyncFunctions.GetMoviesGenres;
import ordabool.themoviedb.Handlers.AppManager;
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

        if (movie.getVideos() == null) {
            GetMovieVideos getMovieVideos = new GetMovieVideos();
            getMovieVideos.execute(movie);
        }

        movieListView = findViewById(R.id.movieListView);

        if(AppManager.shared.getMoviesGenres() == null) {
            GetMoviesGenres getMoviesGenres = new GetMoviesGenres(movieListView, new MovieAdapter(movie, getApplicationContext()));
            getMoviesGenres.execute();
        } else {
            movieListView.setAdapter(new MovieAdapter(movie, getApplicationContext()));
        }

        movieListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0){
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(movie.getVideos()[i-1].getUrl().toString()));
                        startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
