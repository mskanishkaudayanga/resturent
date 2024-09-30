package com.kaniya.resturentbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Resturents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String ResturentName;
    private String Email;
    private String Password;
    private String phoneNumber;
    private String discription;
    private String city;
    private String address;
    private String OpenTime;
    private String CloseTime;
    private String openingDays;

    @OneToMany(mappedBy = "resturents" ,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Menu> menu;

    public Resturents(String resturentName, String email, String password, String phoneNumber) {
        this.ResturentName = resturentName;
        this.Email = email;
        this.Password = password;
        this.phoneNumber = phoneNumber;

    }
}
