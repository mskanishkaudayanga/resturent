package com.kaniya.resturentbackend.reqest;

import lombok.Data;

@Data
public class UpdateResturentRequest {
    private long id;
    private String restaurantName;
    private String email;
    private String password;
    private String phoneNumber;
    private String discription;
    private String address;
    private String city;
    private String openTime;
    private String closeTime;
}
