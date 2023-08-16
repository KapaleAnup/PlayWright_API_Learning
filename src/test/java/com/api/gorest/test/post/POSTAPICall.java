package com.api.gorest.test.post;

import com.api.constants.StatusCode;
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
import java.util.HashMap;
import java.util.Map;

public class POSTAPICall {

    static Playwright playwright;
    static APIRequest apiRequest;
    static APIRequestContext apiRequestContext;
    static APIResponse apiResponse;

    static String emailid;

    @BeforeTest
    public void setUp(){
        playwright = Playwright.create ();
        apiRequest = playwright.request ();
        apiRequestContext = apiRequest.newContext ();
    }

    public static String getRandomEmail(){

        emailid = "tenali.tamakrishna" + System.currentTimeMillis ()+"@gmail";
        return emailid;
    }

    @Test
    public void createUser() throws IOException {
        //Create Json data
        Map<String, Object> userData = new HashMap <> (  );
        userData.put ( "name" , "Tenali Ramakrishna");
        userData.put ( "gender" , "male");
        userData.put ( "email" , getRandomEmail ());
        userData.put ( "status" , "active");

        //Initiate the post call with valid headers
        apiResponse =  apiRequestContext.post ( "https://gorest.co.in/public/v2/users",
                RequestOptions.create ()
                        .setHeader ( "Content-Type","application/json" )
                        .setHeader ( "Authorization" ,"Bearer 213296d3a224c4c4669fd1f4cbe3254ff92826cc45c3fdedaadb0e9754804442")
                        .setData (  userData) );

        //check the status and assert the data
        int statusCode = apiResponse.status ();
        Assert.assertEquals ( statusCode, StatusCode.CREATED.code );

        //convert Byte response to Json object
        ObjectMapper objectMapper = new ObjectMapper (  );
        JsonNode postJsoneResponse = objectMapper.readTree ( apiResponse.body () );
        System.out.println (postJsoneResponse.toPrettyString () );

        //Fetch the response value from the json
        String userId =  postJsoneResponse.get ( "id" ).asText ();
        System.out.println ("user id is :"+userId );

        //Fetch the data though get api

        apiResponse = apiRequestContext.get ( "https://gorest.co.in/public/v2/users/"+userId,
                RequestOptions.create ()
                        .setHeader ( "Authorization" ,"Bearer 213296d3a224c4c4669fd1f4cbe3254ff92826cc45c3fdedaadb0e9754804442"));


        int getstatusCode = apiResponse.status ();
        System.out.println ("Response status code is :" + getstatusCode);
        Assert.assertEquals ( getstatusCode,StatusCode.SUCCESS.code );
        Assert.assertTrue ( apiResponse.text ().contains ( userId ) );
        Assert.assertTrue ( apiResponse.text ().contains ( "Tenali Ramakrishna" ) );
        Assert.assertTrue ( apiResponse.text ().contains ( emailid ));



    }


}
