package restfulbooker;

import com.api.data.bookingpojo.BookingData;
import com.api.data.bookingpojo.BookingDates;
import com.api.endpoints.Endpoints;
import com.api.constants.StatusCode;
import com.api.utilities.ConfigProperties;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
import org.testng.annotations.Test;


public class UpdateBookingTest extends BaseAPI{


    @Test(description = "To validate update booking details")
    public void updateBookingTest(){

        BookingData bookingData =  BookingData.builder ()
                .firstname ("User")
                .lastname ( "Testing" )
                .totalprice ( 100)
                .depositpaid ( true )
                .bookingdates ( BookingDates.builder ()
                        .checkin ( "2018-01-01" )
                        .checkout ( "2019-01-01" )
                        .build ())
                .additionalneeds ( "Dinner" ).build ();

      APIResponse updatedBookingResponse =
              apiRequestContext.put ( ConfigProperties.readConfigProperties ( "bookurl" )+ Endpoints.BOOKING+CreateBookingTest.bookingId,
                RequestOptions.create ()
                .setHeader ( "Content-Type","application/json" )
                .setHeader ( "Accept"," application/json" )
                .setHeader ( "Cookie","token="+ GetToken.TOKEN)
                .setData ( bookingData ));

        Assert.assertEquals (updatedBookingResponse.status (), StatusCode.SUCCESS.code );
        System.out.println ("Booking data updated successfully!!" );

    }



}
