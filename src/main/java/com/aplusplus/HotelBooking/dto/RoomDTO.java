package com.aplusplus.HotelBooking.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomDTO {
    @NotBlank(message = "Room type is required")
    private String roomType;
    @NotBlank(message = "Room size is required")
    private String roomSize;
    @NotBlank(message = "Room price is required")
    private Double roomPrice;
    private String roomDescription;
    private String roomStatus;
    private String bookingCode;
    private String roomPhotoUrl;
    @NotBlank(message = "Room capacity is required")
    private int roomCapacity;
}
