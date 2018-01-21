package ordabool.themoviedb.Model;

import java.net.URL;

import ordabool.themoviedb.Handlers.APIHandler;

/**
 * Created by Or on 16/01/2018.
 */

public class Video {
    String id;
    URL url;
    String name;

    public Video(String id, String urlKey, String name) {
        this.id = id;
        try {
            url = new URL(APIHandler.getVideoUrlString(urlKey));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
