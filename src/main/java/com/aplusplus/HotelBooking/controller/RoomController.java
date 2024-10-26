package com.aplusplus.HotelBooking.controller;

import com.aplusplus.HotelBooking.dto.Response;
import com.aplusplus.HotelBooking.dto.RoomDTO;
import com.aplusplus.HotelBooking.mapper.RoomMapper;
import com.aplusplus.HotelBooking.service.interf.IRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomController {
    private final IRoomService roomService;

    @PostMapping
    public ResponseEntity<Response> addRoom(@RequestBody RoomDTO request){
        Response response = roomService.addRoom(request);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
