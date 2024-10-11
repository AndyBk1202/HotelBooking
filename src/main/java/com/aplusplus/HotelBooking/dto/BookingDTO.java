package com.aplusplus.HotelBooking.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingDTO {
    @NotBlank(message = "Check in date is required")
    private LocalDate checkInDate;
    @NotBlank(message = "Check out date is required")
    private LocalDate checkoutDate;
    @NotBlank(message = "Number of children is required")
    private int numOfChildren;
    @NotBlank(message = "Number of adult is required")
    private int numOfAdults;
    @NotBlank(message = "Number of guests if required")
    private int totalNumOfGuest;
    @NotBlank(message = "Number of booking code is required")
    private String bookingCode;
}
