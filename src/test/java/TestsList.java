import API.API;
import com.google.gson.Gson;
import extraFeature.AlternativeMovies;
import html.UserEnvironment;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.print.DocFlavor;

public class TestsList {

    private Gson gson = new Gson();
    private static API api;

    @BeforeClass
    public static void beforeApiKey (){
        api = new API(UserEnvironment.api_key);
    }

    @Test
    public void testGetDetailsOfLists(){
        String list_id  = "70";
        Response response = api.getListById(list_id);
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void testCheckItemStatus(){
        String list_id = "7057004";
        int movie_id = 18;
        Response response = api.getListByIdStatusItem(list_id, movie_id);
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void testCreateList(){
        String name = "This is my awesome test list.";
        String description = "Just an awesome list hhhhhh.";
        String language = "en";
        Response response = api.createList(name, description, language);
        response.then().assertThat().statusCode(201);
    }

    @Test
    public void testAddMovie(){
        String list_id = "7057004";
        Response response = api.addMovie(list_id);
        response.then().assertThat().statusCode(201);
    }

    @Test
    public void testRemoveMovie(){
        String list_id = "7057004";
        Response response = api.removeMovie(list_id);
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void testClearList(){
        String name = "Good Lddaaaaist";
        String description = "Just an awesome listsddddfsdf...";
        String language = "en";
        int list_id = api.createList(name, description, language).then().extract().path("list_id");
        Response response = api.clearList(list_id + "");
        response.then().assertThat().statusCode(201);
    }

    @Test
    public void testDeleteList(){
        String list_id = "7057004";
        Response response = api.deleteList(list_id);
        response.then().assertThat().statusCode(500);

    }

    @Test
    public void rateMovie(){
        String movie_id = "63481";
        String rate = "5.0";
        Response response = api.rateMovies(movie_id,rate);
        response.then().assertThat().statusCode(201);
    }

    @Test
    public void rateTvShow(){
        String movie_id = "1021";
        String rate = "5.0";
        Response response = api.rateTvShow(movie_id, rate);
        response.then().assertThat().statusCode(201);
    }

    @Test
    public void rateTvEpisode(){
        String tv_id = "1021";
        String season_number = "1";
        String episode_number = "1";
        String rate = "5.0";
        Response response = api.rateTvEpisode(tv_id, season_number, episode_number, rate);
        response.then().assertThat().statusCode(201);
    }

    @Test
    public void getSimilarMovies(){
        Response response = api.getAlternativeTitles("70");
        String json = response.asString();
        AlternativeMovies titleObject = gson.fromJson(json, AlternativeMovies.class);
        Assert.assertEquals("Golpes del destino",titleObject.titles.get(3).getTitle());
    }

}
