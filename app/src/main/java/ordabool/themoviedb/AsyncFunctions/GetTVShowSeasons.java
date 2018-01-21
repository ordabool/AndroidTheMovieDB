package ordabool.themoviedb.AsyncFunctions;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import ordabool.themoviedb.Handlers.APIHandler;
import ordabool.themoviedb.Model.Season;
import ordabool.themoviedb.Model.TVShow;
import ordabool.themoviedb.Model.Video;

/**
 * Created by Or on 21/01/2018.
 */

public class GetTVShowSeasons extends AsyncTask<TVShow, Void, Season[]> {

    String data = "";
    TVShow tvShow;
    int numOfSeasons;

    @Override
    protected Season[] doInBackground(TVShow... tvShows) {
        try {
            tvShow = tvShows[0];
            URL tvShowSeasonsUrl = new URL(APIHandler.getSeasonsUrlString(tvShow.getId()));
            HttpURLConnection httpURLConnection = (HttpURLConnection) tvShowSeasonsUrl.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null){
                line = bufferedReader.readLine();
                data += line;
            }
            JSONObject jsonObject = new JSONObject(data);
            numOfSeasons = (int) jsonObject.get("number_of_seasons");
            JSONArray jsonArray = (JSONArray) jsonObject.get("seasons");
            Season[] seasons = new Season[jsonArray.length()];

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject job = (JSONObject) jsonArray.get(i);
                Season season = new Season(
                        Integer.parseInt(job.get("id").toString()),
                        Integer.parseInt(job.get("season_number").toString()),
                        job.get("poster_path").toString(),
                        job.get("air_date").toString(), //Fix broken data
                        null
                );
                seasons[i] = season;
            }
            return seasons;

        } catch (Exception e){
            e.printStackTrace();
        }
        return new Season[0];
    }

    @Override
    protected void onPostExecute(Season[] seasons) {
        super.onPostExecute(seasons);
        if (tvShow != null){
            tvShow.setNumberOfSeasons(numOfSeasons);
            tvShow.setSeasons(seasons);
        }
    }
}
