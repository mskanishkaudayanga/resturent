package com.kaniya.resturentbackend.reqest;

import com.kaniya.resturentbackend.model.Items;
import lombok.Data;

import java.sql.Blob;
@Data
public class AddDishesRequest {
    private String categoryName;
    private String dishDescription;
    private  Double DishPrice;
//    private Blob dishPicture;
    private Items items;
}
