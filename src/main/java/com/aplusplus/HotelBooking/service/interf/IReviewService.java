package com.aplusplus.HotelBooking.service.interf;

import com.aplusplus.HotelBooking.dto.Response;
import com.aplusplus.HotelBooking.dto.ReviewDTO;
import org.springframework.data.domain.Pageable;

public interface IReviewService {
    Response createReview(ReviewDTO review);
    Response getReviewById(String reviewId);
    //Response getAllReview(); // not necessary
    Response getReviewByUserId(String userId, String roomId, Pageable pageable); // thêm roomId
    Response getReviewByRoomId(String roomId, Pageable pageable);
    Response updateReview(ReviewDTO review, String reviewId);
    Response deleteReview(String reviewId);
    Response likeReview(String reviewId);
}
