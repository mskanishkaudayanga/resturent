package com.kaniya.resturentbackend.repository;

import com.kaniya.resturentbackend.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Menu findByMenuName(String menuName);


    List<Menu> findByResturentsId(long id);


//    Menu findByRestaurantId(long restaurantId);
//    Menu findByMenuName(String menuName);
}
