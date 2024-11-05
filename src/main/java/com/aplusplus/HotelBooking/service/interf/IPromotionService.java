package com.aplusplus.HotelBooking.service.interf;

import com.aplusplus.HotelBooking.dto.PromotionDTO;
import com.aplusplus.HotelBooking.dto.Response;

public interface IPromotionService {
    Response createPromotion(PromotionDTO promotion); // thêm list các roomId
    Response getPromotionById(String promotionId); // Admin xem chi tiết promotion
    Response getAllPromotion(); // Admin
    Response updatePromotion(PromotionDTO promotion); // Admin
    Response deletePromotion(String promotionId);
    Response applyPromotionToRoom(String promotionId, String roomId); // Liên quan Booking
    Response getPromotionByRoomId(String roomId); // List các promotion áp dụng cho 1 room
}
