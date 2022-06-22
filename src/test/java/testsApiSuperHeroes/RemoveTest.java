package testsApiSuperHeroes;

import com.jayway.restassured.response.Response;
import dto.SuperHeroResponseDto;
import org.testng.annotations.Test;

public class RemoveTest extends BaseTest{

//   int id = 302;
    String city = "Abc";
    String phone = "+81234567809";
    Response createResponse = apiSuperHeroes.createHero(city, phone);
    SuperHeroResponseDto heroCreatedResponseDto = createResponse.then().extract().response().as(SuperHeroResponseDto.class);
    int id = heroCreatedResponseDto.getId();
    Response removeResponse = apiSuperHeroes.removeHero(id);


    @Test
    public void removeSuccessStatusCodeTest(){

        removeResponse.then().assertThat().statusCode(200);
        // Bug!!! Allows to delete id that already was deleted and doesn't exist!
    }


}
