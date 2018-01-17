package ordabool.themoviedb.AsyncFunctions;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import ordabool.themoviedb.Activities.FeaturedMediaList;
import ordabool.themoviedb.Handlers.APIHandler;
import ordabool.themoviedb.Handlers.AppManager;

/**
 * Created by Or on 17/01/2018.
 */

public class GetTVShowsGenres extends AsyncTask<Void, Void, Void> {

    String data = "";

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL tvShowsGenresUrl = new URL(APIHandler.getTVShowsGenresUrlString());
            HttpURLConnection httpURLConnection = (HttpURLConnection) tvShowsGenresUrl.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null){
                line = bufferedReader.readLine();
                data += line;
            }
            JSONObject job = new JSONObject(data);
            JSONArray jsonArray = (JSONArray) job.get("genres");
            AppManager.shared.setTvShowsGenres(jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        FeaturedMediaList.featuredListView.invalidateViews();
    }
}
