package com.aplusplus.HotelBooking.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PromotionDTO {
    private Long id;
    private Double percentOfDiscount;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String[] listRoomTypes;
}
