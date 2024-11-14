package com.aplusplus.HotelBooking.repository;

import com.aplusplus.HotelBooking.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ReviewRepo extends JpaRepository<Review, Long> {
    Page<Review> findAllByUserIdAndRoomId(Long userId, Long roomId, Pageable pageable);

    Page<Review> findAllByRoomId(Long roomId, Pageable pageable);
}
