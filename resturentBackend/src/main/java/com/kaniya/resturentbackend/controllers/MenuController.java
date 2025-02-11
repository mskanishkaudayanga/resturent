package com.kaniya.resturentbackend.controllers;

import com.kaniya.resturentbackend.dto.MenuDto;
import com.kaniya.resturentbackend.exceptions.MenuNotFoundExeption;
import com.kaniya.resturentbackend.model.Menu;
import com.kaniya.resturentbackend.reqest.AddMenuRequest;
import com.kaniya.resturentbackend.reqest.UpdateMenuRequest;
import com.kaniya.resturentbackend.responce.ApiResponse;
import com.kaniya.resturentbackend.security.jwt.JwtUtils;
import com.kaniya.resturentbackend.services.menu.MenuServices;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    JwtUtils jwtUtils;
    private final MenuServices menuServices;

    @PostMapping("/menuAdd")
    public ResponseEntity<ApiResponse> addMenu(@RequestBody AddMenuRequest request , @RequestHeader("Authorization") String token) {
        try {
            Long restaurantId = jwtUtils.getIdFromToken(token);
            Menu menu =menuServices.addMenu(request,restaurantId);
            return  ResponseEntity.ok(new ApiResponse("Menu Added Succefull",menu));
        } catch (Exception e) {
            return  ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @GetMapping("MenuByResturentID")
    public ResponseEntity<ApiResponse>  getMenuByRestaurantId(@RequestHeader("Authorization")String token) {
        try {
            Long restaurantId = jwtUtils.getIdFromToken(token);
            List<Menu> allMenus = menuServices.GetMenuByResturentID(restaurantId);
            List<MenuDto> allMenusDto = menuServices.getAllconvertedMenu(allMenus);
            return  ResponseEntity.ok(new ApiResponse("Menu List",allMenusDto));
        }
        catch (Exception e) {
            return  ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @PutMapping("/{menuId}/UpdateMenuByMenuID")
    public ResponseEntity<ApiResponse>  updateMenuBYMenuID(@RequestBody UpdateMenuRequest request ,@PathVariable Long menuId) {
        try {
            Menu updatedMenu = menuServices.updateMenu(request,menuId);
            return  ResponseEntity.ok(new ApiResponse("Menu List",updatedMenu));
        }
        catch (MenuNotFoundExeption e) {
            return  ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @DeleteMapping("/{menuId}/DeleteMenuByMenuID")
    public  ResponseEntity<ApiResponse>  deleteMenuByMenuID(@PathVariable Long menuId) {
        try{
            menuServices.deleteMenu( menuId);
            return ResponseEntity.ok(new ApiResponse("Deleted",null));
        }
        catch (MenuNotFoundExeption e) {
            return  ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }
}
