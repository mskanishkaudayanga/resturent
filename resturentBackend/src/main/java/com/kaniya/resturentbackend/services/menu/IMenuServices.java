package com.kaniya.resturentbackend.services.menu;

import com.kaniya.resturentbackend.model.Menu;
import com.kaniya.resturentbackend.reqest.AddMenuRequest;
import com.kaniya.resturentbackend.reqest.UpdateMenuRequest;

public interface IMenuServices {
    Menu GetMenuByResturentID(int restaurantID);
    Menu GetMenuByMenuName(String menuName);
    Menu addMenu(AddMenuRequest menu);
    Menu updateMenu(UpdateMenuRequest menu,long id);
    void deleteMenu(long menuID);
}
