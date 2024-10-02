package com.kaniya.resturentbackend.repository;

import com.kaniya.resturentbackend.model.Dishes;
import com.kaniya.resturentbackend.model.Items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dishes,Long> {

}
