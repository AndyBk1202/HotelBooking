package com.aplusplus.HotelBooking.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Room type is required")
    private String roomType;
    @NotBlank(message = "Room size is required")
    private String roomSize;
    @NotNull(message = "Room price is required")
    private Double roomPrice;
    private String roomDescription;
    private String roomStatus;
//    private String bookingCode;
    private String roomPhotoUrl;
    @NotNull(message = "Room capacity is required")
    private int roomCapacity;
    @NotNull(message = "Room amount is required")
    private int roomAmount; // số lượng phòng có sẵn

    @ManyToMany
    @JoinTable(
            name = "room_booking",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "booking_id")
    )
    private List<Booking> bookings = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Review> review;

    @OneToOne
    @JoinColumn(name = "facility_id", referencedColumnName = "id")
    private Facility facility;
}
