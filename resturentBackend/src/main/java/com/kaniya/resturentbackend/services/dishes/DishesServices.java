package com.kaniya.resturentbackend.services.dishes;

import com.kaniya.resturentbackend.exceptions.DishNotFoundException;
import com.kaniya.resturentbackend.exceptions.MenuNotFoundExeption;
import com.kaniya.resturentbackend.exceptions.ResturentNotFoundExeption;
import com.kaniya.resturentbackend.model.Dishes;
import com.kaniya.resturentbackend.model.Items;
import com.kaniya.resturentbackend.model.Menu;
import com.kaniya.resturentbackend.model.Resturents;
import com.kaniya.resturentbackend.repository.DishRepository;
import com.kaniya.resturentbackend.repository.ItemsRepository;
import com.kaniya.resturentbackend.repository.MenuRepository;
import com.kaniya.resturentbackend.repository.ResturentRepository;
import com.kaniya.resturentbackend.reqest.AddDishesRequest;
import com.kaniya.resturentbackend.reqest.DishUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DishesServices implements  IDishesServices {

    private  final DishRepository dishRepository;
    private final MenuRepository menuRepository;
    private  final ItemsRepository itemsRepository;
    private final ResturentRepository resturentRepository;

    @Override
    public Dishes getDishesByID(long id) {
        return dishRepository.findById(id).orElseThrow(()-> new DishNotFoundException("dish not found"));
    }

    @Override
    public Menu getAllDishesByMenuId(long id) {
        return menuRepository.findById(id).orElseThrow(()-> new MenuNotFoundExeption("menu Is not Found"));
    }


    @Override
    public Dishes updateDishById(long id, DishUpdateRequest request) {
        return dishRepository.findById(id)
                .map(existingDish->updateDishes(existingDish,request))
                .map(dishRepository::save)
                .orElseThrow(()-> new DishNotFoundException("dish not found"));
    }

    private Dishes updateDishes(Dishes existingDish,  DishUpdateRequest request) {

            existingDish.setCategoryName(request.getCategoryName());
            existingDish.setDishPrice(request.getDishPrice());
            existingDish.setDishDescription(request.getDishDescription());
//            existingDish.setDishPicture(new SerialBlob(request.getDishPicture().getBytes()));
            Items item =itemsRepository.findByItemName(request.getItemName());
            existingDish.setItems(item);

      return  existingDish;


    }
    @Override
    public void deleteDishById(long id) {
        dishRepository.findById(id)
                .ifPresentOrElse(dishRepository::delete,
                        ()->{throw new DishNotFoundException("Dish  not Found");});
    }

    @Override
    public Dishes addDishes(AddDishesRequest request,long menuId) {
        Menu menu = menuRepository.findById(menuId).orElseThrow(()-> new MenuNotFoundExeption("menu not found"));
        Items items = (Items) Optional.ofNullable(itemsRepository.findByItemName(request.getItems().getItemName()))
                .orElseGet(()->{
                    Items newItems = new Items(request.getItems().getItemName());
                    return itemsRepository.save(newItems);
                });
        request.setItems(items);
        return dishRepository.save(createDish(request,items,menu));
    }
    public  Dishes createDish(AddDishesRequest request, Items items,Menu menu) {
        return  new Dishes(
                request.getCategoryName(),
                request.getDishDescription(),
                request.getDishPrice(),
                items,
                menu
        );
    }
}
