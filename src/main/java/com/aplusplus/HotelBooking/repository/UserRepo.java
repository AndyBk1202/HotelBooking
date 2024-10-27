package com.aplusplus.HotelBooking.repository;

import com.aplusplus.HotelBooking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    void deleteByEmail(String email);
    @Query("SELECT u FROM User u WHERE u.role = 'USER'")
    List<User> getAllCustomers();
}
