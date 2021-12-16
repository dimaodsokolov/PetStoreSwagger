import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import petstore.api.dto.User;

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


}
