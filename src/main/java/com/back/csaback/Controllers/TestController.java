package com.back.csaback.Controllers;

import com.back.csaback.Config.MD5PasswordEncoder;
import com.back.csaback.Models.Authentification;
import com.back.csaback.Repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {
    private MD5PasswordEncoder pe;

    @Autowired
    private UserRepository ur;

    @GetMapping("test")
    public String test(){
        return "Hello VVorld";
    }
/*
    @PostConstruct
    public void fixPasswords(){
        pe = new MD5PasswordEncoder();
        Authentification a1 = ur.findByLoginConnection("Administrateur").get();
        a1.setMotPasse(pe.encode(a1.getMotPasse()));
        ur.save(a1);
    }*/
}