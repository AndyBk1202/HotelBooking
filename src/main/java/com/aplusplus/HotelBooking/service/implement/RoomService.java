package com.aplusplus.HotelBooking.service.implement;

import com.aplusplus.HotelBooking.dto.FacilityDTO;
import com.aplusplus.HotelBooking.dto.Response;
import com.aplusplus.HotelBooking.dto.RoomDTO;
import com.aplusplus.HotelBooking.exception.OurException;
import com.aplusplus.HotelBooking.mapper.FacilityMapper;
import com.aplusplus.HotelBooking.mapper.RoomMapper;
import com.aplusplus.HotelBooking.model.Facility;
import com.aplusplus.HotelBooking.model.Room;
import com.aplusplus.HotelBooking.repository.FacilityRepo;
import com.aplusplus.HotelBooking.repository.RoomRepo;
import com.aplusplus.HotelBooking.service.FirebaseStorageService;
import com.aplusplus.HotelBooking.service.interf.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Service
public class RoomService implements IRoomService {

    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private RoomRepo roomRepo;
    @Autowired
    private FacilityMapper facilityMapper;
    @Autowired
    private FacilityRepo facilityRepo;
    @Autowired
    private FirebaseStorageService firebaseStorageService;
    @Override
    public Response getRoom(String roomId) {
        Response response = new Response();
        try{
            Room room = roomRepo.findById(Long.valueOf(roomId)).orElseThrow(() -> new OurException("Room not found"));
            RoomDTO roomDTO = roomMapper.roomToRoomDTO(room);
            response.setRoom(roomDTO);
            response.setStatusCode(200);
            response.setMessage("Get room with id: " + roomId + " successfully");
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public Response getAllRoom(Pageable pageable) {
        Response response = new Response();
        try{
//            List<Room> roomList = roomRepo.findAll();
//            List<RoomDTO> roomDTOList = roomMapper.roomListToRoomDTOList(roomList);
            Page<RoomDTO> roomDTOPage = roomRepo.findAll(pageable).map(roomMapper::roomToRoomDTO);
            response.setRoomList(roomDTOPage.getContent());
            response.setCurrentPage(roomDTOPage.getNumber());
            response.setTotalPages(roomDTOPage.getTotalPages());
            response.setTotalElements(roomDTOPage.getTotalElements());
            response.setStatusCode(200);
            response.setMessage("Get all rooms successfully");
        } catch (OurException e){
            response.setStatusCode(400);
            response.setMessage(e.getMessage());
        } catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public Response addRoom(
            String roomType,
            String roomSize,
            String roomPrice,
            String roomDescription,
            String roomStatus,
            String roomCapacity,
            String roomAmount,
            MultipartFile roomPhoto,
            FacilityDTO facilityDTO) {
        Response response = new Response();
        try{
            Room newRoom = new Room();
            newRoom.setRoomType(roomType);
            newRoom.setRoomSize(roomSize);
            newRoom.setRoomPrice(Double.valueOf(roomPrice));
            if(roomDescription != null) newRoom.setRoomDescription(roomDescription);
            if(roomStatus != null) newRoom.setRoomStatus(roomStatus);
            newRoom.setRoomCapacity(Integer.parseInt(roomCapacity));
            newRoom.setRoomAmount(Integer.parseInt(roomAmount));

            if(roomPhoto != null){
                String fileUrl = firebaseStorageService.uploadFile(roomPhoto);
                newRoom.setRoomPhotoUrl(fileUrl);
            }
            Facility facility = facilityMapper.facilityDTOToFacility(facilityDTO);
            facilityRepo.save(facility);
            newRoom.setFacility(facility);
            roomRepo.save(newRoom);
            response.setStatusCode(200);
            response.setMessage("Add new room successfully");
        } catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public Response updateRoom(
            String roomId,
            String roomType,
            String roomSize,
            String roomPrice,
            String roomDescription,
            String roomStatus,
            String roomCapacity,
            String roomAmount,
            MultipartFile roomPhoto,
            FacilityDTO facilityDTO) {
        Response response = new Response();
        try{
            Room room = roomRepo.findById(Long.valueOf(roomId)).orElseThrow(() -> new OurException("Room not found"));


            if(roomType != null && !roomType.isBlank()) room.setRoomType(roomType);
            if(roomSize != null && !roomSize.isBlank()) room.setRoomSize(roomSize);
            if(roomPrice != null && !roomPrice.isBlank()) room.setRoomPrice(Double.valueOf(roomPrice));
            if(roomDescription != null && !roomDescription.isBlank()) room.setRoomDescription(roomDescription);
            if(roomStatus != null && !roomStatus.isBlank()) room.setRoomStatus(roomStatus);
            if(roomCapacity != null && !roomCapacity.isBlank()) room.setRoomCapacity(Integer.parseInt(roomCapacity));
            if(roomAmount != null && !roomAmount.isBlank()) room.setRoomAmount(Integer.parseInt(roomAmount));

            if(roomPhoto != null){
                String fileUrl = firebaseStorageService.uploadFile(roomPhoto);
                room.setRoomPhotoUrl(fileUrl);
            }

            Facility facility = facilityRepo.findByRoom(room);
            facility.setGymInfo(facilityDTO.getGymInfo());
            facility.setBathInfo(facilityDTO.getBathInfo());
            facility.setDrinkInfo(facilityDTO.getDrinkInfo());
            facility.setCoffeeInfo(facilityDTO.getCoffeeInfo());
            facility.setBreakfastInfo(facilityDTO.getBreakfastInfo());
            facility.setParkingInfo(facilityDTO.getParkingInfo());
            facility.setWifiInfo(facilityDTO.getWifiInfo());
            facility.setPoolInfo(facilityDTO.getPoolInfo());
            facilityRepo.save(facility);
            room.setFacility(facility);
            roomRepo.save(room);
            response.setStatusCode(200);
            response.setMessage("Update room successfully");
        } catch (OurException e){
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public Response deleteRoom(String roomId) {
        Response response = new Response();
        try{
            Room room = roomRepo.findById(Long.valueOf(roomId)).orElseThrow(() -> new OurException("Room not found"));
            roomRepo.deleteById(Long.valueOf(roomId));
            response.setStatusCode(200);
            response.setMessage("Delete room successfully");
        } catch (OurException e){
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public Response getAvailableRoomsByDateAndType(LocalDate checkInDate, LocalDate checkoutDate, String roomType) {
        return null;
    }

    @Override
    public Response getAllAvailableRooms() {
        return null;
    }
}
