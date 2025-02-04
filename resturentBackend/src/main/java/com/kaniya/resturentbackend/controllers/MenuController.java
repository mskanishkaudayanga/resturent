package com.kaniya.resturentbackend.controllers;

import com.kaniya.resturentbackend.dto.MenuDto;
import com.kaniya.resturentbackend.exceptions.MenuNotFoundExeption;
import com.kaniya.resturentbackend.model.Menu;
import com.kaniya.resturentbackend.reqest.AddMenuRequest;
import com.kaniya.resturentbackend.reqest.UpdateMenuRequest;
import com.kaniya.resturentbackend.responce.ApiResponse;
import com.kaniya.resturentbackend.services.menu.MenuServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;


@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("${api.prefix}/menu")
public class MenuController {
    private final MenuServices menuServices;

    @PostMapping("{restaurantId}/menuAdd")
    public ResponseEntity<ApiResponse> addMenu(@RequestBody AddMenuRequest request , @PathVariable long restaurantId) {
        try {
            Menu menu =menuServices.addMenu(request,restaurantId);
            return  ResponseEntity.ok(new ApiResponse("Menu Added Succefull",menu));
        } catch (Exception e) {
            return  ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @GetMapping("menu/MenuByResturentID")
    public ResponseEntity<ApiResponse>  getMenuByRestaurantId(@PathVariable long restaurantId) {
        try {
            List<Menu> allMenus = menuServices.GetMenuByResturentID(restaurantId);
            List<MenuDto> allMenusDto = menuServices.getAllconvertedMenu(allMenus);
            return  ResponseEntity.ok(new ApiResponse("Menu List",allMenusDto));
        }
        catch (Exception e) {
            return  ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @GetMapping("menu/MenuByResturentID")
    public ResponseEntity<ApiResponse>  updateMenuBYMenuID( UpdateMenuRequest request ,Long menuID) {
        try {
            Menu updatedMenu = menuServices.updateMenu(request,menuID);
            return  ResponseEntity.ok(new ApiResponse("Menu List",updatedMenu));
        }
        catch (MenuNotFoundExeption e) {
            return  ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }
}
