package com.kaniya.resturentbackend.repository;

import com.kaniya.resturentbackend.model.Resturents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResturentRepository extends JpaRepository<Resturents ,Long> {

    List<Resturents> findByRestaurantName(String restaurantName);
    List<Resturents> findByCity(String city);

}
