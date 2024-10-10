package com.aplusplus.HotelBooking.service.interf;

import com.aplusplus.HotelBooking.dto.Response;
import com.aplusplus.HotelBooking.dto.RoomDTO;

public interface IRoomService {
    Response getRoom (String roomId);
    Response getAllRoom();
    Response addRoom (RoomDTO request);
    Response updateRoom (RoomDTO request);
    Response deleteRoom (String roomId);
}
