package com.aplusplus.HotelBooking.service.interf;

import com.aplusplus.HotelBooking.dto.FacilityDTO;
import com.aplusplus.HotelBooking.dto.Response;
import com.aplusplus.HotelBooking.dto.RoomDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public interface IRoomService {
    Response getRoom (String roomId);
    Response getAllRoom(Pageable pageable);
    Response addRoom (
            String roomType,
            String roomSize,
            String roomPrice,
            String roomDescription,
            String roomStatus,
            String roomCapacity,
            String roomAmount,
            MultipartFile roomPhoto,
            FacilityDTO facilityDTO);
    Response updateRoom (
            String roomId,
            String roomType,
            String roomSize,
            String roomPrice,
            String roomDescription,
            String roomStatus,
            String roomCapacity,
            String roomAmount,
            MultipartFile roomPhoto,
            FacilityDTO facilityDTO);
    Response deleteRoom (String roomId);

    Response getAvailableRoomsByDateAndType(LocalDate checkInDate, LocalDate checkoutDate, String roomType);

    Response getAllAvailableRooms();
}
