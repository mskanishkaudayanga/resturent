package com.kaniya.resturentbackend.reqest;

import lombok.Data;

@Data
public class AddResturentRequest {
    private long id;
    private String restaurantName;
    private String email;
    private String password;
    private String city;
}
