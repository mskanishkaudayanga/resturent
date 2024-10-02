package com.kaniya.resturentbackend.services.menu;

import com.kaniya.resturentbackend.exceptions.MenuNotFoundExeption;
import com.kaniya.resturentbackend.exceptions.ResturentNotFoundExeption;
import com.kaniya.resturentbackend.model.Menu;
import com.kaniya.resturentbackend.model.Resturents;
import com.kaniya.resturentbackend.repository.MenuRepository;
import com.kaniya.resturentbackend.repository.ResturentRepository;
import com.kaniya.resturentbackend.reqest.AddMenuRequest;
import com.kaniya.resturentbackend.reqest.UpdateMenuRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServices implements IMenuServices{

    private final MenuRepository menuRepository;
    private  final ResturentRepository resturentRepository;

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
        menu.setMenuName(request.getMenuName());
        menu.setResturents(resturents);
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
}
