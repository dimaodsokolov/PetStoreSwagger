package petstore.api.requests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import petstore.api.utils.Log;
import org.awaitility.reflect.exception.FieldNotFoundException;

public class HttpRequests {
    protected Response doGet(String url) { return RestAssured.given().get(url);
    }
    protected Response doPost(String url, Object body){
        Response post = RestAssured.given().body(body).when().post(url);
        Log.getInstance().info("POST RESPONSE: \n" + post.prettyPrint());
        try {
            throw new FieldNotFoundException("C:\\ not found!!");
        }catch (FieldNotFoundException e){
            Log.getInstance().error(e.getMessage());
        }
        return post;
    }

    protected Response doPut(String url, Object body){
        Response put = RestAssured.given().body(body).when().put(url);
        Log.getInstance().info("POST RESPONSE: \n" + put.prettyPrint());
        return put;


    }

    protected Response doDelete(String url){
        return RestAssured.given().when().delete(url);
    }
}
