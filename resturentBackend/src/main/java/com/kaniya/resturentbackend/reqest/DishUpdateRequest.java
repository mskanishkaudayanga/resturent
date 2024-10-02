package com.kaniya.resturentbackend.reqest;

import com.kaniya.resturentbackend.model.Items;
import lombok.Data;

import java.sql.Blob;
@Data
public class DishUpdateRequest {
    private String categoryName;
    private String dishDescription;
    private  Double dishPrice;
    private  String itemName;
//    private Blob dishPicture;
    private Items items;
}
