package com.aplusplus.HotelBooking.service.implement;

import com.aplusplus.HotelBooking.dto.BookingDTO;
import com.aplusplus.HotelBooking.dto.Response;
import com.aplusplus.HotelBooking.exception.OurException;
import com.aplusplus.HotelBooking.mapper.BookingMapper;
import com.aplusplus.HotelBooking.model.User;
import com.aplusplus.HotelBooking.repository.BookingRepo;
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
    public Response getBookingByUserId(String userId) {
        return null;
    }

    @Override
    public Response getBookingByRoomId(String roomId) {
        return null;
    }

    @Override
    public Response updateBooking(BookingDTO request) {
        return null;
    }

    @Override
    public Response cancelBooking(String bookingId) {
        return null;
    }
}
