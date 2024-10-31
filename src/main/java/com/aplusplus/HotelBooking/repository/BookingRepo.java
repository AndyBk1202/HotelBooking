package com.aplusplus.HotelBooking.repository;

import com.aplusplus.HotelBooking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookingRepo extends JpaRepository<Booking, Long> {
    Optional<Booking> findByCheckInDateAndCheckoutDate(LocalDate checkInDate, LocalDate checkOutDate);
}
