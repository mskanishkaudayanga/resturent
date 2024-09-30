package com.kaniya.resturentbackend.reqest;

import com.kaniya.resturentbackend.model.Menu;
import lombok.Data;

@Data
public class AddResturentRequest {
    private long id;
    private String resturentName;
    private String email;
    private String password;
    private String phoneNumber;
}
