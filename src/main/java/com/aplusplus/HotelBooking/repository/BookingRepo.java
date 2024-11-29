package com.aplusplus.HotelBooking.repository;

import com.aplusplus.HotelBooking.model.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookingRepo extends JpaRepository<Booking, Long> {
    Optional<Booking> findByCheckInDateAndCheckOutDate(LocalDate checkInDate, LocalDate checkOutDate);
    Page<Booking> findAll(Pageable pageable);

    @Query("SELECT b FROM Booking b WHERE b.checkInDate >= :startDate AND b.checkInDate <= :endDate")
    Page<Booking> getBookingByDate(LocalDate startDate, LocalDate endDate, Pageable pageable);

    @Query("SELECT b FROM Booking b WHERE b.room.roomType = :roomType")
    Page<Booking> getBookingsByRoomType(String roomType, Pageable pageable);

    @Query("SELECT b FROM Booking b WHERE b.room.roomType = :roomType AND b.checkInDate >= :startDate AND b.checkInDate <= :endDate")
    Page<Booking> getBookingsByDateAndRoomType(String roomType, LocalDate startDate, LocalDate endDate, Pageable pageable);

    @Query(value = "SELECT * FROM bookings b WHERE b.user_id=:userId AND b.check_out_date >= :now", nativeQuery = true)
    Page<Booking> getRecentBookings(Long userId, LocalDate now, Pageable pageable);

    @Query(value = "SELECT * FROM bookings b WHERE b.room_id=:roomId AND b.check_out_date >= :now", nativeQuery = true)
    Page<Booking> getBookingsByRoom(Long roomId, LocalDate now, Pageable pageable);

    @Query(value = "SELECT * FROM bookings b WHERE b.user_id=:userId", nativeQuery = true)
    Page<Booking> getBookingsByUser(Long userId, Pageable pageable);

    @Query(value = "SELECT * FROM " +
            "bookings b JOIN payments p ON b.id = p.booking_id " +
            "WHERE b.user_id = :userId AND payment_status = 'PAID'", nativeQuery = true)
    Page<Booking> getBookingsHistory(Long userId, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM " +
            "payments p JOIN bookings b ON p.booking_id = b.id " +
            "WHERE YEAR(check_in_date) = :year AND MONTH(check_in_date) = :month AND payment_status = 'PAID'", nativeQuery = true)
    Long getTotalBookingsByYearAndMonth(Integer year, Integer month);

    @Query(value = "SELECT b.* FROM " +
            "bookings b LEFT JOIN payments p ON b.id = p.booking_id " +
            "WHERE DATEDIFF(b.check_in_date, :now) <= 1 AND (payment_status != 'PAID' OR payment_status IS NULL)", nativeQuery = true)
    Page<Booking> getBookingsOutOfDue(Pageable pageable, LocalDate now);
}
