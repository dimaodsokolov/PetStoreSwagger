import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;

import static utils.Const.BASE_PATH;
import static utils.Const.BASE_URL;

public class BaseApiTest {
    protected static RequestSpecification petstoreUserRequestSpecification = null;

    @BeforeClass
    public static void beforeApiTest(){
        RestAssured.baseURI = BASE_URL;
        RestAssured.basePath = BASE_PATH;

        petstoreUserRequestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
        RestAssured.requestSpecification = petstoreUserRequestSpecification;
    }
}
