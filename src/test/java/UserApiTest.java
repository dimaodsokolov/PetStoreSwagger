import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import petstore.api.dto.User;
import petstore.api.dto.UserResponse;
import petstore.api.requests.UserRequests;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static petstore.api.utils.Const.*;

public class UserApiTest extends BaseApiTest {
    private static UserRequests requests = null;
    private static User userObject = null;

    @BeforeClass
    public static void beforeUserApiTest() {
        requests = new UserRequests();
        userObject = new User.UserBuilder()
                .withId(1L)
                .withUsername("name1")
                .withFirstName("Petr")
                .withLastName("Petrov")
                .withEmail("pertov@gmail.com")
                .withPassword("pass")
                .withPhone("+380111111111")
                .withUserStatus(true)
                .build();

    }

    @Test
    @Description("Add new User")
    public void canAddUser() {
        UserResponse expectedResult = new UserResponse(200L, "unknown", "1");
        UserResponse actualResult = requests.createdUser(userObject);
        User createdUser = requests.getUserByUserName(userObject.getUsername());
        Assertions.assertThat(actualResult).isEqualToComparingFieldByField(expectedResult);
        Assertions.assertThat(createdUser).isEqualToComparingFieldByField(userObject);


    }

    @Test
    @Description("Delete user")
    public void canDeleteUser() {
        UserResponse expectedResult = new UserResponse(200L, "unknown", userObject.getUsername());
        UserResponse actualResult = requests.deleteUser(userObject.getUsername());
        Assertions.assertThat(actualResult).isEqualToComparingFieldByField(expectedResult);
    }

    @Test
    public void canGetUserByUsername() {
       /* Specifications.installSpecification(
                Specifications.requestSpecification(BASE_URL + BASE_PATH + USER),
                Specifications.responseSpecOK200());
*/
        RequestSpecification request = given();

        given()
                .when()
                .get(userObject.getUsername());


        // Assertions.assertThat(response.getBody().as(User.class)).isEqualToComparingFieldByField(testUser1);
    }

    @Test
    @Description("need to be updated")
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

        UserResponse expectedResult = new UserResponse(200L, "unknown", "ok");
        UserResponse actualResult = requests.logoutUser(userObject.getUsername());
        Assertions.assertThat(actualResult).isEqualToComparingFieldByField(expectedResult);



    }

    @Test
    public void canUpdateUserV2() {
        UserResponse expectedResult = new UserResponse(200L, "unknown", "1");
        User tmpUser = requests.getUserByUserName(userObject.getUsername());
        tmpUser.setPhone("3801234567");
        UserResponse actualResult = requests.updateUser(tmpUser, tmpUser.getUsername());
        User updatedUser = requests.getUserByUserName(tmpUser.getUsername());
        Assertions.assertThat(actualResult).isEqualToComparingFieldByField(expectedResult);
        Assertions.assertThat(updatedUser).isEqualToComparingFieldByField(tmpUser);
    }

    @Test
    public void canUpdateUser() {
        Map<String, String> params = new HashMap<>();
        params.put("username", "name1"); // key 1 = getusername
        params.put("phone", "+3801234567");// key 2 =
        RequestSpecification request = given();

        Response response = request.baseUri(BASE_URL + BASE_PATH + USER)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(params)
                .basePath(params.get("username"))
                .when()
                .put();

        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

}
