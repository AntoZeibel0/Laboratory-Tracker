package com.example.laboratorytrackersd.services;

import com.example.laboratorytrackersd.model.Laboratory;
import com.example.laboratorytrackersd.model.User;
import com.example.laboratorytrackersd.repo.LaboratoryRepo;
import com.example.laboratorytrackersd.repo.UserRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class LaboratoryService {
    @Autowired
    private LaboratoryRepo laboratoryRepo;

    @Autowired
    private UserRepo userRepo;

    public Laboratory createLaboratory(Laboratory laboratory) {
        return laboratoryRepo.save(laboratory);
    }

    public List<Laboratory> fetchLaboratories(){
        return (List<Laboratory>) laboratoryRepo.findAll();
    }

    public void deleteLaboratoryById(Integer id) {
        laboratoryRepo.delete(laboratoryRepo.findById(id).get());
    }

    public Laboratory updateLaboratory(Laboratory laboratory, Integer id) {
        Laboratory laboratoryDB = laboratoryRepo.findById(id).get();

        if(Objects.nonNull(laboratory.getNumber())) {
            laboratoryDB.setNumber(laboratory.getNumber());
        }

        if(Objects.nonNull(laboratory.getTitle())
                && !"".equalsIgnoreCase(laboratory.getTitle())) {
            laboratoryDB.setTitle(laboratory.getTitle());
        }

        if(Objects.nonNull(laboratory.getDate())) {
            laboratoryDB.setDate(laboratory.getDate());
        }

        if(Objects.nonNull(laboratory.getCurricula())
                && !"".equalsIgnoreCase(laboratory.getCurricula())) {
            laboratoryDB.setCurricula(laboratory.getCurricula());
        }

        if(Objects.nonNull(laboratory.getDescription())
                && !"".equalsIgnoreCase(laboratory.getDescription())) {
            laboratoryDB.setDescription(laboratory.getDescription());
        }

        return laboratoryRepo.save(laboratoryDB);
    }

}
