package com.aplusplus.HotelBooking.controller;

import com.aplusplus.HotelBooking.dto.Response;
import com.aplusplus.HotelBooking.service.interf.IUserService;
import com.aplusplus.HotelBooking.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private Utils utils;

    @GetMapping("/get_by_id/{userId}")
    public ResponseEntity<Response> getUserById(@PathVariable("userId") String userId){
        Response response = userService.getUserById(userId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/get_info")
    public ResponseEntity<Response> getUserInfo(){
        Response response = userService.getMyInfo(utils.getCurrentUsername());
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/get_all_customers")
    public ResponseEntity<Response> getAllCustomers(){
        Response response = userService.getAllCustomers();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
