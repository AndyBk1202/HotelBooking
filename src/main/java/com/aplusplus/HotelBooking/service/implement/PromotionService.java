package com.aplusplus.HotelBooking.service.implement;

import com.aplusplus.HotelBooking.dto.PromotionDTO;
import com.aplusplus.HotelBooking.dto.Response;
import com.aplusplus.HotelBooking.mapper.PromotionMapper;
import com.aplusplus.HotelBooking.model.Promotion;
import com.aplusplus.HotelBooking.model.Room;
import com.aplusplus.HotelBooking.repository.PromotionRepo;
import com.aplusplus.HotelBooking.repository.RoomRepo;
import com.aplusplus.HotelBooking.service.interf.IPromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PromotionService implements IPromotionService {
    private final PromotionRepo promotionRepository;
    private final PromotionMapper promotionMapper;
    private final RoomRepo roomRepository;

    @Override
    public Response createPromotionForRoomType(PromotionDTO promotionDTO, String[] listRoomType) {
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
    public Response updatePromotion(PromotionDTO newPromotion) {
        Response response = new Response();
        try {
            Promotion promotionUpdate = promotionRepository.findById(newPromotion.getId()).orElseThrow(() -> new Exception("Promotion not found"));
            if (newPromotion.getPercentOfDiscount() != null) {
                promotionUpdate.setPercentOfDiscount(newPromotion.getPercentOfDiscount());
            }
            if (newPromotion.getDescription() != null && !newPromotion.getDescription().isEmpty()) {
                promotionUpdate.setDescription(newPromotion.getDescription());
            }
            if (newPromotion.getStartDate() != null && !newPromotion.getStartDate().toString().isEmpty()) {
                promotionUpdate.setStartDate(newPromotion.getStartDate());
            }
            if (newPromotion.getEndDate() != null && !newPromotion.getEndDate().toString().isEmpty()) {
                promotionUpdate.setEndDate(newPromotion.getEndDate());
            }

            if (newPromotion.getListRoomTypes() != null) {
                List<Room> roomListPromotion = new ArrayList<>();
                for (int i = 0; i < newPromotion.getListRoomTypes().length; i++) {
                    List<Room> rooms = roomRepository.findByRoomType(newPromotion.getListRoomTypes()[i]);
                    for (Room room : rooms) {
                        /// in the MySQL setting, room_id and promotion_id aren't primary key
                        /// database still save the same record, so we need to check if the room already have the promotion
                        if (!room.getPromotions().contains(promotionUpdate))
                        {
                            room.getPromotions().add(promotionUpdate);
                        }
                    }
                    roomListPromotion.addAll(rooms);
                }
                promotionUpdate.setRooms(roomListPromotion);
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
        return null;
    }

    @Override
    public Response applyPromotionToRoom(String promotionId, String roomId) {
        return null;
    }

    @Override
    public Response getPromotionByRoomId(String roomId) {
        return null;
    }
}
