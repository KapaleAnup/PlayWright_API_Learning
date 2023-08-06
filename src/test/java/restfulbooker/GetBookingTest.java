package restfulbooker;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GetBookingTest extends BaseAPI {

    @Test
    public void getBookingIdTest(){
     APIResponse bookingIdResponse =  apiRequestContext.get ( "https://restful-booker.herokuapp.com/booking/"+ CreateBookingTest.bookingId
        , RequestOptions.create ()
        .setHeader ( "Content-Type","application/json" ));

     String responseText= bookingIdResponse.text ( );
        System.out.println (responseText );

        Assert.assertEquals (bookingIdResponse.status (),200);

        System.out.println ("Booking id is fetched successfully!!" );


    }


}