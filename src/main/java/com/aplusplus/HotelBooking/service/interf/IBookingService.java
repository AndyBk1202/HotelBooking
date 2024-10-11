package com.aplusplus.HotelBooking.service.interf;

import com.aplusplus.HotelBooking.dto.BookingDTO;
import com.aplusplus.HotelBooking.dto.Response;

public interface IBookingService {
    Response createBooking(BookingDTO request);
    Response getBookingById(String bookingId);
    Response getAllBooking();
    Response getBookingByUserId(String userId);
    Response getBookingByRoomId(String roomId);
    Response updateBooking(BookingDTO request);
    Response cancelBooking(String bookingId);
}
