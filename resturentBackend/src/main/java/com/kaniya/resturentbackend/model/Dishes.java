package com.kaniya.resturentbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Dishes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String categoryName;
    private String dishDescription;
    private  Double DishPrice;

    @Lob
    private Blob dishPicture;

    @ManyToOne
    @JoinColumn(name="Menu_is")
    private Menu menu;

   @ManyToOne
   @JoinColumn(name="Item_is")
   private  Items items;

    public Dishes(String categoryName, String dishDescription, Double dishPrice, Items items,Menu menu) {
        this.categoryName = categoryName;
        this.dishDescription = dishDescription;
        this.DishPrice = dishPrice;
        this.items = items;
        this.menu = menu;
    }
}
