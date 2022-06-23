package testsApiSuperHeroes;

import com.jayway.restassured.response.Response;
import dto.SuperHeroErrorDto;
import dto.SuperHeroResponseDto;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetTest extends BaseTest {

    Response responseAllData = apiSuperHeroes.createHero("2011-03-11", "Imatra", "Merja", "F", "Flying", "+81277567809");
    SuperHeroResponseDto heroResponseAllDataDto = apiSuperHeroes.getSuperHeroDto(responseAllData);
    int id = heroResponseAllDataDto.getId();

    Response getHeroById = apiSuperHeroes.getHeroById(id);
    SuperHeroResponseDto heroById = apiSuperHeroes.getSuperHeroDto(getHeroById);

    @Test
    public void getHeroByIdSuccessStatusCodeTest(){

        getHeroById.then().assertThat().statusCode(200); //Expected status line "OK" doesn't match actual status line "HTTP/1.1 200 ".
    }

    @Test
    public void getHeroByIdSuccessStatusLineTest(){

        getHeroById.then().assertThat().statusLine("OK");
        //Bug!!! Expected statusLine "OK" doesn't match actual status line "HTTP/1.1 200 ".
    }

    @Test
    public void getHeroByIdSuccessTest(){

        Assert.assertEquals(heroById.getId(), id);
    }

    @Test
    public void getHeroByIdNotFoundStatusCodeTest(){

        int id = 302;

        apiSuperHeroes.removeHeroById(id);
        Response getHeroById = apiSuperHeroes.getHeroById(id);

        getHeroById.then().assertThat().statusCode(404);
        // Bug!!! Expected status code <404> doesn't match actual status code <400>.
    }

    @Test
    public void getHeroByIdNotFoundCodeTest(){
        // create -> ResponseDto.get.id -> removeById -> getById -> 404 Not Found
        Response responseAllData = apiSuperHeroes.createHero("2011-03-11", "Imatra", "Merja", "F", "Flying", "+81277567809");
        SuperHeroResponseDto heroResponseAllDataDto = apiSuperHeroes.getSuperHeroDto(responseAllData);
        int id = heroResponseAllDataDto.getId();
        apiSuperHeroes.removeHeroById(id);

        Response getHeroByIdResponse = apiSuperHeroes.getHeroById(id);
        SuperHeroErrorDto heroErrorDto = getHeroByIdResponse.then().extract().response().as(SuperHeroErrorDto.class);

        Assert.assertEquals(heroErrorDto.getCode(), "Not Found");

        // Bug!!! expected [Not Found] but found [NOT_FOUND]
    }
}
