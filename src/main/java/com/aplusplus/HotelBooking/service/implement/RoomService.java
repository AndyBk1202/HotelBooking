package com.aplusplus.HotelBooking.service.implement;

import com.aplusplus.HotelBooking.dto.Response;
import com.aplusplus.HotelBooking.dto.RoomDTO;
import com.aplusplus.HotelBooking.exception.OurException;
import com.aplusplus.HotelBooking.mapper.RoomMapper;
import com.aplusplus.HotelBooking.model.Room;
import com.aplusplus.HotelBooking.repository.RoomRepo;
import com.aplusplus.HotelBooking.service.interf.IRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService implements IRoomService {
    private final RoomRepo roomRepo;

    private final RoomMapper roomMapper;

    @Override
    public Response getRoom(String roomId) {
        return null;
    }

    @Override
    public Response getAllRoom() {
        return null;
    }

    @Override
    public Response addRoom(RoomDTO request) {
        Response response = new Response();
        try{
            Room room = roomMapper.toRoom(request);
            roomRepo.save(room);
            response.setStatusCode(200);
            response.setMessage("Add room successfully");
        } catch (OurException e){
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch(Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public Response updateRoom(RoomDTO request) {
        return null;
    }

    @Override
    public Response deleteRoom(String roomId) {
        return null;
    }
}
