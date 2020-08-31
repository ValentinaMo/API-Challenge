package extraFeature;

import io.restassured.response.Response;

import java.util.List;

public class AlternativeMovies {
    public int id;
    public List<getSimilarMovies> titles;

    public AlternativeMovies(){

    }

    public int getId() {
        return id;
    }

    public List<getSimilarMovies> getResponse() {
        return titles;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setResponse(List<getSimilarMovies> response) {
        this.titles = response;
    }

}
