package com.aplusplus.HotelBooking.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PromotionDTO {
    @NotBlank(message = "Percent of discount is required")
    private Double percentOfDiscount;
    private String description;
    @NotBlank(message = "Start date is required")
    private LocalDate startDate;
    @NotBlank(message = "End date is required")
    private LocalDate endDate;
}
