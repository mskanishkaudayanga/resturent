package com.kaniya.resturentbackend.reqest;

import lombok.Data;

@Data
public class UpdateResturentRequest {
    private long id;
    private String ResturentName;
    private String Email;
    private String Password;
    private String phoneNumber;
}
