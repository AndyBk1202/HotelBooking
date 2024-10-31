package com.aplusplus.HotelBooking.service.interf;

import com.aplusplus.HotelBooking.dto.BookingDTO;
import com.aplusplus.HotelBooking.dto.Response;

import java.time.LocalDate;

public interface IBookingService {
    Response createBooking(BookingDTO request);
    Response getBookingById(String bookingId);
    Response getBookingByDate(LocalDate checkInDate, LocalDate checkOutDate);
    Response getAllBooking();
    Response getBookingsByUserId(Long userId);
    Response getBookingByRoomId(String roomId);
    Response updateBooking(BookingDTO request);
    Response cancelBooking(String bookingId);
}
