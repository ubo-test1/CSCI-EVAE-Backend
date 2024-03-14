package com.back.csaback.Controllers;

import com.back.csaback.Repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("qes")
@CrossOrigin
public class QuestionController {

    @Autowired
    private QuestionRepository qr;

    @GetMapping("all")
    @PreAuthorize("hasRole('ADM') || hasRole('ENS')")
    public ResponseEntity<?> all(){
        try{
            return ResponseEntity.ok(qr.findAll());
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}
