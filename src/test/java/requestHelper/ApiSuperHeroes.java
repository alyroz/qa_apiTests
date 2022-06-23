package requestHelper;

import com.jayway.restassured.response.Response;
import dto.SuperHeroRequestDto;
import dto.SuperHeroResponseDto;
import utils.PropertiesUtils;
import utils.RandomUtils;

import static com.jayway.restassured.RestAssured.given;


public class ApiSuperHeroes extends BaseRequest {

    protected final String URL_SUPERHEROES = URL_START + PropertiesUtils.getProperty("pathSuperHeroes");

    public Response getAllHeroes() {

        return given().get(URL_SUPERHEROES);
    }

    public Response getHeroById (int id) {

        return given().get(URL_SUPERHEROES + '/' + id);
    }

    public Response removeHeroById (int id) {

        return given().delete(URL_SUPERHEROES + '/' + id);
    }

    public Response createHero() {
        SuperHeroRequestDto hero = getHeroRequestDto();

        return given()
                .header("Content-Type", "application/json")
                .body(hero)
                .when()
                .post(URL_SUPERHEROES);
    }

    public Response createHero(String birthDate, String city, String fullName, String gender,
                                       String mainSkill, String phone) {
        SuperHeroRequestDto hero = getHeroRequestDto(birthDate, city, fullName, gender,
                mainSkill, phone);
        return given()
                .header("Content-Type", "application/json")
                .body(hero)
                .when()
                .post(URL_SUPERHEROES);
    }

    public SuperHeroResponseDto getSuperHeroDto (Response response) {
        return response.then().extract().response().as(SuperHeroResponseDto.class);
    }

    private SuperHeroRequestDto getHeroRequestDto(String birthDate, String city, String fullName, String gender,
                                                  String mainSkill, String phone) {
        return SuperHeroRequestDto.builder()
                .birthDate(birthDate)
                .city(city)
                .fullName(fullName)
                .gender(gender)
                .mainSkill(mainSkill)
                .phone(phone)
                .build();
    }

    private SuperHeroRequestDto getHeroRequestDto() {

        return SuperHeroRequestDto.builder()
                .birthDate("19" + RandomUtils.createRandomNumbers(2) + "-07-16")
                .city(RandomUtils.createRandomWord(7))
                .fullName(RandomUtils.createRandomWord(7))
                .gender("F")
                .mainSkill(RandomUtils.createRandomWord(5))
                .phone("+8" + RandomUtils.createRandomNumbers(10))
                .build();
    }
}