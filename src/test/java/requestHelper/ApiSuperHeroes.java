package requestHelper;

import com.jayway.restassured.response.Response;
import dto.SuperHeroRequestDto;
import utils.PropertiesUtils;
import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import static com.jayway.restassured.RestAssured.given;


public class ApiSuperHeroes extends BaseRequest {

    protected final String URL_SUPERHEROES = URL_START + PropertiesUtils.getProperty("pathSuperHeroes");

    public Response getAllHeroes() {

        return given().get(URL_SUPERHEROES);
    }

    public Response getHero(int id) {

        return given().get(URL_SUPERHEROES + '/' + id);
    }

    public Response removeHero(int id) {

        return given().delete(URL_SUPERHEROES + '/' + id);
    }

//    public Response createHero(){
//
//        SuperHeroRequestDto hero = getHeroRequestDto();
//
//        return given()
//                .header("Content-Type", "application/json")
//                .body(hero)
//                .when()
//                .post(URL_SUPERHEROES);
//    }

    public Response createHero(String city, String phone) {
        SuperHeroRequestDto hero = getHeroRequestDto(city, phone);

        return given()
                .header("Content-Type", "application/json")
                .body(hero)
                .when()
                .post(URL_SUPERHEROES);
    }

    public Response createNegativeHero(String city, String phone) {
        SuperHeroRequestDto hero = SuperHeroRequestDto.builder()
                .birthDate("")
                .city(city)
                .fullName("Pkjay")
                .gender("F")
                .mainSkill("jlkjl")
                .phone(phone)
                .build();

        return given()
                .header("Content-Type", "application/json")
                .body(hero)
                .when()
                .post(URL_SUPERHEROES);
    }

 /*   private SuperHeroRequestDto getHeroRequestDto() {
    //    String randomBirthDate = getRandomBirthDate();
        String randomBirthDate = "1900" + randomNum(2) + "05-17";
        String randomCity = getRandomWord(4);
        String randomFullName = getRandomWord(7);
        String randomPhone = getRandomPhone(10);

        SuperHeroRequestDto hero = SuperHeroRequestDto.builder()
                .birthDate(randomBirthDate)
                .city(randomCity)
                .fullName(randomFullName)
                .gender("F")
                .mainSkill("jlkjl")
                .phone("+8" + randomPhone)
                .build();
        return hero;
    }
*/
    private SuperHeroRequestDto getHeroRequestDto(String city, String phone) {
        //    String randomBirthDate = getRandomBirthDate();
        //String randomBirthDate = "1900" + randomNum(2) + "05-17";
        String birthDate = "2012-05-17";
        String randomFullName = getRandomWord(7);

        SuperHeroRequestDto hero = SuperHeroRequestDto.builder()
                .birthDate(birthDate)
                .city(city)
                .fullName(randomFullName)
                .gender("F")
                .mainSkill("jlkjl")
                .phone(phone)
                .build();
        return hero;
    }

    private String getRandomWord(int len) {
        String[] strArr = { "A", "P", "Q", "R", "E", "S", "T", "I", "H", "L", "U", "V", "W" };

        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            strBuilder.append(strArr[new Random().nextInt(strArr.length)]);
        }
        return strBuilder.toString();
    }

//    private String getRandomBirthDate() {
//        LocalDate startDate = LocalDate.of(1960, 1, 1);
//        LocalDate endDate = LocalDate.of(2010, 12, 31);
//
//        String randomBirthDate = new LocalDate(ThreadLocalRandom.current().nextLong(startDate.getTime(),
//                        endDate.getTime()).findAny()).toString();   // get.Long - ???
//        return randomBirthDate;
//    }

    private String getRandomPhone(int len) {
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            strBuilder.append(randomNum(len));
        }
        return strBuilder.toString();
    }

    public static char randomNum(int len) {
        char[] num = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        return num[(int) Math.floor(Math.random() * len)];
    }

}
