package com.aplusplus.HotelBooking.service.interf;

import com.aplusplus.HotelBooking.dto.Response;
import com.aplusplus.HotelBooking.dto.ReviewDTO;

public interface IReviewService {
    Response createReview(ReviewDTO review);
    Response getReviewById(String reviewId);
    Response getAllReview();
    Response getReviewByUserId(String userId);
    Response getReviewByRoomId(String roomId);
    Response updateReview(ReviewDTO review);
    Response deleteReview(String reviewId);
}
