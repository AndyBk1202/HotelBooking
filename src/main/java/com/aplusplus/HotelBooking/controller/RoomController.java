package com.aplusplus.HotelBooking.controller;

import com.aplusplus.HotelBooking.dto.Response;
import com.aplusplus.HotelBooking.dto.RoomDTO;
import com.aplusplus.HotelBooking.service.implement.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping("/{id}")
    public ResponseEntity<Response> getRoom(@PathVariable("id") String id){
        Response response = roomService.getRoom(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }


    @GetMapping("/get-all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> getAllRoom(){
        Response response = roomService.getAllRoom();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }


    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> addRoom(
            @RequestParam(value = "roomType", required = true) String roomType,
            @RequestParam(value = "roomSize", required = true) String roomSize,
            @RequestParam(value = "roomPrice", required = true) String roomPrice,
            @RequestParam(value = "roomDescription", required = false) String roomDescription,
            @RequestParam(value = "roomStatus", required = false) String roomStatus,
            @RequestParam(value = "roomCapacity", required = true) String roomCapacity,
            @RequestParam(value = "roomAmount", required = true) String roomAmount,
            @RequestParam(value = "file", required = false) MultipartFile roomPhoto
    ){
        if(roomType == null || roomType.isBlank() ||
        roomSize == null || roomSize.isBlank() ||
        roomPrice == null || roomPrice.isBlank() ||
        roomCapacity == null || roomCapacity.isBlank() ||
        roomAmount == null || roomAmount.isBlank() ||
        roomPhoto == null || roomPhoto.isEmpty()){
            Response response = new Response();
            response.setStatusCode(400);
            response.setMessage("Please provide value for all fields(roomType, roomSize, roomPrice, roomCapacity, roomAmount, file)");
            return ResponseEntity.status(response.getStatusCode()).body(response);
        }

        Response response = roomService.addRoom(roomType, roomSize, roomPrice, roomDescription, roomStatus, roomCapacity, roomAmount, roomPhoto);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> updateRoom(
            @PathVariable("id") String roomId,
            @RequestParam(value = "roomType", required = false) String roomType,
            @RequestParam(value = "roomSize", required = false) String roomSize,
            @RequestParam(value = "roomPrice", required = false) String roomPrice,
            @RequestParam(value = "roomDescription", required = false) String roomDescription,
            @RequestParam(value = "roomStatus", required = false) String roomStatus,
            @RequestParam(value = "roomCapacity", required = false) String roomCapacity,
            @RequestParam(value = "roomAmount", required = false) String roomAmount,
            @RequestParam(value = "file", required = false) MultipartFile roomPhoto){

        Response response = roomService.updateRoom(roomId, roomType, roomSize, roomPrice, roomDescription, roomStatus, roomCapacity, roomAmount, roomPhoto);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteRoom(@PathVariable("id") String id){
        Response response = roomService.deleteRoom(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
