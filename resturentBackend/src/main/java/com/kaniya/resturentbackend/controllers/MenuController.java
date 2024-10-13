package com.kaniya.resturentbackend.controllers;

import com.kaniya.resturentbackend.model.Menu;
import com.kaniya.resturentbackend.reqest.AddMenuRequest;
import com.kaniya.resturentbackend.responce.ApiResponse;
import com.kaniya.resturentbackend.services.menu.MenuServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;


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
}
