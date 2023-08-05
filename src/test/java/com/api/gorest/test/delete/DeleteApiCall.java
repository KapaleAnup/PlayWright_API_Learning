package com.api.gorest.test.delete;

import com.api.data.User;
import com.api.data.UsersLambok;
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

public class DeleteApiCall {


    static Playwright playwright;
    static APIRequest apiRequest;
    static APIRequestContext apiRequestContext;
    //static APIResponse apiResponse;

    static String emailid;
    static String userId;

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

        //Create user data from pojo classes with lombk method
        UsersLambok userData = UsersLambok.builder ( )
                .name ( "Tenali Ramakrishna" )
                .email ( getRandomEmail ( ) )
                .gender ( "male" )
                .status ( "active" ).build ( );


        //Initiate the post call with valid headers and data
        APIResponse postApiResponse = apiRequestContext.post ( "https://gorest.co.in/public/v2/users" ,
                RequestOptions.create ( )
                        .setHeader ( "Content-Type" , "application/json" )
                        .setHeader ( "Authorization" , "Bearer 213296d3a224c4c4669fd1f4cbe3254ff92826cc45c3fdedaadb0e9754804442" )
                        .setData ( userData ) );

        //check the status and assert the data
        int statusCode = postApiResponse.status ( );
        Assert.assertEquals ( statusCode , 201 );

        System.out.println ( "User Created successfully!!" );

        // Covert pojo to text
        String responseText = postApiResponse.text ( );
        ObjectMapper objectMapper = new ObjectMapper ( );
        User actualUser = objectMapper.readValue ( responseText , User.class );
        Assert.assertEquals ( actualUser.getEmail ( ) , userData.getEmail ( ) );
        Assert.assertEquals ( actualUser.getName ( ) , userData.getName ( ) );
        Assert.assertEquals ( actualUser.getStatus ( ) , userData.getStatus ( ) );

        //Get the user id from response and send it to get api user
        userId = actualUser.getId ( );

        // Update the user data with some other values
        userData.setStatus ( "inactive" );


        // PUT CALL
        APIResponse putApiResponse = apiRequestContext.put ( "https://gorest.co.in/public/v2/users/" + userId ,
                RequestOptions.create ( )
                        .setHeader ( "Content-Type" , "application/json" )
                        .setHeader ( "Authorization" , "Bearer 213296d3a224c4c4669fd1f4cbe3254ff92826cc45c3fdedaadb0e9754804442" )
                        .setData ( userData ) );


        //check the status and assert the data
        int putApistatusCode = putApiResponse.status ( );
        Assert.assertEquals ( putApistatusCode , 200 );

        String putResponseText = putApiResponse.text ( );
        UsersLambok actualputUser = objectMapper.readValue ( putResponseText , UsersLambok.class );
        Assert.assertEquals ( actualputUser.getId ( ) , userId );
        Assert.assertEquals ( actualputUser.getStatus ( ) , userData.getStatus ( ) );
        System.out.println ( "User Updated successfully!!" );


        //Fetch the data though get api

        APIResponse getApiResponse = apiRequestContext.get ( "https://gorest.co.in/public/v2/users/" + userId ,
                RequestOptions.create ( )
                        .setHeader ( "Authorization" , "Bearer 213296d3a224c4c4669fd1f4cbe3254ff92826cc45c3fdedaadb0e9754804442" ) );


        int getstatusCode = getApiResponse.status ( );
        System.out.println ( "Response status code is :" + getstatusCode );

        UsersLambok actuagetlUser = objectMapper.readValue ( responseText , UsersLambok.class );
        Assert.assertEquals ( getstatusCode , 200 );
        Assert.assertEquals ( actuagetlUser.getId ( ) , userId );
        Assert.assertEquals ( actuagetlUser.getName ( ) , userData.getName ( ) );
        Assert.assertNotNull ( actuagetlUser.getId ( ) );

        System.out.println ( "User Fetched successfully!!" );


        //Delete Call
        APIResponse deleteApiResponse = apiRequestContext.delete ( "https://gorest.co.in/public/v2/users/" + userId ,
                RequestOptions.create ( )
                        .setHeader ( "Authorization" , "Bearer 213296d3a224c4c4669fd1f4cbe3254ff92826cc45c3fdedaadb0e9754804442" ) );


        int deleteStatusCode = deleteApiResponse.status ( );
        Assert.assertEquals ( deleteStatusCode , 204 );

        String deleteResponseText = deleteApiResponse.text ( );


        //Get all to check the record is deleted

        APIResponse getApiResponse1 = apiRequestContext.get ( "https://gorest.co.in/public/v2/users/" + userId ,
                RequestOptions.create ( )
                        .setHeader ( "Authorization" , "Bearer 213296d3a224c4c4669fd1f4cbe3254ff92826cc45c3fdedaadb0e9754804442" ) );


        int getstatusCode1 = getApiResponse1.status ( );
        System.out.println ( "Response status code is :" + getstatusCode1 );
        Assert.assertEquals ( getstatusCode1 ,  404);

        Assert.assertTrue ( getApiResponse1.text ().contains ( "Resource not found" ) );

        System.out.println ("User successfully deleted !!" );


    }
}