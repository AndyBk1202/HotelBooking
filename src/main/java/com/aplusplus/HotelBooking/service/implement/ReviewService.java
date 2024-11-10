package com.aplusplus.HotelBooking.service.implement;


import com.aplusplus.HotelBooking.dto.Response;
import com.aplusplus.HotelBooking.dto.ReviewDTO;
import com.aplusplus.HotelBooking.exception.OurException;
import com.aplusplus.HotelBooking.mapper.ReviewMapper;
import com.aplusplus.HotelBooking.repository.ReviewRepo;
import com.aplusplus.HotelBooking.service.interf.IReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService implements IReviewService {
    private final ReviewRepo reviewRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public Response createReview(ReviewDTO reviewRequest) {
        Response response = new Response();
        try{
            var review = reviewMapper.toReview(reviewRequest);
            review.setCreatedTime(LocalDateTime.now());
            reviewRepository.save(review);
            response.setStatusCode(200);
            response.setMessage("Create review successfully");
        } catch (OurException e){
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public Response getReviewById(String reviewId) {
        Response response = new Response();
        try{
            var review = reviewRepository.findById(Long.parseLong(reviewId)).orElseThrow(() -> new OurException("Review not found"));

            response.setReview(reviewMapper.toReviewDTO(review));
            response.setStatusCode(200);
            response.setMessage("Create review successfully");
        } catch (OurException e){
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public Response getReviewByUserId(String userId, String roomId, Pageable pageable) {
        Response response = new Response();
        try{
            Page<ReviewDTO> reviewDTOPage = reviewRepository.findAllByUserIdAndRoomId(Long.parseLong(userId), Long.parseLong(roomId), pageable).map(reviewMapper::toReviewDTO);
            List<ReviewDTO> reviewDTOList = reviewDTOPage.getContent();

            response.setReviewList(reviewDTOList);
            response.setCurrentPage(reviewDTOPage.getNumber());
            response.setTotalElements(reviewDTOPage.getTotalElements());
            response.setTotalPages(reviewDTOPage.getTotalPages());

            response.setStatusCode(200);
            response.setMessage("Get reviews successfully");
        } catch (OurException e){
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public Response getReviewByRoomId(String roomId, Pageable pageable) {
        Response response = new Response();
        try{
            Page<ReviewDTO> reviewDTOPage = reviewRepository.findAllByRoomId(Long.parseLong(roomId), pageable).map(reviewMapper::toReviewDTO);
            List<ReviewDTO> reviewDTOList = reviewDTOPage.getContent();

            response.setReviewList(reviewDTOList);
            response.setCurrentPage(reviewDTOPage.getNumber());
            response.setTotalElements(reviewDTOPage.getTotalElements());
            response.setTotalPages(reviewDTOPage.getTotalPages());

            response.setStatusCode(200);
            response.setMessage("Get reviews successfully");
        } catch (OurException e){
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public Response updateReview(ReviewDTO reviewRequest, String reviewId) {
        Response response = new Response();
        try{
            var review = reviewRepository.findById(Long.parseLong(reviewId)).orElseThrow(() -> new OurException("Review not found"));
            review.setReviewRate(reviewRequest.getReviewRate());
            review.setComment(reviewRequest.getComment());
            review.setCreatedTime(LocalDateTime.now());
            reviewRepository.save(review);

            response.setStatusCode(200);
            response.setMessage("Update review successfully");
        } catch (OurException e){
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public Response deleteReview(String reviewId) {
        Response response = new Response();
        try{
            reviewRepository.deleteById(Long.parseLong(reviewId));
            response.setStatusCode(200);
            response.setMessage("Delete review successfully");
        } catch (OurException e){
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }
}
