package com.aplusplus.HotelBooking.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class Utils {
    public String getCurrentUsername() {
        // Lấy thông tin xác thực (authentication) từ SecurityContextHolder
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Kiểm tra nếu thông tin xác thực là một đối tượng UserDetails
        if (principal instanceof UserDetails) {
            // Trả về username từ đối tượng UserDetails
            return ((UserDetails) principal).getUsername();
        } else {
            // Nếu thông tin xác thực không phải là UserDetails, trả về chuỗi principal
            return principal.toString();
        }
    }
}
