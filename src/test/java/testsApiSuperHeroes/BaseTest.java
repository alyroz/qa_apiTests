package testsApiSuperHeroes;

import org.testng.annotations.Listeners;
import requestHelper.ApiSuperHeroes;
import utils.ListenerUtils;
import utils.LoggerUtils;

@Listeners(ListenerUtils.class)
public class BaseTest extends LoggerUtils {
    ApiSuperHeroes apiSuperHeroes = new ApiSuperHeroes();

}
