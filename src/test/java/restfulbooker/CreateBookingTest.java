package restfulbooker;

import com.api.data.bookingpojo.BookingData;
import com.api.data.bookingpojo.BookingDates;
import com.api.data.bookingpojo.BookingPayload;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateBookingTest extends BaseAPI {

     static int bookingId;

    @Test(description = "Create booking details")
    public void createBookingTest() throws JsonProcessingException {

       BookingData bookingData =  BookingData.builder ()
                .firstname ( "JimTet" )
                .lastname ( "BrownTest" )
                .totalprice ( 1112 )
                .depositpaid ( true )
                .bookingdates ( BookingDates.builder ()
                                .checkin ( "2018-01-01" )
                                .checkout ( "2018-01-01" )
                        .build ())
               .additionalneeds ( "Breakfast" ).build ();

        APIResponse bookingResponse = apiRequestContext.post ( "https://restful-booker.herokuapp.com/booking",
                RequestOptions.create ()
                .setHeader ( "Content-Type","application/json" )
                .setData (bookingData ));

        String bookingDataText = bookingResponse.text ();
        System.out.println (bookingDataText);

        Assert.assertEquals (bookingResponse.status (), 200);

        ObjectMapper objectMapper = new ObjectMapper (  );
        BookingPayload payload = objectMapper.readValue ( bookingDataText, BookingPayload.class );

        Assert.assertNotNull ( payload.getBookingid () );
        Assert.assertEquals ( payload.getBookingData ().getFirstname (), bookingData.getFirstname () );
        Assert.assertEquals ( payload.getBookingData ().getBookingdates ().getCheckin (), bookingData.getBookingdates ().getCheckin ());

        System.out.println ("Booking Data successfully created!! " );

        // Fetch the response data

        bookingId = payload.getBookingid ();
        System.out.println ("Booking id is :"+bookingId );
        System.out.println ( "Booking first name is :"+ payload.getBookingData ().getFirstname ());



    }


}