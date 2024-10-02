package com.kaniya.resturentbackend.repository;

import com.kaniya.resturentbackend.model.Items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsRepository extends JpaRepository<Items, Long> {

    Items findByItemName(String itemName);
}


