package html;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.trustStore;

public class HttpMessageSender {
    private static String url;

    public HttpMessageSender(String url){
        this.url = url;
    }

    //Authentication
    public String getToken(String endPoint) {
        Response response =
                given().contentType(ContentType.JSON).
                        when().get(url + endPoint);
        String token = response.then().extract().path("request_token");
        return token;
    }

    public Response postMessageTo(String endPoint, String bodyAuthentication){
        Response response =
                given().contentType(ContentType.JSON).body(bodyAuthentication).
                        when().post(url + endPoint).
                        andReturn();
        response.then().log().all();
        return response;
    }

    public Response deleteRequest(String endPoint, String bodyAuthentication){
        Response response =
                given().
                        contentType(ContentType.JSON).body(bodyAuthentication).log().all().
                        when().delete(url + endPoint).andReturn();
        return response;
    }
//
    public Response getList(String endPoint){
        Response response =
                given().
                        contentType(ContentType.JSON).
                        when().log().uri().
                        get(url + endPoint);
        response.then().log().all();
        return response;
    }


}
