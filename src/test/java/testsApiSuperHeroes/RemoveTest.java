package testsApiSuperHeroes;

import com.jayway.restassured.response.Response;
import dto.SuperHeroErrorDto;
import dto.SuperHeroResponseDto;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RemoveTest extends BaseTest{

    @Test
    public void removeSuccessStatusCodeTest(){
        Response responseAllData = apiSuperHeroes.createHero("2011-03-11", "Imatra", "Merja", "F", "Flying", "+81277567809");
        SuperHeroResponseDto heroResponseAllDataDto = apiSuperHeroes.getSuperHeroDto(responseAllData);
        int id = heroResponseAllDataDto.getId();

        Response removeHeroById = apiSuperHeroes.removeHeroById(id);

        removeHeroById.then().assertThat().statusCode(200);
    }

    @Test
    public void removeSuccessTest(){
        // create -> ResponseDto.get.id  -> removeById -> getById -> 403 NOT_FOUND
        Response responseAllData = apiSuperHeroes.createHero("2011-03-11", "Imatra", "Merja", "F", "Flying", "+81277567809");
        SuperHeroResponseDto heroResponseAllDataDto = apiSuperHeroes.getSuperHeroDto(responseAllData);
        int id = heroResponseAllDataDto.getId();

        apiSuperHeroes.removeHeroById(id);

        Response getHeroByIdResponse = apiSuperHeroes.getHeroById(id);
        SuperHeroErrorDto heroErrorDto = getHeroByIdResponse.then().extract().response().as(SuperHeroErrorDto.class);

        Assert.assertEquals(heroErrorDto.getCode(), "NOT_FOUND");
    }

@Test
    public void removeNegativeStatusTest(){
    // create -> give.id -> 2 x removeById -> getById -> 403 NOT_FOUND
    Response responseAllData = apiSuperHeroes.createHero("2011-03-11", "Imatra", "Merja", "F", "Flying", "+81277567809");
    SuperHeroResponseDto heroResponseAllDataDto = apiSuperHeroes.getSuperHeroDto(responseAllData);
    int id = heroResponseAllDataDto.getId();

    apiSuperHeroes.removeHeroById(id);
    Response removeHeroById = apiSuperHeroes.removeHeroById(id);
    //SuperHeroErrorDto heroErrorDto = removeHeroById.then().extract().response().as(SuperHeroErrorDto.class);
    SuperHeroResponseDto removeHeroByIdDto = removeHeroById.then().extract().response().as(SuperHeroResponseDto.class);

    Assert.assertNotEquals(removeHeroByIdDto.getId(), id);
}

    // Bug!!! Allows to delete id that already was deleted and doesn't exist!

}
