package com.aplusplus.HotelBooking.controller;

import com.aplusplus.HotelBooking.dto.BookingDTO;
import com.aplusplus.HotelBooking.dto.DateRequest;
import com.aplusplus.HotelBooking.dto.PromotionDTO;
import com.aplusplus.HotelBooking.dto.Response;
import com.aplusplus.HotelBooking.exception.OurException;
import com.aplusplus.HotelBooking.model.Booking;
import com.aplusplus.HotelBooking.model.User;
import com.aplusplus.HotelBooking.repository.UserRepo;
import com.aplusplus.HotelBooking.service.implement.BookingService;
import com.aplusplus.HotelBooking.service.interf.IBookingService;
import com.aplusplus.HotelBooking.service.interf.IPromotionService;
import com.aplusplus.HotelBooking.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/promotions")
public class PromotionController {

    @Autowired
    private  IPromotionService promotionService;

    // user service createPromotion
    @PostMapping("/create-promotion")
    public ResponseEntity<Response> createPromotion(@RequestBody PromotionDTO promotion) {
        Response response = promotionService.createPromotionForRoomType(promotion, promotion.getListRoomTypes());
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    // use service getAllPromotion
    @GetMapping("/get-all-promotions")
    public ResponseEntity<Response> getAllPromotion(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size){
        Pageable pageable = PageRequest.of(page, size);
        Response response = promotionService.getAllPromotion(pageable);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    // user service getPromotionById
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Response> getPromotion(@PathVariable("id") String id){
        Response response = promotionService.getPromotionById(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    // user service updatePromotion
    @PostMapping("/update-promotion/{id}")
    public ResponseEntity<Response> updatePromotion(@RequestBody PromotionDTO promotion){
        Response response = promotionService.updatePromotion(promotion);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    // user service deletePromotion
    @PostMapping("/delete-promotion/{id}")
    public ResponseEntity<Response> deletePromotion(@PathVariable("id") String id){
        return null;
    }

    // user service applyPromotionToRoom
    @GetMapping("/get-promotion-by-room/{room_id}")
    public ResponseEntity<Response> getPromotionByRoom(@PathVariable("room_id") String room_id){
        return null;
    }


}
