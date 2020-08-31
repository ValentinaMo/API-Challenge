package API;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import extraFeature.AlternativeMovies;
import html.HttpMessageSender;
import html.UserEnvironment;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class API {
    private Gson gson = new Gson();
    private String endPoint;
    private String token;
    private final String apiKey;
    private final String username;
    private final String password;


    public API(String api_key){
        HttpMessageSender message = new HttpMessageSender(UserEnvironment.url);
        this.apiKey = api_key;
        this.username = UserEnvironment.username;
        this.password = UserEnvironment.password;
    }

    public String getToken(){
        HttpMessageSender httpMessageSender = new HttpMessageSender(UserEnvironment.url);
        token = httpMessageSender.getToken("/authentication/token/new?api_key=" + apiKey);
        return token;
    }

    public Response createSessionIdLogIn(){
        HttpMessageSender httpMessageSender = new HttpMessageSender(UserEnvironment.url);
        token = getToken();
        String bodyAuthentication = "{\"username\" : \"ValentinaMorenoPuentes\", \"password\" : \"chorro123\", \"request_token\" : \"" + token + "\"}";
        Response response = httpMessageSender.postMessageTo("/authentication/token/validate_with_login?api_key=" + apiKey, bodyAuthentication);
        return response;
    }

    public Response createSessionId(){
        HttpMessageSender httpMessageSender = new HttpMessageSender(UserEnvironment.url);
        createSessionIdLogIn();
        String bodyAuthentication = "{\"request_token\" : \"" + token + "\"}";
        Response response = httpMessageSender.postMessageTo("/authentication/session/new?api_key=" + apiKey, bodyAuthentication);
        return response;
    }

    public Response deleteSession(){
        HttpMessageSender httpMessageSender = new HttpMessageSender(UserEnvironment.url);
        String sessionId = createSessionId().then().extract().path("session_id");
        String bodyAuthentication = "{\"session_id\" : \"" + sessionId + "\"}";
            Response response = httpMessageSender.deleteRequest("/authentication/session?api_key=" + apiKey, bodyAuthentication);
        return response;
    }
//
    public Response getListById(String list_id){
        HttpMessageSender httpMessageSender = new HttpMessageSender(UserEnvironment.url);
        Response response = httpMessageSender.getList("/list/" + list_id + "?api_key=" + apiKey);
        return response;
    }
//
    public Response getListByIdStatusItem(String list_id, int movie_id){
        HttpMessageSender httpMessageSender = new HttpMessageSender(UserEnvironment.url);
        Response response = httpMessageSender.getList("/list/" + list_id + "/item_status?api_key=" + apiKey + "&movie_id=" + movie_id);
        return  response;
    }

    public Response createList(String name, String description, String language){
        HttpMessageSender httpMessageSender = new HttpMessageSender(UserEnvironment.url);
        String sessionId = createSessionId().then().extract().path("session_id");
        String bodyList = "{\"name\" : \"" + name + "\",  \"description\" : \"" + description + "\", \"language\" : \"" + language + "\"}";
        Response response = httpMessageSender.postMessageTo("/list?api_key=" + apiKey + "&session_id=" + sessionId, bodyList);
        return response;
    }

    public Response addMovie(String list_id){
        HttpMessageSender httpMessageSender = new HttpMessageSender(UserEnvironment.url);
        String bodyAuthentication = "{\"media_id\" : 18}";
        String sessionId = createSessionId().then().extract().path("session_id");
        Response response = httpMessageSender.postMessageTo("/list/" + list_id + "/add_item?api_key=" + apiKey + "&session_id=" + sessionId, bodyAuthentication);
        return response;
    }

    public Response removeMovie(String list_id){
        HttpMessageSender httpMessageSender = new HttpMessageSender(UserEnvironment.url);
        String bodyAuthentication = "{\"media_id\" : 18}";
        String sessionId = createSessionId().then().extract().path("session_id");
        Response response = httpMessageSender.postMessageTo("/list/" + list_id + "remove_item?api_key=" + apiKey + "&session_id=" + sessionId, bodyAuthentication);
        return response;
    }

    public Response clearList(String list_id){
        HttpMessageSender httpMessageSender = new HttpMessageSender(UserEnvironment.url);
        String sessionId = createSessionId().then().extract().path("session_id");
        String booleanConfirm = "true";
        Response response = httpMessageSender.postMessageTo("/list/" + list_id + "/clear?api_key=" + apiKey + "&session_id=" + sessionId + "&confirm=" + booleanConfirm, "");
        return response;
    }

    public Response deleteList(String list_id){
        HttpMessageSender httpMessageSender = new HttpMessageSender(UserEnvironment.url);
        String sessionId = createSessionId().then().extract().path("session_id");
        System.out.println(sessionId);
        Response response = httpMessageSender.deleteRequest("/list/" + list_id + "?session_id=" + sessionId + "&api_key=" + apiKey, "");
        return response;
    }

    public Response rateMovies(String movie_id, String rate){
        HttpMessageSender httpMessageSender = new HttpMessageSender(UserEnvironment.url);
        String sessionId = createSessionId().then().extract().path("session_id");
        String bodyAuthentication = "{\"value\": " + rate + "}";
        Response response = httpMessageSender.postMessageTo("/movie/" + movie_id + "/rating?session_id=" + sessionId + "&api_key=" + apiKey, bodyAuthentication);
        return response;
    }

    public Response rateTvShow(String tv_id, String rate){
        HttpMessageSender httpMessageSender = new HttpMessageSender(UserEnvironment.url);
        String sessionId = createSessionId().then().extract().path("session_id");
        String bodyAuthentication = "{\"value\": " + rate + "}";
        Response response = httpMessageSender.postMessageTo("/tv/" + tv_id + "/rating?session_id=" + sessionId + "&api_key=" + apiKey, bodyAuthentication);
        return response;
    }

    public Response rateTvEpisode(String tv_id, String season_number, String episode_number, String rate){
        HttpMessageSender httpMessageSender = new HttpMessageSender(UserEnvironment.url);
        String sessionId = createSessionId().then().extract().path("session_id");
        String bodyAuthentication = "{\"value\": " + rate + "}";
        Response response = httpMessageSender.postMessageTo("/tv/" + tv_id + "/season/" + season_number+ "/episode/" + episode_number + "/rating?session_id=" + sessionId + "&api_key=" + apiKey, bodyAuthentication);
        return response;
    }

    public Response getAlternativeTitles(String movie_id){

        HttpMessageSender httpMessageSender = new HttpMessageSender(UserEnvironment.url);
        Response response = httpMessageSender.getList("/movie/" + movie_id + "/alternative_titles?api_key=" + apiKey);
        return response;
    }

}
