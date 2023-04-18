package com.example.laboratorytrackersd.controllers;

import com.example.laboratorytrackersd.model.Laboratory;
import com.example.laboratorytrackersd.services.LaboratoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/laboratory")
@EnableMethodSecurity
public class LaboratoryController {
    @Autowired
    LaboratoryService laboratoryService;

    @GetMapping("/getLaboratories")
    //@PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<List<Laboratory>> getConcerts(){
        return ResponseEntity.ok().body(laboratoryService.fetchLaboratories());
    }

    @PostMapping("/createLaboratory")
    @PreAuthorize("hasAuthority('TEACHER')")
    public Laboratory createConcert(@RequestBody Laboratory laboratory) {
        return laboratoryService.createLaboratory(laboratory);
    }

    @DeleteMapping("/deleteLaboratory/{id}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public ResponseEntity<?>deleteLaboratory(@PathVariable("id") Integer id){
        laboratoryService.deleteLaboratoryById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/updateLaboratory/{id}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public Laboratory updateConcert(@RequestBody Laboratory laboratory , @PathVariable("id") Integer id){
        return laboratoryService.updateLaboratory(laboratory,id);
    }
}
