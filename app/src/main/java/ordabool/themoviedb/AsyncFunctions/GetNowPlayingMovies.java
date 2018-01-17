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
import ordabool.themoviedb.Handlers.AppManager;
import ordabool.themoviedb.Activities.MainActivity;
import ordabool.themoviedb.Model.Movie;

/**
 * Created by Or on 16/01/2018.
 */

public class GetNowPlayingMovies extends AsyncTask<Void, Void, Void> {

    String data = "";

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL nowPlayingMoviesUrl = new URL(APIHandler.getNowPlayingMoviesUrlString());
            HttpURLConnection httpURLConnection = (HttpURLConnection) nowPlayingMoviesUrl.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null){
                line = bufferedReader.readLine();
                data += line;
            }

            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = (JSONArray) jsonObject.get("results");
            Movie[] nowPlayingMovies = new Movie[jsonArray.length()];

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject job = (JSONObject) jsonArray.get(i);
                Movie movie = new Movie(job.get("title").toString(), Integer.parseInt(job.get("id").toString()), job.get("release_date").toString(), job.get("poster_path").toString(), Float.parseFloat(job.get("vote_average").toString()), job.get("overview").toString(), null, null);
                nowPlayingMovies[i] = movie;
            }

            AppManager.shared.setNowPlayingMovies(nowPlayingMovies);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MainActivity.featuredListView.invalidateViews();
    }
}
