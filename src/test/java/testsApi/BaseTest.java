package testsApi;

import org.testng.annotations.Listeners;
import pages.CreatePage;
import pages.GetPage;
import pages.RemovePage;
import utils.ListenerUtils;
import utils.LoggerUtils;

@Listeners(ListenerUtils.class)
public class BaseTest extends LoggerUtils {
    CreatePage createPage = new CreatePage();
    GetPage getPage = new GetPage();
    RemovePage removePage = new RemovePage();
}
