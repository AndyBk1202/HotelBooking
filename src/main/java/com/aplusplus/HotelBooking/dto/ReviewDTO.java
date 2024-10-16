package com.aplusplus.HotelBooking.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReviewDTO {
    @Min(value = 1, message = "Review rate must be from 1 to 5")
    @Max(value = 5, message = "Review rate must be from 1 to 5")
    private int reviewRate;
    private String comment;
}
