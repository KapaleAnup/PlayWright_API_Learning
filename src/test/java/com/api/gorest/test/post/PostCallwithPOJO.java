package com.api.gorest.test.post;

import com.api.data.userspojo.User;
import com.api.endpoints.StatusCode;
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

public class PostCallwithPOJO {

    static Playwright playwright;
    static APIRequest apiRequest;
    static APIRequestContext apiRequestContext;
    static APIResponse apiResponse;

    static String emailid;

    @BeforeTest
    public void setUp ( ) {
        playwright = Playwright.create ( );
        apiRequest = playwright.request ( );
        apiRequestContext = apiRequest.newContext ( );
    }

    public static String getRandomEmail ( ) {

        emailid = "tenali.tamakrishna" + System.currentTimeMillis ( ) + "@gmail";
        return emailid;
    }

    @Test
    public void createUser ( ) throws IOException {

        //Create user data from pojo classes

        User userData = new User ( "Tenali Ramakrishna" , getRandomEmail ( ) , "male" , "active" );


        //Initiate the post call with valid headers
        apiResponse = apiRequestContext.post ( "https://gorest.co.in/public/v2/users" ,
                RequestOptions.create ( )
                        .setHeader ( "Content-Type" , "application/json" )
                        .setHeader ( "Authorization" , "Bearer 213296d3a224c4c4669fd1f4cbe3254ff92826cc45c3fdedaadb0e9754804442" )
                        .setData ( userData ) );

        //check the status and assert the data
        int statusCode = apiResponse.status ( );
        Assert.assertEquals ( statusCode , StatusCode.CREATED.code );

        System.out.println ("User Created successfully!!" );

        // Covert pojo to text
        String responseText = apiResponse.text ();
        ObjectMapper objectMapper = new ObjectMapper (  );
        User actualUser = objectMapper.readValue ( responseText, User.class );
        Assert.assertEquals ( actualUser.getEmail (), userData.getEmail () );
        Assert.assertEquals ( actualUser.getName (), userData.getName () );
        Assert.assertEquals ( actualUser.getStatus (), userData.getStatus ());

        //Get the user id from response and send it to get api user
        String  userId = actualUser.getId ();

        //Fetch the data though get api

        apiResponse = apiRequestContext.get ( "https://gorest.co.in/public/v2/users/" + userId ,
                RequestOptions.create ( )
                        .setHeader ( "Authorization" , "Bearer 213296d3a224c4c4669fd1f4cbe3254ff92826cc45c3fdedaadb0e9754804442" ) );


        int getstatusCode = apiResponse.status ( );
        System.out.println ( "Response status code is :" + getstatusCode );
        Assert.assertEquals ( getstatusCode , StatusCode.SUCCESS.code );
        Assert.assertEquals (actualUser.getId (), userId  );
        Assert.assertEquals (actualUser.getName (), userData.getName ()  );
        Assert.assertNotNull ( actualUser.getId () );

        System.out.println ("User Fetched successfully!!" );


    }
}
