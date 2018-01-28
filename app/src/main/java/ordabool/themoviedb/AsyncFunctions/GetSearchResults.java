package ordabool.themoviedb.AsyncFunctions;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import ordabool.themoviedb.Activities.SearchActivity;
import ordabool.themoviedb.Adapters.FeaturedMediaListAdapter;
import ordabool.themoviedb.Adapters.SearchedMediaAdapter;
import ordabool.themoviedb.Handlers.APIHandler;
import ordabool.themoviedb.Handlers.AppManager;
import ordabool.themoviedb.Model.Media;
import ordabool.themoviedb.Model.Movie;

/**
 * Created by Or on 28/01/2018.
 */

public class GetSearchResults extends AsyncTask<String, Void, Void> {

    String data = "";
    Media[] movieResults, tvShowResults;
    Context context;

    public GetSearchResults(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(String... strings) {
        AppManager.shared.setSearchResult(null);
        String query = strings[0];
        try {
            URL searchMovieResultsUrl = new URL(APIHandler.getMovieSearchUrlString(query));
            HttpURLConnection httpURLConnection = (HttpURLConnection) searchMovieResultsUrl.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null){
                line = bufferedReader.readLine();
                data += line;
            }

            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = (JSONArray) jsonObject.get("results");
            movieResults = new Media[jsonArray.length()];

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject job = (JSONObject) jsonArray.get(i);
                JSONArray genresJSONArray = (JSONArray) job.get("genre_ids");
                int[] genres = new int[genresJSONArray.length()];
                for (int j=0; j<genresJSONArray.length(); j++){
                    genres[j] = genresJSONArray.getInt(j);
                }
                Media media = new Media(job.get("title").toString(),
                        Integer.parseInt(job.get("id").toString()),
                        job.get("release_date").toString(),
                        job.get("poster_path").toString(),
                        Float.parseFloat(job.get("vote_average").toString()),
                        job.get("overview").toString(),
                        genres);
                movieResults[i] = media;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        data = "";
        try {
            URL searchTvShowResultsUrl = new URL(APIHandler.getTvShowSearchUrlString(query));
            HttpURLConnection httpURLConnection = (HttpURLConnection) searchTvShowResultsUrl.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null){
                line = bufferedReader.readLine();
                data += line;
            }

            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = (JSONArray) jsonObject.get("results");
            tvShowResults = new Media[jsonArray.length()];

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject job = (JSONObject) jsonArray.get(i);
                JSONArray genresJSONArray = (JSONArray) job.get("genre_ids");
                int[] genres = new int[genresJSONArray.length()];
                for (int j=0; j<genresJSONArray.length(); j++){
                    genres[j] = genresJSONArray.getInt(j);
                }
                Media media = new Media(job.get("name").toString(),
                        Integer.parseInt(job.get("id").toString()),
                        job.get("first_air_date").toString(),
                        job.get("poster_path").toString(),
                        Float.parseFloat(job.get("vote_average").toString()),
                        job.get("overview").toString(),
                        genres);
                tvShowResults[i] = media;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        Media[] searchResults = new Media[movieResults.length+tvShowResults.length];
        for (int i = 0; i < movieResults.length; i++){
            searchResults[i] = movieResults[i];
        }
        for (int i = 0; i < tvShowResults.length; i++){
            searchResults[movieResults.length + i] = tvShowResults[i];
        }
        AppManager.shared.setSearchResult(searchResults);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
//        Log.i("ex", AppManager.shared.getSearchResult()[0].getTitle());
        SearchActivity.resultsListView.setAdapter(new SearchedMediaAdapter(context));
    }
}
