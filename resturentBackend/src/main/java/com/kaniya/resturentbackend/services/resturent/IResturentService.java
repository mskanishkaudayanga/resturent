package com.kaniya.resturentbackend.services.resturent;

import com.kaniya.resturentbackend.model.Resturents;
import com.kaniya.resturentbackend.reqest.AddResturentRequest;
import com.kaniya.resturentbackend.reqest.UpdateResturentRequest;

import java.util.List;

public interface IResturentService {
    Resturents addResturents(AddResturentRequest resturents);
    Resturents updateResturents(UpdateResturentRequest resturents , long id);
    void deleteResturents(long id);
    Resturents getResturentsById(long id);
    List<Resturents> getAllResturents();
    List<Resturents> getResturentsByName(String name);
    List<Resturents> getResturentsByCity(String city);



}
