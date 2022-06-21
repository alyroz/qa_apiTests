package testsApi;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.HeroRequestDto;
import dto.HeroResponseDto;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class CreateTest extends BaseTest{
    @BeforeMethod
    public void precondition() {
        RestAssured.baseURI = "https://superhero.qa-test.csssr.com/";
        RestAssured.basePath = "api";
    }
    @Test
    public void createTestSucces() {
        HeroRequestDto hero = HeroRequestDto.builder()
                .birthDate("")
                .city("Dd12345~")
                .fullName("")
                .gender("")
                .phone("")
                .build();

        HeroResponseDto responseHDto = given()
                .contentType("application/json")
                .body(hero)
                .when()
                .post("superheroes")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(HeroResponseDto.class);

        System.out.println( responseHDto.getId());
        // System.out.println(responseHDto.getBirthDate());
        //System.out.println(responseHDto.getFullName());

    }
    @Test
    public void createHeroNeg(){

        HeroRequestDto hero = HeroRequestDto.builder()
                .birthDate("")
                .city("Dd12345~")
                .fullName("")
                .gender("")
                .phone("")
                .build();

        String message = given().contentType(ContentType.JSON)
                .body(hero)
                .when()
                .get("superheroes")
                .then()
                .assertThat().statusCode(401)
                .extract().path("message");

        System.out.println(message);
        Assert.assertEquals(message,"Unauthorized");
    }


}
