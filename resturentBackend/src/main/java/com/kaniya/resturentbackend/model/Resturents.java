package com.kaniya.resturentbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Resturents implements UserDetails {
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
    private String openTime;
    private String closeTime;
    private String openingDays;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @JsonIgnore
    @OneToMany(mappedBy = "resturents" ,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Menu> menu;

    public Resturents(String restaurantName, String email, String password, String city) {
        this.restaurantName = restaurantName;
        this.email = email;
        this.password = password;
        this.city = city;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.email;
    }
    @Override
    public String getPassword() {
        return this.password;
    }
}
