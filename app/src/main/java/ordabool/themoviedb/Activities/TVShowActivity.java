package ordabool.themoviedb.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import ordabool.themoviedb.Adapters.TVShowAdapter;
import ordabool.themoviedb.AsyncFunctions.GetTVShowSeasons;
import ordabool.themoviedb.AsyncFunctions.GetTVShowsGenres;
import ordabool.themoviedb.Handlers.AppManager;
import ordabool.themoviedb.Model.Season;
import ordabool.themoviedb.Model.TVShow;
import ordabool.themoviedb.R;

public class TVShowActivity extends BaseActivity {

    public static TVShow tvShow;
    ListView tvShowListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_tvshow, frameLayout);

        setTitle(tvShow.getTitle());

        if (tvShow.getSeasons() == null) {
            GetTVShowSeasons getTVShowSeasons = new GetTVShowSeasons();
            getTVShowSeasons.execute(tvShow);
        }

        tvShowListView = findViewById(R.id.tvShowListView);
        tvShowListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0){
                    try {
                        Intent intent = new Intent(getApplicationContext(), SeasonActivity.class);
                        SeasonActivity.season = tvShow.getSeasons()[i-1];
                        SeasonActivity.tvShowName = tvShow.getTitle();
                        SeasonActivity.tvShowId = tvShow.getId();
                        startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        if((AppManager.shared.getTvShowsGenres() == null)||(tvShow.getSeasons() == null)) {
            GetTVShowsGenres getTVShowsGenres = new GetTVShowsGenres(tvShowListView, new TVShowAdapter(tvShow, getApplicationContext()));
            getTVShowsGenres.execute();
        } else {
            tvShowListView.setAdapter(new TVShowAdapter(tvShow, getApplicationContext()));
        }
    }
}
