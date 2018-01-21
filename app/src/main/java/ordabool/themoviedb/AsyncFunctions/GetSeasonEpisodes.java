package ordabool.themoviedb.AsyncFunctions;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import ordabool.themoviedb.Activities.SeasonActivity;
import ordabool.themoviedb.Handlers.APIHandler;
import ordabool.themoviedb.Model.Episode;
import ordabool.themoviedb.Model.Season;

/**
 * Created by Or on 21/01/2018.
 */

public class GetSeasonEpisodes extends AsyncTask<Season, Void, Episode[]> {

    String data = "";
    Season season;
    int tvShowId;

    public GetSeasonEpisodes(int tvShowId) {
        this.tvShowId = tvShowId;
    }

    @Override
    protected Episode[] doInBackground(Season... seasons) {
        try {
            season = seasons[0];
            URL seasonEpisodeUrl = new URL(APIHandler.getEpisodesUrlString(tvShowId, season.getNumber()));
            HttpURLConnection httpURLConnection = (HttpURLConnection) seasonEpisodeUrl.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null){
                line = bufferedReader.readLine();
                data += line;
            }
            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = (JSONArray) jsonObject.get("episodes");
            Episode[] episodes = new Episode[jsonArray.length()];

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject job = (JSONObject) jsonArray.get(i);
                Episode episode = new Episode(
                        Integer.parseInt(job.get("id").toString()),
                        Integer.parseInt(job.get("episode_number").toString()),
                        job.get("name").toString(),
                        job.get("air_date").toString(),
                        job.get("still_path").toString(),
                        job.get("overview").toString()
                        );
                episodes[i] = episode;
            }
            return episodes;

        } catch (Exception e){
            e.printStackTrace();
        }

        return new Episode[0];
    }

    @Override
    protected void onPostExecute(Episode[] episodes) {
        super.onPostExecute(episodes);
        if (season != null){
            season.setEpisodes(episodes);
            SeasonActivity.reloadList();
        }
    }
}
