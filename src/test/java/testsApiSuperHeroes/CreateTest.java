package testsApiSuperHeroes;

import com.jayway.restassured.response.Response;
import dto.SuperHeroErrorDto;
import dto.SuperHeroResponseDto;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateTest extends BaseTest{

    String city = "Abc";
    String phone = "+81234567809";
    Response response = apiSuperHeroes.createHero(city, phone);
    SuperHeroResponseDto heroResponseDto = response.then().extract().response().as(SuperHeroResponseDto.class);
    //Response resp = apiSuperHeroes.createHero(city, phone);
   // SuperHeroResponseDto heroRespDto = resp.then().extract().response().as(SuperHeroResponseDto.class);

    Response negativeResponse = apiSuperHeroes.createNegativeHero(city, phone);
    SuperHeroErrorDto heroErrorDto = negativeResponse.then().extract().response().as(SuperHeroErrorDto.class);

    @Test
    public void positiveStatusCodeTest(){
        response.then().assertThat().statusCode(201);
        // NB!!! Error in API -> 200 OK, not 201 Created => will fail. Bug in API
    }

    @Test
    public void positiveStatusLineTest(){
        response.then().assertThat().statusLine("Created");
        // NB!!! Error in API -> 200 OK, not 201 Created => will fail. Bug in API
    }

    @Test
    public void verifyCityFromResponseTest() {
        Assert.assertEquals(heroResponseDto.getCity(), city);
    }

    @Test
    public void verifyPhoneFromResponseTest() {
        Assert.assertEquals(heroResponseDto.getPhone(), phone);
        // Failed: phone is empty - ?
    }

    @Test
    public void verifyErrorCodeTest() {

        Assert.assertTrue(heroErrorDto.getCode().equals("BAD_REQUEST"));
    }

        @Test
        public void verifyErrorMessageTest() {

            Assert.assertEquals(heroErrorDto.getMessage(), "Incorrect request data");
        }

}
