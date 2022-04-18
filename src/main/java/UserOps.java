import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserOps {

    private User user;
    private String token;

    @Step("create valid authorization request body")
    public String createValidAuthorizationRequestBody(String userEmail, String userPassword){
        user = new User();
        user.setEmail(userEmail);
        user.setPassword(userPassword);
        return new Gson().toJson(user);
    }

    @Step("registration and authorization valid user")
    public String authorizeCreatedUser(String email, String password){
        return login(createValidAuthorizationRequestBody(email,password)).extract().path("accessToken");
    }

    @Step("request delete user")
    public void delete(String token) {
        given().spec(Base.getBaseSpec()).auth().oauth2(token.replaceAll("Bearer", "").trim()).when().delete("auth/user").then().statusCode(202);
    }

    @Step("request authorize user")
    public ValidatableResponse login(String body){
        return given().spec(Base.getBaseSpec()).body(body).when().post("auth/login").then();
    }

    @Step("delete user if one was created")
    public void cleanUp(String email, String password){
        token = authorizeCreatedUser(email, password);
        if(token != null){
            delete(token);
        }
    }
}