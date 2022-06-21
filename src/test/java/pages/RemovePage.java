package pages;

import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ValidatableResponse;
import utils.PropertiesUtils;

import static com.jayway.restassured.RestAssured.given;

public class RemovePage {
    protected final String REMOVE_PATH = PropertiesUtils.getProperty("pathRemove");

    public ValidatableResponse assertion(Response response) {
        return response.then().assertThat();
    }
}
