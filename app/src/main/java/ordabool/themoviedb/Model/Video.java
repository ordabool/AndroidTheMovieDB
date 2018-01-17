package ordabool.themoviedb.Model;

import java.net.URL;

/**
 * Created by Or on 16/01/2018.
 */

public class Video {
    String id;
    URL url;
    String name;

    public Video(String id, URL url, String name) {
        this.id = id;
        this.url = url; // Create a built-in function to convert String to URL
        this.name = name;
    }
}
