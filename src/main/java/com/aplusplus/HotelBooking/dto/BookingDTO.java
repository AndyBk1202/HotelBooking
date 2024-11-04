package com.aplusplus.HotelBooking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingDTO {
    private Long id;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int numOfChildren;
    private int numOfAdults;
    private int totalNumOfGuest;
    private String bookingCode;
    private RoomDTO room;
}
