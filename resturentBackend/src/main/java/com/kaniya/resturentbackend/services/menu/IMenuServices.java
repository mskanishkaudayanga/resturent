package com.kaniya.resturentbackend.services.menu;

import com.kaniya.resturentbackend.model.Menu;
import com.kaniya.resturentbackend.reqest.AddMenuRequest;
import com.kaniya.resturentbackend.reqest.UpdateMenuRequest;

import java.util.List;

public interface IMenuServices {
    List<Menu> GetMenuByResturentID(long id);
    Menu GetMenuByMenuName(String menuName);
    Menu addMenu(AddMenuRequest menu,long resturentId);
    Menu updateMenu(UpdateMenuRequest menu,long id);
    void deleteMenu(long menuID);
}
