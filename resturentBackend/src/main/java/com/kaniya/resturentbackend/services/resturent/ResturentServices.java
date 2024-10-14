package com.kaniya.resturentbackend.services.resturent;


import com.kaniya.resturentbackend.dto.MenuDto;
import com.kaniya.resturentbackend.dto.ResturentDto;
import com.kaniya.resturentbackend.exceptions.ResturentNotFoundExeption;
import com.kaniya.resturentbackend.model.Menu;
import com.kaniya.resturentbackend.model.Resturents;
import com.kaniya.resturentbackend.repository.MenuRepository;
import com.kaniya.resturentbackend.repository.ResturentRepository;
import com.kaniya.resturentbackend.reqest.AddResturentRequest;
import com.kaniya.resturentbackend.reqest.UpdateResturentRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class ResturentServices implements  IResturentService{

    private  final ResturentRepository resturentRepository;
    private final MenuRepository menuRepository;
    private final ModelMapper modelMapper;

    @Override
    public Resturents addResturents(AddResturentRequest restaurants) {
        String restaurantName = Optional.ofNullable(restaurants.getRestaurantName())
                .orElseThrow(()-> new IllegalArgumentException("restaurantName is null"));
        return resturentRepository.save(createRestaurant(restaurants));
    }

    private  Resturents createRestaurant(AddResturentRequest request) {
        return new Resturents(
               request.getRestaurantName(),
                request.getEmail(),
                request.getPassword(),
                request.getCity()
        );
    }

    @Override
    public Resturents updateResturents(UpdateResturentRequest resturents, long id) {
        return  resturentRepository.findById(id)
                .map(existingResturent-> updateRestaurant(existingResturent,resturents))
                .map(resturentRepository::save)
                .orElseThrow(()-> new ResturentNotFoundExeption("Resturent not found"));
    }
    private  Resturents updateRestaurant(Resturents existingRestaurant, UpdateResturentRequest request) {
        existingRestaurant.setRestaurantName(request.getResturentName());
        existingRestaurant.setEmail(request.getEmail());
        existingRestaurant.setPassword(request.getPassword());
        existingRestaurant.setPhoneNumber(request.getPhoneNumber());
        return  existingRestaurant;
    }

    @Override
    public void deleteResturents(long id) {
        resturentRepository.findById(id)
                .ifPresentOrElse(resturentRepository::delete,
                        ()->{throw new ResturentNotFoundExeption("Resturent Not Found");
                        });

    }

    @Override
    public Resturents getResturentsById(long id) {
        return resturentRepository.findById(id).orElseThrow(()-> new ResturentNotFoundExeption("resturent Not Found"));
    }

    @Override
    public List<Resturents> getAllResturents() {
        return resturentRepository.findAll();
    }

    @Override
    public List<Resturents> getResturentsByName(String name) {
        return resturentRepository.findByRestaurantName(name);
    }

    @Override
    public List<Resturents> getResturentsByCity(String city) {
        return resturentRepository.findByCity(city);
    }

    @Override
    public List<ResturentDto> getConvertedResturents(List<Resturents> resturents){
       return resturents.stream().map(this::convertToDto).toList();
    }


    @Override
    public ResturentDto convertToDto(Resturents resturents){
        ResturentDto  resturentDto =modelMapper.map(resturents,ResturentDto.class);
        List<Menu> menues =  menuRepository.findByResturentsId(resturents.getId());
        List<MenuDto> menu = menues.stream()
                .map(menus->modelMapper.map(menus,MenuDto.class))
                .toList();
       resturentDto.setMenu(menu);
        return resturentDto;
    }
}
