package ordabool.themoviedb.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import ordabool.themoviedb.Adapters.SeasonAdapter;
import ordabool.themoviedb.AsyncFunctions.GetSeasonEpisodes;
import ordabool.themoviedb.Model.Season;
import ordabool.themoviedb.Model.TVShow;
import ordabool.themoviedb.R;

public class SeasonActivity extends BaseActivity {

    public static String tvShowName;
    public static int tvShowId;
    public static Season season;
    public static ListView seasonListView;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_season, frameLayout);
        seasonListView = findViewById(R.id.seasonListView);
        context = getApplicationContext();

        if (season.getNumber() == 0){
            setTitle("Specials");
        } else {
            setTitle("Season " + season.getNumber());
        }

        if (season.getEpisodes() == null) {
            GetSeasonEpisodes getSeasonEpisodes = new GetSeasonEpisodes(tvShowId);
            getSeasonEpisodes.execute(season);
        } else {
            seasonListView.setAdapter(new SeasonAdapter(season, getApplicationContext(), tvShowName));
        }

    }

    public static void reloadList(){
        seasonListView.setAdapter(new SeasonAdapter(season, context, tvShowName));
    }
}
