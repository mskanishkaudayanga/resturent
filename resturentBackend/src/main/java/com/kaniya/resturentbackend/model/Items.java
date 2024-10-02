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
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    private  String itemName;

    @OneToMany(mappedBy = "items" ,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dishes> dishes;

    public Items(String itemName) {
        this.itemName = itemName;
    }
}
