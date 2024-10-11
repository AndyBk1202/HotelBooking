package com.aplusplus.HotelBooking.service.interf;

import com.aplusplus.HotelBooking.dto.LoginRequest;
import com.aplusplus.HotelBooking.dto.Response;
import com.aplusplus.HotelBooking.model.User;

public interface IUserService {
    Response register (User user);
    Response login(LoginRequest loginRequest);
    Response getAllCustomers();
    Response getUserBookingHistory(String userId);
    Response deleteUser(String username);
    Response getUserById(String userId);
    Response getMyInfo(String username);
}
