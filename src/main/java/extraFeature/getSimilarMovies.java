package extraFeature;

import java.util.List;

public class getSimilarMovies {
    private String iso_3166_1;
    private String title;
    private String type;

    public getSimilarMovies(String iso_3166_1, String title, String type) {
        this.iso_3166_1 = iso_3166_1;
        this.title = title;
        this.type = type;
    }

    public String getIso_3166_1() {
        return iso_3166_1;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public void setIso_3166_1(String iso_3166_1) {
        this.iso_3166_1 = iso_3166_1;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }
}
