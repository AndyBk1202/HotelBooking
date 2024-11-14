package com.aplusplus.HotelBooking.service.implement;

import com.aplusplus.HotelBooking.dto.PromotionDTO;
import com.aplusplus.HotelBooking.dto.Response;
import com.aplusplus.HotelBooking.exception.OurException;
import com.aplusplus.HotelBooking.model.Promotion;
import com.aplusplus.HotelBooking.repository.PromotionRepo;
import com.aplusplus.HotelBooking.service.interf.IPromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PromotionService implements IPromotionService {
    private final PromotionRepo promotionRepository;

    @Override
    public Response createPromotion(PromotionDTO promotion) {
        Response response = new Response();

        return null;
    }

    @Override
    public Response getPromotionById(String promotionId) {
        Response response = new Response();
        try {
//            Optional<Promotion> promotion = promotionRepository.findById(Long.parseLong(promotionId)).orElseThrow(() -> new OurException("Promotion not found"));
        }
        catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }
        catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }
        return null;
    }

    @Override
    public Response getAllPromotion() {
        return null;
    }

    @Override
    public Response updatePromotion(PromotionDTO promotion) {
        return null;
    }

    @Override
    public Response deletePromotion(String promotionId) {
        return null;
    }

    @Override
    public Response applyPromotionToRoom(String promotionId, String roomId) {
        return null;
    }

    @Override
    public Response getPromotionByRoomId(String roomId) {
        return null;
    }
}
