package com.aplusplus.HotelBooking.repository;

import com.aplusplus.HotelBooking.model.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface RoomRepo extends JpaRepository<Room, Long> {

    @Query(value = "SELECT * FROM rooms r WHERE r.room_capacity >= :numOfGuest AND r.room_amount > (SELECT COUNT(*) FROM bookings b WHERE b.room_id = r.id AND :checkInDate = b.check_in_date OR :checkInDate < b.check_in_date AND :checkOutDate > b.check_in_date OR :checkInDate > b.check_in_date AND :checkInDate < b.check_out_date)", nativeQuery = true)
    //@Query(value = "SELECT * FROM rooms r WHERE r.room_capacity >= :numOfGuest AND r.room_amount > (SELECT COUNT(*) FROM bookings b WHERE b.room_id = r.id AND :checkInDate = b.check_in_date OR :checkInDate < b.check_in_date AND :checkOutDate > b.check_in_date OR :checkInDate > b.check_in_date AND :checkInDate < b.check_out_date) OR r.room_capacity >= :numOfGuest AND NOT EXISTS (SELECT * FROM bookings b1 WHERE b1.room_id = r.id AND :checkInDate = b1.check_in_date OR :checkInDate < b1.check_in_date AND :checkOutDate > b1.check_in_date OR :checkInDate > b1.check_in_date AND :checkInDate < b1.check_out_date)", nativeQuery = true)
    Page<Room> findByDateAndNumOfGuest(LocalDate checkInDate, LocalDate checkOutDate, int numOfGuest, Pageable pageable);

    List<Room> findByRoomType(String roomType);
}
