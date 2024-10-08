package com.kaniya.resturentbackend.dto;

import com.kaniya.resturentbackend.model.Dishes;
import com.kaniya.resturentbackend.model.Resturents;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
public class MenuDto {

    private long id;
    private String menuName;
    private List<DishDto> dishes;
}
