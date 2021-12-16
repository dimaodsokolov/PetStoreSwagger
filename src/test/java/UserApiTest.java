import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import petstore.api.dto.User;
import petstore.api.dto.UserResponse;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static utils.Const.*;

public class UserApiTest {


    @Test
    public void canAddUser() {
        User testUser1 = new User.UserBuilder()
                .withId(1l)
                .withUsername("name1")
                .withFirstName("Petr")
                .withLastName("Petrov")
                .withEmail("pertov@gmail.com")
                .withPassword("pass")
                .withPhone("+380111111111")
                .withUserStatus(true).build();

        RequestSpecification request = given();
        Response response = request.baseUri(BASE_URL + BASE_PATH + USER)
                .contentType(ContentType.JSON)
                .body(testUser1)
                .when()
                .post();

        Assertions.assertThat(response.statusCode()).isEqualTo(200);
        Assertions.assertThat(response.getContentType().contentEquals(ContentType.JSON.toString()));
    }

    @Test
    public  void canDeleteUser(){
        User testUser1 = new User.UserBuilder()

                .withUsername("name1")
                .withUserStatus(true).build();

        RequestSpecification request = given();

        Response response = request.baseUri(BASE_URL + BASE_PATH + USER)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .basePath(testUser1.getUsername())
                .when()
                .delete();

        response.then()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON);
    }

    @Test
    public void canGetUserByUsername() {
        User testUser1 = new User.UserBuilder()
                .withId(1l)
                .withUsername("name1")
                .withFirstName("Petr")
                .withLastName("Petrov")
                .withEmail("pertov@gmail.com")
                .withPassword("pass")
                .withPhone("+380111111111")
                .withUserStatus(true).build();

        RequestSpecification request = given();

        Response response = request.baseUri(BASE_URL + BASE_PATH + USER)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .basePath(testUser1.getUsername())
                .when()
                .get();


        response.then()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON);

        Assertions.assertThat(response.getBody().as(User.class)).isEqualToComparingFieldByField(testUser1);
    }

    @Test
    public void canLoginUser() {

        Map<String, String> params = new HashMap<>();
        params.put("username", "name1");
        params.put("password", "pass");
        RequestSpecification request = given();

        Response response = request.baseUri(BASE_URL + BASE_PATH + USER)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .basePath("/login").params(params)
                .when()
                .get();

        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON);

    }

    @Test
    public void canLogoutUser() {

        RequestSpecification request = given();

        Response response = request.baseUri(BASE_URL + BASE_PATH + USER)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .basePath("/logout")
                .when()
                .get();

        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON);



    }
    @Test
    public void canUpdateUser(){
        Map<String, String> params = new HashMap<>();
        params.put("username", "name1");
        params.put("phone", "+3801234567");
        RequestSpecification request = given();

        Response response = request.baseUri(BASE_URL + BASE_PATH+USER)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(params)
                .basePath(params.get("username"))   // не уверен что работает правильно
                .when()
                .put();

        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }


}
