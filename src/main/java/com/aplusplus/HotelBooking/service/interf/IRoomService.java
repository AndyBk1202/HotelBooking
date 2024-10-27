package com.aplusplus.HotelBooking.service.interf;

import com.aplusplus.HotelBooking.dto.Response;
import com.aplusplus.HotelBooking.dto.RoomDTO;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public interface IRoomService {
    Response getRoom (String roomId);
    Response getAllRoom();
    Response addRoom (
            String roomType,
            String roomSize,
            String roomPrice,
            String roomDescription,
            String roomStatus,
            String roomCapacity,
            String roomAmount,
            MultipartFile roomPhoto);
    Response updateRoom (
            String roomId,
            String roomType,
            String roomSize,
            String roomPrice,
            String roomDescription,
            String roomStatus,
            String roomCapacity,
            String roomAmount,
            MultipartFile roomPhoto);
    Response deleteRoom (String roomId);

    Response getAvailableRoomsByDateAndType(LocalDate checkInDate, LocalDate checkoutDate, String roomType);

    Response getAllAvailableRooms();
}
