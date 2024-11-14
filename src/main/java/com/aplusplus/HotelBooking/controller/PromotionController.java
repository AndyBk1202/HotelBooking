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
import com.aplusplus.HotelBooking.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/promotions")
@PreAuthorize("hasAuthority('ADMIN')")
public class PromotionController {
    // user service createPromotion
    @PostMapping("create-promotion")
    public ResponseEntity<Response> createPromotion(@RequestBody PromotionDTO promotion){
        return null;
    }

    // use service getAllPromotion
    @GetMapping("/get-all-promotions")
    public ResponseEntity<Response> getAllPromotion(){
        return null;
    }

    // user service getPromotionById
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Response> getPromotion(@PathVariable("id") String id){
        return null;
    }

    // user service updatePromotion
    @PostMapping("update-promotion/{id}")
    public ResponseEntity<Response> updatePromotion(@RequestBody PromotionDTO promotion){
        return null;
    }

    // user service deletePromotion
    @PostMapping("delete-promotion/{id}")
    public ResponseEntity<Response> deletePromotion(@PathVariable("id") String id){
        return null;
    }

    // user service applyPromotionToRoom
    @GetMapping("get-promotion-by-room/{room_id}")
    public ResponseEntity<Response> getPromotionByRoom(@PathVariable("room_id") String room_id){
        return null;
    }


}
