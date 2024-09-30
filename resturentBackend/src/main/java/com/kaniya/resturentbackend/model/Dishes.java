package com.kaniya.resturentbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Dishes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    private  String dishName;
    private  Double dishPrice;
    private  String dishDescription;
    private String dishImage;

    @ManyToOne
    @JoinColumn(name="Category_is")
    private Category category;
}
