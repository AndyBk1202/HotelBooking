package com.aplusplus.HotelBooking.service.interf;

import com.aplusplus.HotelBooking.dto.PromotionDTO;
import com.aplusplus.HotelBooking.dto.Response;

public interface IPromotionService {
    Response createPromotion(PromotionDTO promotion);
    Response getPromotionById(String promotionId);
    Response getAllPromotion();
    Response updatePromotion(PromotionDTO promotion);
    Response deletePromotion(String promotionId);

    Response applyPromotionToRoom(String promotionId, String roomId);
}
