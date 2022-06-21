package pages;

import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ValidatableResponse;
import utils.PropertiesUtils;

import static com.jayway.restassured.RestAssured.given;

public class GetPage {
    protected final String GET_PATH = PropertiesUtils.getProperty("pathGet");

    public ValidatableResponse assertion(Response response) {
        return response.then().assertThat();
    }

}

