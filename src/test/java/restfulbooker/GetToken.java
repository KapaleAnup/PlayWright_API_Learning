package restfulbooker;


import com.api.endpoints.StatusCode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GetToken {

     Playwright playwright;
     APIRequest apiRequest;
     APIRequestContext apiRequestContext;
     APIResponse apiResponse;

    static String TOKEN;

    @BeforeTest
    public void baseSetup(){
       playwright =  Playwright.create ();
       apiRequest = playwright.request ();
       apiRequestContext = apiRequest.newContext ();
    }

    @AfterTest
    public void tearDown(){
        playwright.close ();
    }

    @Test(description = "Validate the generated token")
    public void generateToken() throws IOException {

        Map<String, Object> credentials = new HashMap <> (  );
        credentials.put ( "username", "admin" );
        credentials.put ( "password" ,"password123");

        apiResponse =  apiRequestContext.post ( "https://restful-booker.herokuapp.com/auth",
               RequestOptions.create ()
               .setHeader ( "Content-Type", "application/json" )
               .setData ( credentials ));

        Assert.assertEquals ( apiResponse.status (), StatusCode.SUCCESS.code );

       //Convert api response to text
       String tokenResponse = apiResponse.text ();

       //Convert text to json
        ObjectMapper objectMapper = new ObjectMapper (  );
        JsonNode response =  objectMapper.readTree ( apiResponse.body () );
        System.out.println (response.toPrettyString () );

        //Capture token from the response
        TOKEN =  response.get ( "token" ).asText ();
        System.out.println ("Generated token is : "+ TOKEN );


    }
}
