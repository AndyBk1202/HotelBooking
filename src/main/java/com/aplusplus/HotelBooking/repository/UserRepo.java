package com.aplusplus.HotelBooking.repository;

import com.aplusplus.HotelBooking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);

    void deleteByUsername(String username);
    @Query("SELECT u FROM User u WHERE u.role = 'USER'")
    List<User> getAllCustomers();
}