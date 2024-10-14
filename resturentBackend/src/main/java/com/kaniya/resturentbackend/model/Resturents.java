package com.kaniya.resturentbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @OneToMany(mappedBy = "resturents" ,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Menu> menu;

    public Resturents(String restaurantName, String email, String password, String city) {
        this.restaurantName = restaurantName;
        this.email = email;
        this.password = password;
        this.city = city;

    }
}
