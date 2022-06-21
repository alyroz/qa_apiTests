package pages;

import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ValidatableResponse;
import utils.PropertiesUtils;

public class CreatePage extends MainPage{

    protected final String CREATE_PATH = PropertiesUtils.getProperty("pathCreate");

    public ValidatableResponse assertion(Response response) {
        return response.then().assertThat();
    }

}
