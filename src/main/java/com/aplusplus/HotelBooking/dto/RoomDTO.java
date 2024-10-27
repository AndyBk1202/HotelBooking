package com.aplusplus.HotelBooking.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomDTO {
    private Long id;
    private String roomType;
    private String roomSize;
    private Double roomPrice;
    private String roomDescription;
    private String roomStatus;
//  private String bookingCode;
    private String roomPhotoUrl;
    private int roomCapacity;
    private int roomAmount;
}
