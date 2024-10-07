package com.kaniya.resturentbackend.dto;

import com.kaniya.resturentbackend.model.Menu;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

import java.util.List;

public class ResturentDto {
    private long id;
    private String restaurantName;
    private String email;
    private String password;
    private String phoneNumber;
    private String discription;
    private String city;
    private String address;
    private String OpenTime;
    private String CloseTime;
    private String openingDays;

    private List<MenuDto> menu;

}
 