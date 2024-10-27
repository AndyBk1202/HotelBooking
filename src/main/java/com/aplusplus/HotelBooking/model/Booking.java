package com.aplusplus.HotelBooking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Check in date is required")
    private LocalDate checkInDate;
    @NotNull(message = "Check out date is required")
    private LocalDate checkoutDate;
    @NotNull(message = "Number of children is required")
    private int numOfChildren;
    @NotNull(message = "Number of adult is required")
    private int numOfAdults;
    @NotNull(message = "Number of guests if required")
    private int totalNumOfGuest;
    @NotBlank(message = "Number of booking code is required")
    private String bookingCode;

    @ManyToMany(mappedBy = "bookings", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Room> rooms = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "booking")
    private Payment payment;
}
