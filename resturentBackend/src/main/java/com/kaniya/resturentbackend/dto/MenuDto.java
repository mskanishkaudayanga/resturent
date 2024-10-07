package com.kaniya.resturentbackend.dto;

import com.kaniya.resturentbackend.model.Dishes;
import com.kaniya.resturentbackend.model.Resturents;
import jakarta.persistence.*;

import java.util.List;

public class MenuDto {

    private long id;
    private String menuName;
    private Resturents resturents;
    private List<Dishes> dishes;
}
