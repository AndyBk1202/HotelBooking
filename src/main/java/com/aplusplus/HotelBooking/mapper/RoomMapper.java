package com.aplusplus.HotelBooking.mapper;

import com.aplusplus.HotelBooking.dto.RoomDTO;
import com.aplusplus.HotelBooking.dto.UserDTO;
import com.aplusplus.HotelBooking.model.Room;
import com.aplusplus.HotelBooking.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    Room toRoom(RoomDTO roomDTO);
}
