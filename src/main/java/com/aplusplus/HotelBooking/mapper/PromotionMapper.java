package com.aplusplus.HotelBooking.mapper;

import org.mapstruct.Mapper;
import com.aplusplus.HotelBooking.dto.PromotionDTO;
import com.aplusplus.HotelBooking.model.Promotion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class PromotionMapper {
    public abstract PromotionDTO toPromotionDTO(Promotion promotion);
    public abstract Promotion toPromotion(PromotionDTO promotionDTO);
}
