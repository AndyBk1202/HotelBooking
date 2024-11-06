package com.aplusplus.HotelBooking.controller;

import com.aplusplus.HotelBooking.dto.Response;
import com.aplusplus.HotelBooking.dto.ReviewDTO;
import com.aplusplus.HotelBooking.service.interf.IReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
    private final IReviewService reviewService;

    @PostMapping("/create-review")
    public ResponseEntity<Response> createReview(@RequestBody ReviewDTO review){
        Response response = reviewService.createReview(review);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/get-review-by-id/{id}")
    public ResponseEntity<Response> getReviewById(@PathVariable("id") String id){
        Response response = reviewService.getReviewById(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/get-review-by-user-id")
    public ResponseEntity<Response> getReviewByUserId(@RequestParam String userId, @RequestParam String roomId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size){
        Pageable pageable = PageRequest.of(page, size);
        Response response = reviewService.getReviewByUserId(userId, roomId, pageable);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/get-review-by-room-id")
    public ResponseEntity<Response> getReviewByRoomId(@RequestParam String roomId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size){
        Pageable pageable = PageRequest.of(page, size);
        Response response = reviewService.getReviewByRoomId(roomId, pageable);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping("/update-review/{id}")
    public ResponseEntity<Response> updateReview(@RequestBody ReviewDTO review, @PathVariable("id") String id){
        Response response = reviewService.updateReview(review, id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/delete-review/{id}")
    public ResponseEntity<Response> deleteReview(@PathVariable("id") String id){
        Response response = reviewService.deleteReview(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
