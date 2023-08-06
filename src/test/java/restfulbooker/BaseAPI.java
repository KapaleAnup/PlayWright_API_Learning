package restfulbooker;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseAPI {

    Playwright playwright;
    APIRequest apiRequest;
    static APIRequestContext apiRequestContext;

    /*
    This method will help to initialize the setup.
     */
    @BeforeTest
    public void baseSetup(){
        playwright = Playwright.create ();
        apiRequest = playwright.request ();
        apiRequestContext = apiRequest.newContext ();
    }

    @AfterTest
    public void tearDown(){
        playwright.close ();
    }

}
