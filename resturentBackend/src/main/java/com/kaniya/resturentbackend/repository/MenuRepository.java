package com.kaniya.resturentbackend.repository;

import com.kaniya.resturentbackend.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Menu findByMenuName(String menuName);


//    Menu findByRestaurantId(long restaurantId);
//    Menu findByMenuName(String menuName);
}
