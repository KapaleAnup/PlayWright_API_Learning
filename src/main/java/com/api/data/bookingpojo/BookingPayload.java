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
public class BookingPayload {
    @JsonProperty("bookingid")
    private int bookingid;
    @JsonProperty("booking")
    private BookingData bookingData;
}
