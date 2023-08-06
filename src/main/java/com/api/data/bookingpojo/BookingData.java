package com.api.data.bookingpojo;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BookingData {

    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    @JsonProperty("bookingdates")
    private BookingDates bookingdates;;
    private String additionalneeds;
}
