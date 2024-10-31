package com.aplusplus.HotelBooking.controller;

import com.aplusplus.HotelBooking.dto.BookingDTO;
import com.aplusplus.HotelBooking.dto.DateRequest;
import com.aplusplus.HotelBooking.dto.Response;
import com.aplusplus.HotelBooking.service.implement.BookingService;
import com.aplusplus.HotelBooking.service.interf.IBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final IBookingService bookingService;

    @PostMapping
    public ResponseEntity<Response> createBooking(@RequestBody BookingDTO request){
        Response response = bookingService.createBooking(request);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/get-all-bookings")
    public ResponseEntity<Response> getAllBooking(){
        Response response = bookingService.getAllBooking();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Response> getBooking(@PathVariable("id") String id){
        Response response = bookingService.getBookingById(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("get-by-date")
    public ResponseEntity<Response> getBookingByDate(@RequestBody DateRequest date) {
        LocalDate checkInDate = date.getCheckInDate();
        LocalDate checkoutDate = date.getCheckoutDate();
        Response response = bookingService.getBookingByDate(checkInDate, checkoutDate);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/get-by-user-id/{userId}")
    public ResponseEntity<Response> getBookingsByUserId(@PathVariable("userId") Long userId){
        Response response = bookingService.getBookingsByUserId(userId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    // Doesn't work because createBooking doesn't have add room to booking
    @GetMapping("/get-by-room-id/{roomId}")
    public ResponseEntity<Response> getBookingByRoomId(@PathVariable("roomId") String roomId){
        Response response = bookingService.getBookingByRoomId(roomId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/cancel-booking/{bookingId}")
    public ResponseEntity<Response> cancelBooking(@PathVariable("bookingId") String bookingId){
        Response response = bookingService.cancelBooking(bookingId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
