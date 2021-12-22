package petstore.api.requests;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import petstore.api.dto.User;
import petstore.api.dto.UserResponse;
import petstore.api.utils.Log;

import static java.lang.String.format;
import static petstore.api.utils.Const.LOGOUT;
import static petstore.api.utils.Const.USER;

public class UserRequests extends HttpRequests {

    public UserResponse createdUser(User user) {
        Log.getInstance().debug(format("Send POST request Create user : \n %s", user.toString()));
        Response response = doPost(USER, user);
        response.then().statusCode(200);
        response.then().contentType(ContentType.JSON);

        UserResponse result = response
                .getBody().as(UserResponse.class);
        return result;
    }
     public UserResponse updateUser(User user, String userName) {
        Log.getInstance().debug(format("Send PUT request Update user : \n %s", user.toString()));
        Response response = doPut(USER + "/" + userName, user);
        response.then().statusCode(200);
        response.then().contentType(ContentType.JSON);

        UserResponse result = response
                .getBody().as(UserResponse.class);
        return result;
    }

    public User getUserByUserName(String userName) {
        return doGet(USER + "/" + userName).as(User.class);
    }

    public User loginUserByUserNameAndPass(String userName, String userPassword) {
        return doGet(USER + "/login" + userName + userPassword).as(User.class);
    }

    public UserResponse logoutUser(String userName) {
        Response response = doGet(USER + LOGOUT);
        response.then().statusCode(200);
        response.then().contentType(ContentType.JSON);

        UserResponse result = response
                .getBody().as(UserResponse.class);
        return result;
    }

    public UserResponse deleteUser(String userName) {

        Response response = doDelete(USER + "/" + userName);
        response.then().statusCode(200);
        response.then().contentType(ContentType.JSON);

        UserResponse result = response
                .getBody().as(UserResponse.class);
        return result;
    }
}
