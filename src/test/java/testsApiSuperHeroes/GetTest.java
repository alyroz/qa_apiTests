package testsApiSuperHeroes;

import com.jayway.restassured.response.Response;
import dto.SuperHeroErrorDto;
import dto.SuperHeroResponseDto;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetTest extends BaseTest{

    String city = "Abc";
    String phone = "+81234567809";
    Response createResponse = apiSuperHeroes.createHero(city, phone);
    SuperHeroResponseDto heroCreatedResponseDto = createResponse.then().extract().response().as(SuperHeroResponseDto.class);
    int id = heroCreatedResponseDto.getId();

    Response getAllHeroesResponse = apiSuperHeroes.getAllHeroes();
    Response getHeroByIdResponse = apiSuperHeroes.getHero(id);
    SuperHeroResponseDto getHeroByIdResponseDto = getHeroByIdResponse.then().extract().response().as(SuperHeroResponseDto.class);

    @Test
    public void getAllHeroesStatusCodeTest(){
        getAllHeroesResponse.then().assertThat().statusCode(200);
    }

    @Test
    public void getHeroByIdSuccessStatusCodeTest(){

        getHeroByIdResponse.then().assertThat().statusLine("OK");
    }

    @Test
    public void getHeroByIdSuccessTest(){

        Assert.assertEquals(getHeroByIdResponseDto.getId(), id);
//        System.out.println("*****************" + id);  // 302, 207
    }

    @Test
    public void getHeroByIdNotFoundStatusCodeTest(){
        // create -> give.id -> removeById -> getById

        getCreatedAndRemovedHeroResponse().then().assertThat().statusCode(400);
    }

    @Test
    public void getHeroByIdNotFoundCodeTest(){
        // create -> give.id -> removeById -> getById
        Response getHeroByIdResponse = getCreatedAndRemovedHeroResponse();
        SuperHeroErrorDto heroErrorDto = getHeroByIdResponse.then().extract().response().as(SuperHeroErrorDto.class);

        Assert.assertTrue(heroErrorDto.getCode().equals("NOT_FOUND"));   // .get.message.equals("Superhero with id '302' was not found");
    }

    private Response getCreatedAndRemovedHeroResponse() {
        Response createResponse = apiSuperHeroes.createHero(city, phone);
        SuperHeroResponseDto heroCreatedResponseDto = createResponse.then().extract().response().as(SuperHeroResponseDto.class);
        int id = heroCreatedResponseDto.getId();
        apiSuperHeroes.removeHero(id);

        return apiSuperHeroes.getHero(id);
    }
}
