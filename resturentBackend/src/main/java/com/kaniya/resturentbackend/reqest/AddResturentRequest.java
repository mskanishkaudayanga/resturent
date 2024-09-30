package com.kaniya.resturentbackend.reqest;

import com.kaniya.resturentbackend.model.Menu;
import lombok.Data;

@Data
public class AddResturentRequest {
    private long id;
    private String ResturentName;
    private String Email;
    private String Password;
    private String phoneNumber;
}
