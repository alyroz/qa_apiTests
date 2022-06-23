package testsApiSuperHeroes;

import com.jayway.restassured.response.Response;
import dto.SuperHeroErrorDto;
import dto.SuperHeroResponseDto;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateTest extends BaseTest{
    String birthDate = "2001-03-25";
    String city = "Abc";
    String fullName = "Diana";
    String gender = "F";
    String mainSkill = "Magic";
    String phone = "+81234567809";
    Response responseAllData = apiSuperHeroes.createHero(birthDate, city, fullName, gender, mainSkill, phone);
    SuperHeroResponseDto heroResponseAllDataDto = apiSuperHeroes.getSuperHeroDto(responseAllData);
    Response responseRandom = apiSuperHeroes.createHero();

    Response negativeResponse = apiSuperHeroes.createHero("", city, fullName, gender, mainSkill, phone);
    SuperHeroErrorDto heroErrorDto = negativeResponse.then().extract().response().as(SuperHeroErrorDto.class);

    @Test
    public void positiveStatusCodeTest(){
        responseRandom.then().assertThat().statusCode(201);
        // NB!!! Error in API -> 200 OK, not 201 Created => will fail. Bug in API
    }

    @Test
    public void positiveStatusLineTest(){
        responseRandom.then().assertThat().statusLine("Created");
        // NB!!! Error in API -> 200 OK, not 201 Created => will fail. Bug in API
    }

    @Test
    public void verifyCityFromResponseTest() {

        Assert.assertEquals(heroResponseAllDataDto.getCity(), city);
    }

    @Test
    public void verifyFullNameFromResponseTest() {

        Assert.assertEquals(heroResponseAllDataDto.getFullName(), fullName);
    }

    @Test
    public void verifyErrorCodeTest() {

        Assert.assertEquals(heroErrorDto.getCode(), "BAD_REQUEST",
                "Actual code in the response body is not the \"BAD_REQUEST\"");
    }

    @Test
    public void verifyErrorMessageTest() {

        Assert.assertEquals(heroErrorDto.getMessage(), "Incorrect request data",
                "Actual message in the response body is not the \"Incorrect request data\"");
        }
}
