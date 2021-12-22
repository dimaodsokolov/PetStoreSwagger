import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import petstore.api.dto.User;
import petstore.api.dto.UserResponse;

import static io.restassured.RestAssured.given;

import static petstore.api.utils.Const.USER;

public class PetStoreApiTests extends BaseApiTest{
    private static User user = null;

    @BeforeClass
    public static void beforeClass()  {
        user = new User.UserBuilder()
                .withId(1L)
                .withUsername("name1")
                .withFirstName("Petr")
                .withLastName("Petrov")
                .withEmail("pertov@gmail.com")
                .withPassword("pass")
                .withPhone("+380111111111")
                .withUserStatus(true).build();
    }
    @Test
    public void getUserByUserName() {



        UserResponse expectedResult = new UserResponse(200L, "unknown", "logged in user session:1639585258727");
        UserResponse actualResult = given().body(user).when().get(USER).getBody().as(UserResponse.class);


        Assertions.assertThat((actualResult))
                .isInstanceOf(UserResponse.class)
                //.hasNoNullFieldsOrProperties()
                .hasFieldOrProperty("code")
                .hasFieldOrProperty("type")
                .hasFieldOrProperty("message")
                .isEqualToComparingFieldByField(expectedResult)
                .hasToString(expectedResult.toString());
    }
}
