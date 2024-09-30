package com.kaniya.resturentbackend.services.resturent;

import com.kaniya.resturentbackend.model.Resturents;
import com.kaniya.resturentbackend.reqest.AddResturentRequest;

import java.util.List;

public interface IResturentService {
    Resturents addResturents(AddResturentRequest resturents);
    Resturents updateResturents(AddResturentRequest resturents ,long id);
    void deleteResturents(long id);
    Resturents getResturentsById(long id);
    List<Resturents> getAllResturents();


}
