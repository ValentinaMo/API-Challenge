import API.API;
import html.UserEnvironment;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestsAuthentication {

    private static API api;

    @BeforeClass
    public static void beforeApiKey (){
        api = new API(UserEnvironment.api_key);
    }

    @Test
    public void testRequestTokenSuccess(){
        String token = api.getToken();

    }

    @Test
    public void testCreateSessionIdWithLogIn(){
        Response response = api.createSessionIdLogIn();
        response.then().assertThat().statusCode(200);
    }
//T
    @Test
    public void testGetSessionId(){
        Response response = api.createSessionId();
        response.then().assertThat().statusCode(200);
    }
//
    @Test
    public void TestDeleteSessionId(){
        Response response = api.deleteSession();
        response.then().assertThat().statusCode(200);
    }
}
