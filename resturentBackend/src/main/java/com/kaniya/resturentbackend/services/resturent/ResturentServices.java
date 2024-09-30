package com.kaniya.resturentbackend.services.resturent;


import com.kaniya.resturentbackend.exceptions.ResturentNotFoundExeption;
import com.kaniya.resturentbackend.model.Resturents;
import com.kaniya.resturentbackend.repository.ResturentRepository;
import com.kaniya.resturentbackend.reqest.AddResturentRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ResturentServices implements  IResturentService{

    private  final ResturentRepository resturentRepository;
    @Override
    public Resturents addResturents(AddResturentRequest resturents) {
        String resturentName = Optional.ofNullable(resturents.getResturentName())
                .orElseThrow(()-> new IllegalArgumentException("resturentName is null"));
        return resturentRepository.save(createResturent(resturents));
    }

    private  Resturents createResturent(AddResturentRequest request) {
        return new Resturents(
               request.getResturentName(),
                request.getEmail(),
                request.getPassword(),
                request.getPhoneNumber()
        );
    }

    @Override
    public Resturents updateResturents(AddResturentRequest resturents, long id) {

        return null;
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
}
