package testsApi;

import com.jayway.restassured.RestAssured;
import dto.HeroResponseDto;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class GetTest extends BaseTest{

    Integer id= 56789;

    @BeforeMethod
    public void precondition(){
        RestAssured.baseURI = "";
        RestAssured.basePath = "api";
    }
    @Test
    public void deleteHeroSuccess(){

        given().header("Description", id)
                .when()
                .get("superheroes/{id}",19987)
                .then()
                .statusCode(200)
                .assertThat()
                //.body();
                .extract().response().as(HeroResponseDto.class);

        //for (RecordDto record : allRecordsDto.getRecords()) {
        //   System.out.println(record.getId());
        //  System.out.println("***********");
    }
}
