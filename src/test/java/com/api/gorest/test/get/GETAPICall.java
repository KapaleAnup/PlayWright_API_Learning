package com.api.gorest.test.get;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class GETAPICall {
    static Playwright playwright;
    static APIRequest apiRequest;
    static APIRequestContext apiRequestContext;
    static APIResponse apiResponse;

    @BeforeTest
    public void setUp(){
       playwright = Playwright.create ();
       apiRequest = playwright.request ();
       apiRequestContext = apiRequest.newContext ();
    }

    @Test
    public void getUserApiWithQueryParam() throws IOException {
        apiResponse = apiRequestContext.get ( "https://gorest.co.in/public/v2/users",
                RequestOptions.create ().
                        setQueryParam ( "gender", "male" )
                        .setQueryParam ( "status","active"));

        int statusCode = apiResponse.status ();
        System.out.println ("Response status code is :" + statusCode);
        Assert.assertEquals ( statusCode,200 );

        String statusText = apiResponse.statusText ();
        System.out.println ("Response status Text  is :" + statusText);

        ObjectMapper objectMapper = new ObjectMapper (  );
        JsonNode jsonResponse =   objectMapper.readTree ( apiResponse.body () );
        String jsonPrettyResponse = jsonResponse.toPrettyString ();
        System.out.println (jsonPrettyResponse );
    }


    @Test
    public static void getUserApi() throws IOException {
       apiResponse = apiRequestContext.get ( "https://gorest.co.in/public/v2/users " );
        int statusCode = apiResponse.status ();
        System.out.println ("Response status code is :" + statusCode);
        Assert.assertEquals ( statusCode,200 );

        String statusText = apiResponse.statusText ();
        System.out.println ("Response status Text  is :" + statusText);

        ObjectMapper objectMapper = new ObjectMapper (  );
        JsonNode jsonResponse =   objectMapper.readTree ( apiResponse.body () );
        String jsonPrettyResponse = jsonResponse.toPrettyString ();
        System.out.println (jsonPrettyResponse );




    }


}
