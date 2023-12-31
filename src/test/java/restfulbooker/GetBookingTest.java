package restfulbooker;

import com.api.constants.Endpoints;
import com.api.constants.StatusCode;
import com.api.utilities.ConfigProperties;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetBookingTest extends BaseAPI {

    @Test
    public void getBookingIdTest(){
     APIResponse bookingIdResponse =
             apiRequestContext.get ( ConfigProperties.readConfigProperties ( "bookurl" )+ Endpoints.BOOKING + CreateBookingTest.bookingId
        , RequestOptions.create ()
        .setHeader ( "Content-Type","application/json" ));

     String responseText= bookingIdResponse.text ( );
        System.out.println (responseText );

        Assert.assertEquals (bookingIdResponse.status (), StatusCode.SUCCESS.code );

        System.out.println ("Booking id is fetched successfully!!" );


    }


}
