package com.kaniya.resturentbackend.dto;

import com.kaniya.resturentbackend.model.Items;
import com.kaniya.resturentbackend.model.Menu;
import lombok.Data;

import java.sql.Blob;

@Data
public class DishDto {
    private long id;
    private String categoryName;
    private String dishDescription;
    private  Double DishPrice;
    private Blob dishPicture;

    private Menu menu;

    private Items items;

}
