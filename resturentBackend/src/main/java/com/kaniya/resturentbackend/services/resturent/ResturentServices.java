package com.kaniya.resturentbackend.services.resturent;


import com.kaniya.resturentbackend.dto.MenuDto;
import com.kaniya.resturentbackend.dto.ResturentDto;
import com.kaniya.resturentbackend.exceptions.AlreadyExistsException;
import com.kaniya.resturentbackend.exceptions.ResturentNotFoundExeption;
import com.kaniya.resturentbackend.model.Menu;
import com.kaniya.resturentbackend.model.Resturents;
import com.kaniya.resturentbackend.model.UserRole;
import com.kaniya.resturentbackend.repository.MenuRepository;
import com.kaniya.resturentbackend.repository.ResturentRepository;
import com.kaniya.resturentbackend.reqest.AddResturentRequest;
import com.kaniya.resturentbackend.reqest.UpdateResturentRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class ResturentServices implements  IResturentService{

    private final PasswordEncoder passwordEncoder;
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
    public Resturents createUser(AddResturentRequest request) {
        try {
            System.out.println(passwordEncoder.encode(request.getPassword()));
            return  Optional.of(request)
                    .filter(user -> !resturentRepository.existsByEmail(request.getEmail()))
                    .map(req -> {
                        Resturents user = new Resturents();
                        user.setEmail(request.getEmail());
                        user.setPassword(passwordEncoder.encode(request.getPassword()));
                        user.setRole(UserRole.USER);
                        user.setRestaurantName(request.getRestaurantName());

                        return  resturentRepository.save(user);
                    }).orElseThrow(() -> new AlreadyExistsException("Oops!" +request.getEmail() +" already exists!"));
        } catch (AlreadyExistsException e) {
            throw new RuntimeException(e);
        }
    }




    @Override
    public Resturents updateResturents(UpdateResturentRequest resturents, long id) {
        return  resturentRepository.findById(id)
                .map(existingResturent-> updateRestaurant(existingResturent,resturents))
                .map(resturentRepository::save)
                .orElseThrow(()-> new ResturentNotFoundExeption("Resturent not found"));
    }
    private  Resturents updateRestaurant(Resturents existingRestaurant, UpdateResturentRequest request) {
        existingRestaurant.setRestaurantName(request.getRestaurantName());
        existingRestaurant.setEmail(request.getEmail());
        existingRestaurant.setPassword(request.getPassword());
        existingRestaurant.setPhoneNumber(request.getPhoneNumber());
        existingRestaurant.setCity(request.getCity());
        existingRestaurant.setAddress(request.getAddress());
        existingRestaurant.setOpenTime(request.getOpenTime());
        existingRestaurant.setCloseTime(request.getCloseTime());
        existingRestaurant.setDiscription(request.getDiscription());
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
