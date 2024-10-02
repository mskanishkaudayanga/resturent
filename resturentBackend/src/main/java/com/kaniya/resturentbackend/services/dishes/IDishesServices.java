package com.kaniya.resturentbackend.services.dishes;

import com.kaniya.resturentbackend.model.Dishes;
import com.kaniya.resturentbackend.model.Menu;
import com.kaniya.resturentbackend.reqest.AddDishesRequest;
import com.kaniya.resturentbackend.reqest.DishUpdateRequest;

import java.util.List;

public interface IDishesServices {
    Dishes getDishesByID(long id);
    Menu getAllDishesByMenuId(long id);
    Dishes updateDishById(long id, DishUpdateRequest request);
    void deleteDishById(long id);
    Dishes addDishes(AddDishesRequest request,long menuId);
}
