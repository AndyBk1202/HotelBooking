package com.aplusplus.HotelBooking.service.implement;

import com.aplusplus.HotelBooking.dto.PromotionDTO;
import com.aplusplus.HotelBooking.dto.Response;
import com.aplusplus.HotelBooking.mapper.PromotionMapper;
import com.aplusplus.HotelBooking.model.Promotion;
import com.aplusplus.HotelBooking.model.Room;
import com.aplusplus.HotelBooking.repository.PromotionRepo;
import com.aplusplus.HotelBooking.repository.RoomRepo;
import com.aplusplus.HotelBooking.service.FirebaseStorageService;
import com.aplusplus.HotelBooking.service.interf.IPromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;


@Service
@RequiredArgsConstructor
public class PromotionService implements IPromotionService {
    private final PromotionRepo promotionRepository;
    private final PromotionMapper promotionMapper;
    private final RoomRepo roomRepository;
    private final FirebaseStorageService firebaseStorageService;

    @Override
    public Response createPromotionForRoomType(PromotionDTO promotionDTO, String[] listRoomType, MultipartFile imageFile) {
        Response response = new Response();
        try {
            Promotion promotion = promotionMapper.toPromotion(promotionDTO);
            List<Room> roomListPromotion = new ArrayList<>();

            for (int i = 0; i < listRoomType.length; i++) {
                List<Room> rooms = roomRepository.findByRoomType(listRoomType[i]);
                for (Room room : rooms) {
                    room.getPromotions().add(promotion);
                }
                roomListPromotion.addAll(rooms);
            }
            promotion.setRooms(roomListPromotion);

            // save image to firebase
            if (imageFile != null) {
                String imageUrl = firebaseStorageService.uploadFile(imageFile);
                promotion.setPromotionPhotoUrl(imageUrl);
            }

            promotionRepository.save(promotion);


            response.setStatusCode(200);
            response.setMessage("Create promotion successfully");
        }
        catch (Exception e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @Override
    public Response getPromotionById(String promotionId) {
        Response response = new Response();
        try {
            Promotion promotion = promotionRepository.findById(Long.parseLong(promotionId)).orElseThrow(() -> new Exception("Promotion not found"));
            PromotionDTO promotionDTO = promotionMapper.toPromotionDTO(promotion);

            List<Room> rooms = promotion.getRooms();
            List<String> listRoomType = new ArrayList<>();

            for (Room room : rooms) {
                if (!listRoomType.contains(room.getRoomType())) {
                    listRoomType.add(room.getRoomType());
                }
            }
            listRoomType.sort(String::compareTo);
            promotionDTO.setListRoomTypes(listRoomType.toArray(new String[0]));

            response.setStatusCode(200);
            response.setPromotion(promotionDTO);
        }
        catch (Exception e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public Response getAllPromotion(Pageable pageable) {
        Response response = new Response();
        try {
            List<Promotion> promotions = promotionRepository.findAll(pageable).getContent();
            List<PromotionDTO> promotionDTOS = new ArrayList<>();

            for (Promotion promotion : promotions) {
                PromotionDTO promotionDTO = promotionMapper.toPromotionDTO(promotion);
                List<Room> rooms = promotion.getRooms();
                List<String> listRoomType = new ArrayList<>();

                for (Room room : rooms) {
                    if (!listRoomType.contains(room.getRoomType())) {
                        listRoomType.add(room.getRoomType());
                    }
                }
                listRoomType.sort(String::compareTo);
                promotionDTO.setListRoomTypes(listRoomType.toArray(new String[0]));

                promotionDTOS.add(promotionDTO);
            }

            response.setStatusCode(200);
            response.setPromotionList(promotionDTOS);
        }
        catch (Exception e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public Response updatePromotion(PromotionDTO newPromotion, String promotionId, MultipartFile imageFile) {
        Response response = new Response();
        try {
            Promotion promotionUpdate = promotionRepository.findById(Long.parseLong(promotionId)).orElseThrow(() -> new Exception("Promotion not found"));
            if (newPromotion.getPercentOfDiscount() != null) {
                promotionUpdate.setPercentOfDiscount(newPromotion.getPercentOfDiscount());
            }
            if (newPromotion.getDescription() != null && !newPromotion.getDescription().isEmpty()) {
                promotionUpdate.setDescription(newPromotion.getDescription());
            }
            if (newPromotion.getPromotionTitle() != null && !newPromotion.getPromotionTitle().isEmpty()) {
                promotionUpdate.setPromotionTitle(newPromotion.getPromotionTitle());
            }
            if (newPromotion.getStartDate() != null && !newPromotion.getStartDate().toString().isEmpty()) {
                promotionUpdate.setStartDate(newPromotion.getStartDate());
            }
            if (newPromotion.getEndDate() != null && !newPromotion.getEndDate().toString().isEmpty()) {
                promotionUpdate.setEndDate(newPromotion.getEndDate());
            }

            if (newPromotion.getListRoomTypes() != null) {


                // for removing room type from promotion
                List<String> listOfNewRoomType = List.of(newPromotion.getListRoomTypes());
                boolean[] isRoomTypeExist = new boolean[listOfNewRoomType.size()];
                Arrays.fill(isRoomTypeExist, false);
                // false when room type is not exist in new promotion

                for (Room room : promotionUpdate.getRooms()) {
                    if (!listOfNewRoomType.contains(room.getRoomType())) {
                        room.getPromotions().remove(promotionUpdate);
                    } else {
                        isRoomTypeExist[listOfNewRoomType.indexOf(room.getRoomType())] = true;
                    }
                }

                // for adding new room type to promotion
                List<Room> roomListPromotions = new ArrayList<>();
                for (int i = 0; i < newPromotion.getListRoomTypes().length; i++) {
                    if (!isRoomTypeExist[i]) {
                        List<Room> rooms = roomRepository.findByRoomType(newPromotion.getListRoomTypes()[i]);
                        for (Room room : rooms) {
                            // check duplicate room
                            if (!roomListPromotions.contains(room)) {
                                room.getPromotions().add(promotionUpdate);
                            }
                        }
                        roomListPromotions.addAll(rooms);
                    }
                }
                promotionUpdate.setRooms(roomListPromotions);

                // save image to firebase
                if (imageFile != null) {
                    String imageUrl = firebaseStorageService.uploadFile(imageFile);
                    promotionUpdate.setPromotionPhotoUrl(imageUrl);
                }

                promotionRepository.save(promotionUpdate);
            }

            response.setStatusCode(200);
            response.setMessage("Update newPromotion successfully");
        }
        catch (Exception e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public Response deletePromotion(String promotionId) {
        Response response = new Response();
        try {
            Promotion promotion = promotionRepository.findById(Long.parseLong(promotionId)).orElseThrow(() -> new Exception("Promotion not found"));
            List<Room> rooms = promotion.getRooms();
            for (Room room : rooms) {
                room.getPromotions().remove(promotion);
            }
            promotionRepository.delete(promotion);

            response.setStatusCode(200);
            response.setMessage("Delete promotion successfully");
        }
        catch (Exception e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public Response applyPromotionToRoom(String promotionId, String roomId) {
        return null;
    }

    @Override
    public Response getPromotionByRoomId(String roomId) {
        Response response = new Response();
        try {
            Room room = roomRepository.findById(Long.parseLong(roomId)).orElseThrow(() -> new Exception("Room not found"));
            List<Promotion> promotions = room.getPromotions();
            List<PromotionDTO> promotionDTOS = new ArrayList<>();

            for (Promotion promotion : promotions) {
                PromotionDTO promotionDTO = promotionMapper.toPromotionDTO(promotion);
                promotionDTOS.add(promotionDTO);
            }

            response.setStatusCode(200);
            response.setPromotionList(promotionDTOS);
        }
        catch (Exception e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public Response getLatestPromotion(Pageable pageable) {
        Response response = new Response();
        try {
            List<Promotion> promotions = promotionRepository.findTop3ByCurrentDateBetweenStartDateAndEndDateOrderByStartDateDesc(pageable);
            List<PromotionDTO> promotionDTOS = new ArrayList<>();

            for (Promotion promotion : promotions) {
                PromotionDTO promotionDTO = promotionMapper.toPromotionDTO(promotion);
                List<Room> rooms = promotion.getRooms();
                List<String> listRoomType = new ArrayList<>();

                for (Room room : rooms) {
                    if (!listRoomType.contains(room.getRoomType())) {
                        listRoomType.add(room.getRoomType());
                    }
                }
                listRoomType.sort(String::compareTo);
                promotionDTO.setListRoomTypes(listRoomType.toArray(new String[0]));

                promotionDTOS.add(promotionDTO);
            }

            response.setStatusCode(200);
            response.setPromotionList(promotionDTOS);
        }
        catch (Exception e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }
        return response;
    }
}
