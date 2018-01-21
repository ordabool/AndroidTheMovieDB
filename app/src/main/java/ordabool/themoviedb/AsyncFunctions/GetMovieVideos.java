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

import ordabool.themoviedb.Handlers.APIHandler;
import ordabool.themoviedb.Handlers.AppManager;
import ordabool.themoviedb.Model.Movie;
import ordabool.themoviedb.Model.Video;

/**
 * Created by Or on 21/01/2018.
 */

public class GetMovieVideos extends AsyncTask<Movie, Void, Video[]>{

    String data = "";
    Movie movie;

    @Override
    protected Video[] doInBackground(Movie... movies) {
        try {
            movie = movies[0];
            URL movieVideosUrl = new URL(APIHandler.getMovieVideosUrlString(movie.getId()));
            HttpURLConnection httpURLConnection = (HttpURLConnection) movieVideosUrl.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null){
                line = bufferedReader.readLine();
                data += line;
            }
            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = (JSONArray) jsonObject.get("results");
            Video[] videos = new Video[jsonArray.length()];

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject job = (JSONObject) jsonArray.get(i);
                Video video = new Video(
                        job.get("id").toString(),
                        job.get("key").toString(),
                        job.get("name").toString());
                videos[i] = video;
            }
            return videos;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Video[] videos) {
        super.onPostExecute(videos);
        if (videos != null){
            movie.setVideos(videos);
        }
    }
}
