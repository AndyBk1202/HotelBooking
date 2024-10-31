package com.aplusplus.HotelBooking.service.implement;

import com.aplusplus.HotelBooking.dto.BookingDTO;
import com.aplusplus.HotelBooking.dto.Response;
import com.aplusplus.HotelBooking.exception.OurException;
import com.aplusplus.HotelBooking.mapper.BookingMapper;
import com.aplusplus.HotelBooking.model.Booking;
import com.aplusplus.HotelBooking.model.Room;
import com.aplusplus.HotelBooking.model.User;
import com.aplusplus.HotelBooking.repository.BookingRepo;
import com.aplusplus.HotelBooking.repository.RoomRepo;
import com.aplusplus.HotelBooking.repository.UserRepo;
import com.aplusplus.HotelBooking.service.interf.IBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService implements IBookingService {
    private final BookingRepo bookingRepository;
    private final BookingMapper bookingMapper;
    private final UserRepo userRepo;
    private final RoomRepo roomRepo;

    @Override
    public Response createBooking(BookingDTO request) {
        Response response = new Response();
        try{
            var booking = bookingMapper.toBooking(request);
            bookingRepository.save(booking);

            response.setStatusCode(200);
            response.setMessage("Create booking successfully");
        } catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public Response getBookingById(String bookingId) {
        Response response = new Response();
        try{
            var booking = bookingRepository.findById(Long.parseLong(bookingId)).orElseThrow(() -> new OurException("Booking not found"));
            BookingDTO bookingDTO = bookingMapper.toBookingDTO(booking);

            response.setBooking(bookingDTO);
            response.setStatusCode(200);
            response.setMessage("Find booking information successfully");
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
    public Response getBookingByDate(LocalDate checkInDate, LocalDate checkOutDate) {
        Response response = new Response();
        try{
            var booking = bookingRepository.findByCheckInDateAndCheckoutDate(checkInDate, checkOutDate).orElseThrow(() -> new OurException("Booking not found"));
            BookingDTO bookingDTO = bookingMapper.toBookingDTO(booking);

            response.setBooking(bookingDTO);
            response.setStatusCode(200);
            response.setMessage("Find booking information successfully");
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
    public Response getAllBooking() {
        Response response = new Response();
        try{
            List<BookingDTO> bookingDTOList = bookingRepository.findAll().stream().map(bookingMapper::toBookingDTO).toList();

            response.setBookingList(bookingDTOList);
            response.setStatusCode(200);
            response.setMessage("Find all booking information successfully");
        } catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public Response getBookingsByUserId(Long userId) {
        Response response = new Response();
        try{

            // TODO code to get booking by user id
            User user = userRepo.findById(userId).orElseThrow(() -> new OurException("User not found"));
            List<BookingDTO> bookingDTOList = user.getBookings().stream().map(bookingMapper::toBookingDTO).toList();

            System.out.println("print something here");

            for (BookingDTO bookingDTO : bookingDTOList) {
                System.out.println("booking DTO: ");
                System.out.println(bookingDTO);
            }

            response.setBookingList(bookingDTOList);
            response.setStatusCode(200);
            response.setMessage("Find booking information successfully");
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
    public Response getBookingByRoomId(String roomId) {
        Response response = new Response();

        try{

            /*
            TODO code to get booking by room id
            This code still doesn't work because createBooking doesn't add room to booking
            */

            // This code will return no rooms because createBooking doesn't add room to booking
            Room room = roomRepo.findById(Long.parseLong(roomId)).orElseThrow(() -> new OurException("Room not found"));


            Booking booking = room.getBookings().stream().findFirst().orElseThrow(() -> new OurException("Booking not found"));
            BookingDTO bookingDTO = bookingMapper.toBookingDTO(booking);

            response.setBooking(bookingDTO);
            response.setStatusCode(200);
            response.setMessage("Find booking information successfully");
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
    public Response updateBooking(BookingDTO request) {
        return null;
    }

    @Override
    public Response cancelBooking(String bookingId) {
        // TODO code to cancel booking (delete booking)
        Response response = new Response();
        try{
            var booking = bookingRepository.findById(Long.parseLong(bookingId)).orElseThrow(() -> new OurException("Booking not found"));
            bookingRepository.delete(booking);

            response.setStatusCode(200);
            response.setMessage("Cancel booking successfully");
        } catch (OurException e){
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }

        return response;
    }
}
