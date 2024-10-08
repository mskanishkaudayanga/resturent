package com.kaniya.resturentbackend.services.menu;

import com.kaniya.resturentbackend.dto.DishDto;
import com.kaniya.resturentbackend.dto.MenuDto;
import com.kaniya.resturentbackend.exceptions.MenuNotFoundExeption;
import com.kaniya.resturentbackend.exceptions.ResturentNotFoundExeption;
import com.kaniya.resturentbackend.model.Dishes;
import com.kaniya.resturentbackend.model.Menu;
import com.kaniya.resturentbackend.model.Resturents;
import com.kaniya.resturentbackend.repository.DishRepository;
import com.kaniya.resturentbackend.repository.MenuRepository;
import com.kaniya.resturentbackend.repository.ResturentRepository;
import com.kaniya.resturentbackend.reqest.AddMenuRequest;
import com.kaniya.resturentbackend.reqest.UpdateMenuRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServices implements IMenuServices{

    private final MenuRepository menuRepository;
    private  final ResturentRepository resturentRepository;
    private  final ModelMapper modelMapper;
    private final DishRepository dishRepository;
    @Override
    public List<Menu> GetMenuByResturentID(long id) {
       Resturents resturent= resturentRepository.findById(id).orElseThrow(()-> new ResturentNotFoundExeption("resturent Not Found"));
        return resturent.getMenu();

    }

    @Override
    public Menu GetMenuByMenuName(String menuName) {
        return menuRepository.findByMenuName(menuName);
//        menuRepository.findByMenuName(menuName);
    }

    @Override
    public Menu addMenu(AddMenuRequest request ,long restaurantId) {
        Resturents resturents = resturentRepository.findById(restaurantId)
                .orElseThrow(()-> new ResturentNotFoundExeption("restaurant not found"));

        Menu menu = new Menu();
        System.out.println("resturent find "+resturents);
        menu.setMenuName(request.getMenuName());
        menu.setResturents(resturents);
        System.out.println("menu" +menu);
        return menuRepository.save(menu);
    }

    @Override
    public Menu updateMenu(UpdateMenuRequest menu,long id) {
        return menuRepository.findById(id)
                .map(existingMenu->UpdateMenu(existingMenu,menu))
                .map(menuRepository::save)
                .orElseThrow(()-> new MenuNotFoundExeption("menu not found"));

    }
    private  Menu UpdateMenu(Menu existingMenu ,UpdateMenuRequest request){
        existingMenu.setMenuName(request.getMenuName());
        return existingMenu;
    }

    @Override
    public void deleteMenu(long menuID) {
        menuRepository.findById(menuID).ifPresentOrElse(
                menuRepository::delete,
                ()->{throw new MenuNotFoundExeption("menu not Found");}
        );

    }
    @Override
    public List<MenuDto> getAllconvertedMenu(List<Menu> menus) {
        return menus.stream().map(this::convertToDto).toList();

    }
    @Override
    public MenuDto convertToDto(Menu menu){
        MenuDto menuDto =modelMapper.map(menu,MenuDto.class);
        List<Dishes> dishes = dishRepository.findByMenuId(menu.getId());
        List<DishDto> dishDto = dishes.stream()
                .map(dish->modelMapper.map(dish, DishDto.class))
                .toList();
        menuDto.setDishes(dishDto);
        return menuDto;
    }
}
