package com.back.csaback.Controllers;

import com.back.csaback.Models.Enseignant;
import com.back.csaback.Services.EnseignantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/promotion"})
@CrossOrigin(origins = "http://localhost:3000")
public class EnseignantController {
    @Autowired
    EnseignantService enseignantService;


    public Enseignant findByid(Integer id){
        return enseignantService.findById(id).orElse(null);
    }
}
