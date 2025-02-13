package com.kaniya.resturentbackend.controllers;

import com.kaniya.resturentbackend.dto.ResturentDto;
import com.kaniya.resturentbackend.exceptions.ResturentNotFoundExeption;
import com.kaniya.resturentbackend.model.Resturents;
import com.kaniya.resturentbackend.reqest.AddResturentRequest;
import com.kaniya.resturentbackend.reqest.UpdateResturentRequest;
import com.kaniya.resturentbackend.responce.ApiResponse;
import com.kaniya.resturentbackend.security.jwt.JwtUtils;
import com.kaniya.resturentbackend.services.resturent.ResturentServices;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kaniya.resturentbackend.security.jwt.JwtUtils.*;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("${api.prefix}/restaurants")
public class ResturentController {
    @Autowired
    JwtUtils jwtUtils;
private final ResturentServices resturentServices;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllRestaurant() {
        try {
            List<Resturents> allResturents = resturentServices.getAllResturents();
            System.out.println(allResturents);
            List<ResturentDto> allResturentDto =resturentServices.getConvertedResturents(allResturents);
            return ResponseEntity.ok(new ApiResponse("Resturents ",allResturentDto));
        } catch (ResturentNotFoundExeption e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Resturents ",e));
        }
    }


    @PostMapping("/add")
    public  ResponseEntity<ApiResponse> addRestaurant(@RequestBody AddResturentRequest restaurant) {
        try {
            System.out.println("back"+restaurant);
            Resturents resturents=resturentServices.createUser(restaurant);
            return ResponseEntity.ok(new ApiResponse("Susses ",resturents));
        } catch (Exception e) {
            return  ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));

        }
    }
//    id get by the token decode
    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateRestaurant(@RequestBody UpdateResturentRequest restaurant, @RequestHeader("Authorization") String token) {
        try {
            Long restaurantId = jwtUtils.getIdFromToken(token);
            Resturents resturents =resturentServices.updateResturents(restaurant,restaurantId);
            return  ResponseEntity.ok(new ApiResponse("Updated ",resturents));
        } catch (ResturentNotFoundExeption e) {
           return  ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Resturents ",e));
        }
    }

    @GetMapping("/restaurant")
    public  ResponseEntity<ApiResponse> getRestaurantById(@RequestHeader("Authorization")String token ) {
        try {
            Long restaurantId = jwtUtils.getIdFromToken(token);
            Resturents restaurant = resturentServices.getResturentsById(restaurantId);
            ResturentDto resturentDto =resturentServices.convertToDto(restaurant);
            return  ResponseEntity.ok(new ApiResponse("Found Restaurant ",resturentDto));
        } catch (ResturentNotFoundExeption e) {
            return  ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Not Found Restaurant ",e));
        }
    }

    @DeleteMapping("{restaurantID}/delete")
    public  ResponseEntity<ApiResponse> deleteRestaurant(@PathVariable long restaurantID) {
        try {
            resturentServices.deleteResturents(restaurantID);
            return  ResponseEntity.ok(new ApiResponse("Deleted ",null));
        } catch (ResturentNotFoundExeption e) {
           return  ResponseEntity.status(NOT_FOUND).body(new ApiResponse( e.getMessage(),null));
        }
    }

    @GetMapping("restaurant/by/name")
    public ResponseEntity<ApiResponse> getRestaurantsByName(@RequestParam String name) {
        try {
            List<Resturents> restaurantsByName = resturentServices.getResturentsByName(name);
            return ResponseEntity.ok(new ApiResponse("Resturents ",restaurantsByName));
        } catch (ResturentNotFoundExeption e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Resturents ",e));
        }
    }


    @GetMapping("restaurant/by/city")
    public ResponseEntity<ApiResponse> getRestaurantsByCity(@RequestParam String city) {
        try {
            List<Resturents> restaurantsByCity = resturentServices.getResturentsByCity(city);
            return ResponseEntity.ok(new ApiResponse("Resturents ",restaurantsByCity));
        } catch (ResturentNotFoundExeption e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Resturents ",e));
        }
    }

}
