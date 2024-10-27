package com.aplusplus.HotelBooking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingDTO {
    @NotNull(message = "Check in date is required")
    private LocalDate checkInDate;
    @NotNull(message = "Check out date is required")
    private LocalDate checkoutDate;
    @NotNull(message = "Number of children is required")
    private int numOfChildren;
    @NotNull(message = "Number of adult is required")
    private int numOfAdults;
    @NotNull(message = "Number of guests if required")
    private int totalNumOfGuest;
    @NotBlank(message = "Number of booking code is required")
    private String bookingCode;
}
