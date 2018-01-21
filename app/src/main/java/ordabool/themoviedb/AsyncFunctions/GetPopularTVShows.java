package ordabool.themoviedb.AsyncFunctions;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import ordabool.themoviedb.Activities.PopularTVShows;
import ordabool.themoviedb.Handlers.APIHandler;
import ordabool.themoviedb.Handlers.AppManager;
import ordabool.themoviedb.Model.Movie;
import ordabool.themoviedb.Model.TVShow;

/**
 * Created by Or on 21/01/2018.
 */

public class GetPopularTVShows extends AsyncTask<Void, Void, Void> {

    String data = "";

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL popularTVShowsUrl = new URL(APIHandler.getOnAirTVShowsUrlString());
            HttpURLConnection httpURLConnection = (HttpURLConnection) popularTVShowsUrl.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null){
                line = bufferedReader.readLine();
                data += line;
            }

            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = (JSONArray) jsonObject.get("results");
            TVShow[] popularTVShows = new TVShow[jsonArray.length()];

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject job = (JSONObject) jsonArray.get(i);
                JSONArray genresJSONArray = (JSONArray) job.get("genre_ids");
                int[] genres = new int[genresJSONArray.length()];
                for (int j=0; j<genresJSONArray.length(); j++){
                    genres[j] = genresJSONArray.getInt(j);
                }
                TVShow tvShow = new TVShow(job.get("name").toString(),
                        Integer.parseInt(job.get("id").toString()),
                        job.get("first_air_date").toString(),
                        job.get("poster_path").toString(),
                        Float.parseFloat(job.get("vote_average").toString()),
                        job.get("overview").toString(),
                        genres,
                        null,
                        0);
                popularTVShows[i] = tvShow;
            }

            AppManager.shared.setPopularTVShow(popularTVShows);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.i("Array length", AppManager.shared.getPopularTVShow().length +"");
        PopularTVShows.setGridAdapter();
    }
}
