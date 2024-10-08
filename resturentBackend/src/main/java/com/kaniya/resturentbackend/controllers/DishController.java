package com.kaniya.resturentbackend.controllers;

import com.kaniya.resturentbackend.exceptions.MenuNotFoundExeption;
import com.kaniya.resturentbackend.model.Dishes;
import com.kaniya.resturentbackend.model.Menu;
import com.kaniya.resturentbackend.reqest.AddDishesRequest;
import com.kaniya.resturentbackend.responce.ApiResponse;
import com.kaniya.resturentbackend.services.dishes.DishesServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("${api.prefix}/Dishes")
public class DishController {

    private  final DishesServices dishesServices;

    @GetMapping("/{id}/getDishesByMenuId")
    public ResponseEntity<ApiResponse> getAllDishesMenuID(@PathVariable long id){
        try {
            Menu menu = dishesServices.getAllDishesByMenuId(id);
            return  ResponseEntity.ok(new ApiResponse("Success", menu));
        } catch (MenuNotFoundExeption e) {
            return  ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Menu Not Found", e));
        }
    }
    @PostMapping("{menuId}/DishAdd")
    public ResponseEntity<ApiResponse> addDishesMenuID(@PathVariable long menuId, @RequestBody AddDishesRequest request){
        try {
            Dishes dish = dishesServices.addDishes(request,menuId);
            return  ResponseEntity.ok(new ApiResponse("Dish Added ", dish));
        } catch (Exception e) {
           return  ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }

}
